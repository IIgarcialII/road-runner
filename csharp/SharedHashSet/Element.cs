using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace SharedHashSet
{
    class Element<T>
    {
        private readonly T value;
        private readonly uint key = 0;
        private ElementState<T> state;

        public Element()
            : this(default(T), 0)
        {
        }

        public override string ToString()
        {
            bool sentinel;
            uint revKey = Util.RevertKey(key, out sentinel);
            if (sentinel)
            {
                return "(" + revKey + ")";
            }
            return "[" + value + "]";
        }

        public Element(T value, uint key)
        {
            this.value = value;
            this.key = key;
            state = new ElementState<T>(false, null);
        }

        /// <summary>
        /// Try to set the state to newState if state equals oldState. Return succeess//fail
        /// </summary>
        /// <param name="oldState"></param>
        /// <param name="newState"></param>
        /// <returns></returns>
        private bool CasState(ElementState<T> oldState, ElementState<T> newState)
        {
            ElementState<T> prevState = Interlocked.CompareExchange<ElementState<T>>(ref state, newState, oldState);
            return (oldState == prevState);
        }

        /// <summary>
        /// Flag this element as deleted
        /// </summary>
        public void FlagAsDeleted()
        {
            ElementState<T> newState;
            ElementState<T> oldState;
            do
            {
                oldState = state;
                newState = new ElementState<T>(true, oldState.Next);
            }
            while (!CasState(oldState, newState));
        }

        /// <summary>
        /// Try just once to actually remove our child (the next element)
        /// </summary>
        /// <param name="child"></param>
        public void TryDeleteChild(Element<T> child)
        {
            ElementState<T> oldState = state;
            if (oldState.Next == child)
            {
                ElementState<T> newState = new ElementState<T>(oldState.IsDeleted, child.state.Next);
                CasState(oldState, newState);
            }
        }

        /// <summary>
        /// Get the next undeleted child
        /// </summary>
        /// <returns></returns>
        public Element<T> GetNext()
        {
            Element<T> node = state.Next;
            while (node != null && node.state.IsDeleted)
            {
                TryDeleteChild(node);
                node = state.Next;
            }
            return node;
        }

        /// <summary>
        /// Try to insert a new child element
        /// </summary>
        /// <param name="newNode"></param>
        /// <param name="successor"></param>
        /// <returns></returns>
        public bool TryInsertChild(Element<T> newNode, Element<T> successor)
        {
            ElementState<T> oldState = state;

            if (!oldState.IsDeleted && oldState.Next == successor)
            {
                ElementState<T> newState = new ElementState<T>(false, newNode);
                newNode.state = new ElementState<T>(false, oldState.Next);
                return CasState(oldState, newState);
            }

            return false;
        }

        public bool IsLessThanKey(uint otherKey)
        {
            return key < otherKey;
        }

        public bool HasSameKey(uint otherKey)
        {
            return key == otherKey;
        }

        public bool IsDeleted()
        {
            return state.IsDeleted;
        }
    }
}
