using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SharedHashSet
{
    class Util
    {
        /// <summary>
        /// Reverse the bits in src and return them
        /// </summary>
        /// <param name="src"></param>
        /// <returns></returns>
        public static uint ReverseBits(uint src)
        {
            uint ret = 0;
            uint mask = 1;
            uint current_bit;
            for (int bit = 0; bit < sizeof(uint) * 8; bit++)
            {
                current_bit = (src & mask) >> bit;
                ret = (ret << 1) + current_bit;
                mask = mask << 1;
            }
            return ret;
        }

        /// <summary>
        /// Get the next highest power of two
        /// </summary>
        /// <param name="n"></param>
        /// <returns></returns>
        public static uint NextHighestPowerOfTwo(uint n)
        {
            n = n - 1;
            n = n | (n >> 1);
            n = n | (n >> 2);
            n = n | (n >> 4);
            n = n | (n >> 8);
            n = n | (n >> 16);
            n = n + 1;
            return n;
        }

        /// <summary>
        /// Transform a casual element key
        /// </summary>
        /// <param name="key"></param>
        /// <returns></returns>
        public static uint MakeRegularKey(uint key)
        {
            return ReverseBits(key | 0x80000000);
        }

        /// <summary>
        /// Transform a sentinel element key
        /// </summary>
        /// <param name="key"></param>
        /// <returns></returns>
        public static uint MakeSentinelKey(uint key)
        {
            return ReverseBits(key);
        }

        /// <summary>
        /// Find out about a transformed key if it is for sentinels or for usual elements
        ///  and return the original key
        /// </summary>
        /// <param name="key"></param>
        /// <param name="sentinel"></param>
        /// <returns></returns>
        public static uint RevertKey(uint key, out bool sentinel)
        {
            sentinel = true;
            uint revKey = Util.ReverseBits(key);
            if (revKey > 2147483648)
            {
                sentinel = false;
                revKey = revKey & 0x4FFFFFFF;
            }
            return revKey;
        }

        public static uint RemoveMostSignificantBit(uint bucket)
        {
            uint mask = 0;
            bool found1 = false;
            for (int bit = sizeof(uint) * 8 - 1; bit >= 0; bit--)
            {
                uint bitmask = (uint)(1 << bit);
                uint current_bit = (bucket & bitmask) >> bit;
                if (found1)
                {
                    mask = (mask << 1) + 1;
                }
                else
                {
                    if (current_bit==1)
                    {
                        found1 = true;
                        mask = (mask << 1);
                    }
                    else
                    {
                        mask = (mask << 1) + 1;
                    }
                }
            }
            return bucket & mask;
        }
    }
}
