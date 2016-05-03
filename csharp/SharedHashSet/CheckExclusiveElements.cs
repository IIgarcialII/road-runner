using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Diagnostics;

namespace SharedHashSet
{
    class CheckExclusiveElements
    {
        MyHashSet<int> set;
        int min, max, repetitions;

        /// <summary>
        /// Inclusive min, exclusive max
        /// </summary>
        /// <param name="set"></param>
        /// <param name="min"></param>
        /// <param name="max"></param>
        /// <param name="repetitions"></param>
        public CheckExclusiveElements(MyHashSet<int> set, int min, int max, int repetitions)
        {
            this.set = set;
            this.min = min;
            this.max = max;
            this.repetitions = repetitions;
        }

        public void Run()
        {
            bool []state = new bool[max-min];
            for (int i = 0; i < state.Length; i++)
            {
                state[i] = false;
            }

            Random rnd = new Random(System.DateTime.Now.Millisecond);
            int element, index;
            bool result, expected;
            for (int i = 0; i < repetitions; i++)
            {
                index = rnd.Next(state.Length);
                element = index + min;
                
                // Assert current state
                expected = state[index];
                result = set.Contains(element);
                Debug.Assert(expected == result, "INCONSISTENCY!!!");

                if (state[index])
                {
                    // The element is already in there

                    // Check to see that adding it again fails
                    expected = false;
                    result = set.Add(element);
                    Debug.Assert(expected == result, "INCONSISTENCY!!!");

                    // Remove the element
                    expected = true;
                    result = set.Remove(element);
                    Debug.Assert(expected == result, "INCONSISTENCY!!!");
                    state[index] = false;
                }
                else
                {
                    // Check to see that removing it again fails
                    expected = false;
                    result = set.Remove(element);
                    Debug.Assert(expected == result, "INCONSISTENCY!!!");

                    // Check to see that adding it again works
                    expected = true;
                    result = set.Add(element);
                    Debug.Assert(expected == result, "INCONSISTENCY!!!");
                    state[index] = true;
                }

                // Assert current state
                expected = state[index];
                result = set.Contains(element);
                Debug.Assert(expected == result, "INCONSISTENCY!!!");
            }
        }
    }
}
