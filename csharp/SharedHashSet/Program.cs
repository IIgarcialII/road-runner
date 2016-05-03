using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace SharedHashSet
{
    class Program
    {
        static void CheckExclusiveElements()
        {
            MyHashSet<int> set = new MyHashSet<int>();

            int numOfParallelCheckers = 50;
            int numOfRepetitions = 1000;
            int checkSize = 10;
            int currentStart = 0;
            CheckExclusiveElements[] checkers = new CheckExclusiveElements[numOfParallelCheckers];
            Thread[] threads = new Thread[checkers.Length];
            for (int i = 0; i < checkers.Length; i++)
            {
                checkers[i] = new CheckExclusiveElements(set, currentStart, currentStart + checkSize, numOfRepetitions);
                currentStart += checkSize;
                threads[i] = new Thread(checkers[i].Run);
            }

            for (int i = 0; i < threads.Length; i++)
            {
                threads[i].Start();
            }

            for (int i = 0; i < threads.Length; i++)
            {
                threads[i].Join();
            }

            Console.WriteLine("If you do not see any error messages, it means that the exclusive checkers have finished ok");
        }

        static void CheckElementsRange()
        {
            MyHashSet<int> set = new MyHashSet<int>();

            int numOfParallelCheckers = 50;
            int numOfRepetitions = 1000;
            int checkSize = 10;
            int currentStart = 0;
            CheckElementsRange[] checkers = new CheckElementsRange[numOfParallelCheckers];
            Thread[] threads = new Thread[checkers.Length];
            for (int i = 0; i < checkers.Length; i++)
            {
                checkers[i] = new CheckElementsRange(set, currentStart, currentStart + checkSize, numOfRepetitions);
                threads[i] = new Thread(checkers[i].Run);
            }

            for (int i = 0; i < threads.Length; i++)
            {
                threads[i].Start();
            }

            for (int i = 0; i < threads.Length; i++)
            {
                threads[i].Join();
            }

            Console.WriteLine("If you do not see any error messages, it means that the checkers have finished ok");
        }

        // Insipired from:
        //  - http://www.cs.purdue.edu/homes/jv/events/TiC06/B-SLIDES/mh1.pdf
        //  - http://www.boyet.com/Articles/LockFreeLinkedList3.html
        static void Main(string[] args)
        {
            CheckExclusiveElements();
            CheckElementsRange();
        }
    }
}
