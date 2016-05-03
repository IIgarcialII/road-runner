using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SharedHashSet
{
    class CheckElementsRange
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
        public CheckElementsRange(MyHashSet<int> set, int min, int max, int repetitions)
        {
            this.set = set;
            this.min = min;
            this.max = max;
            this.repetitions = repetitions;
        }

        public void Run()
        {
            Random rnd = new Random(System.DateTime.Now.Millisecond);
            int element;
            int op;
            bool result;
            for (int i = 0; i < repetitions; i++)
            {
                element = rnd.Next(max - min) + min;
                op = rnd.Next(3);

                if (op == 0)
                {
                    // Check for element
                    result = set.Contains(element);
                }
                else if (op == 1)
                {
                    // Add element
                    result = set.Add(element);
                }
                else if (op == 2)
                {
                    // Remove element
                    result = set.Remove(element);
                }
            }
        }
    }
}
