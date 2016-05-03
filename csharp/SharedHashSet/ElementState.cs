using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SharedHashSet
{
    // Immutable object containing wether the current node is deleted and what his child is
    class ElementState<T>
    {
        private readonly bool isDeleted;
        private readonly Element<T> next;

        public ElementState(bool isDeleted, Element<T> next)
        {
            this.isDeleted = isDeleted;
            this.next = next;
        }

        public bool IsDeleted
        {
            get { return isDeleted; }
        }

        public Element<T> Next
        {
            get { return next; }
        }
    }
}
