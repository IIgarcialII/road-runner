using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace SharedHashSet
{
    class MyHashSet<T>
    {
        private Element<T>[] table;
        //private int tableSize;
        private int setSize;

        private void printList(string pre)
        {
            //Console.Write(pre);
            //Element<T> t = table[0];
            //while (t != null)
            //{
            //    Console.Write(t + " ");
            //    t = t.GetNext();
            //}
            //Console.WriteLine();
        }

        public MyHashSet(/*uint maximumCapacity*/)
        {
            //uint capacity = Util.NextHighestPowerOfTwo(maximumCapacity);
            uint capacity = 2;
            table = new Element<T>[capacity];
            table[0] = new Element<T>(default(T), Util.MakeSentinelKey(0));
            //tableSize = 2;
            setSize = 0;
            printList("Initial List: ");
        }

        /// <summary>
        /// Find a node in the linked list starting from head
        /// </summary>
        /// <param name="key"></param>
        /// <param name="head"></param>
        /// <param name="parent"></param>
        /// <returns></returns>
        private Element<T> LinkedListFindNode(uint key, Element<T> head, out Element<T> parent)
        {
            Element<T> dad = head;
            Element<T> node = dad.GetNext();

            while (node != null && (node.IsLessThanKey(key)))
            {
                dad = node;
                node = dad.GetNext();
            }

            parent = dad;
            return node;
        }

        /// <summary>
        /// Delete a key from the LinkedList, start looking for it at head
        /// </summary>
        /// <param name="key"></param>
        /// <param name="head"></param>
        /// <returns></returns>
        private bool LinkedListDelete(uint key, Element<T> head)
        {
            Element<T> parent;
            Element<T> node = LinkedListFindNode(key, head, out parent);
            if (node!=null && !node.IsDeleted() && node.HasSameKey(key))
            {
                node.FlagAsDeleted();
                parent.TryDeleteChild(node);
                return true;
            }
            return false;
        }

        /// <summary>
        /// Initialize a bucket
        /// </summary>
        /// <param name="bucket"></param>
        private void InitializeBucket(Element<T>[] table, uint bucket)
        {
            // The parent bucket is the one which has the same Least Significant Bits except
            //  the Most Significant Bit
            uint bucket_parent = Util.RemoveMostSignificantBit(bucket);
            if (table[bucket_parent] == null)
            {
                // If papa is not initialized, initialize him
                InitializeBucket(table, bucket_parent);
            }

            // Generate our sentinel key
            uint sentinel_key = Util.MakeSentinelKey(bucket);

            // Insert the sentinel until succesfull or someone else inserted it
            Element<T> head = table[bucket_parent];
            Element<T> node, parent;
            Element<T> newNode = new Element<T>(default(T), sentinel_key);
            do
            {
                node = LinkedListFindNode(sentinel_key, head, out parent);
                if (node != null && node.HasSameKey(sentinel_key))
                {
                    // Sentinel already inserted by someone else
                    break;
                }
            } while (!parent.TryInsertChild(newNode, node));

            // Get the sentinel
            node = LinkedListFindNode(sentinel_key, head, out parent);

            // Set the bucket's reference to sentinel if not already set
            Interlocked.CompareExchange<Element<T>>(ref table[bucket], node, null);

            printList("After inserting sentinel " + bucket + ": ");
        }

        /// <summary>
        /// Get a bucket
        /// </summary>
        /// <param name="bucket"></param>
        /// <returns></returns>
        private Element<T> GetBucketList(Element<T>[] table, uint bucket)
        {
            if (table[bucket] == null)
            {
                InitializeBucket(table, bucket);
            }
            return table[bucket];
        }

        /// <summary>
        /// Check to see if resize is needed and resize if it is the case
        /// </summary>
        private void ResizeCheck()
        {
            Element<T>[] old_table = table;
            int mySetSize = setSize;
            double ratio = (double)(mySetSize) / (double)(old_table.Length);
            if (ratio > 1.5)
            {
                Element<T>[] new_table = new Element<T>[2 * old_table.Length];
                // Make sure all buckets in the previous size are initialized before
                //  increasing the table size
                for (uint i = 0; i < old_table.Length; i++)
                {
                    Element<T> bucket = GetBucketList(old_table, i);
                    new_table[i] = bucket;
                }
                Console.WriteLine("Increasing Table!!");
                Interlocked.CompareExchange(ref table, new_table, old_table);
            }
        }

        /// <summary>
        /// Add an element to the HashSet
        /// </summary>
        /// <param name="element"></param>
        /// <returns></returns>
        public bool Add(T element)
        {
            uint hash = (uint)element.GetHashCode();
            uint bucket = hash % (uint)table.Length;
            uint key = Util.MakeRegularKey(hash);
            Element<T> newNode = new Element<T>(element, key);

            Element<T> parent, node;
            Element<T> head = GetBucketList(table, bucket);

            do 
            {
                node = LinkedListFindNode(key, head, out parent);
                if (node!=null && node.HasSameKey(key))
                {
                    // This element is already in the Hash
                    return false;
                }
            } while (!parent.TryInsertChild(newNode, node));

            // Increment the number of elements in the Hash
            Interlocked.Increment(ref setSize);
            
            // Check to see if a resize is appropiate
            ResizeCheck();

            printList("After Add " + element + ": ");
            return true;
        }

        /// <summary>
        /// Returns true if element is in the HashSet, false otherwise
        /// </summary>
        /// <param name="element"></param>
        /// <returns></returns>
        public bool Contains(T element)
        {
            uint hash = (uint)element.GetHashCode();
            uint bucket = hash % (uint)(table.Length);
            uint key = Util.MakeRegularKey(hash);

            Element<T> parent, node;
            Element<T> head = GetBucketList(table, bucket);

            node = LinkedListFindNode(key, head, out parent);
            if (node!=null && node.HasSameKey(key))
            {
                return true;
            }
            return false;
        }

        /// <summary>
        /// Remove an element from the HashSet
        /// </summary>
        /// <param name="element"></param>
        /// <returns></returns>
        public bool Remove(T element)
        {
            uint hash = (uint)element.GetHashCode();
            uint bucket = hash % (uint)(table.Length);
            uint key = Util.MakeRegularKey(hash);

            Element<T> head = GetBucketList(table, bucket);
            if (LinkedListDelete(key, head))
            {
                // Decrement the number of elements in the hash
                Interlocked.Decrement(ref setSize);

                printList("After successful Remove " + element + ": ");
                return true;
            }

            printList("After failed Remove " + element + ": ");
            return false;
        }
    }
}
