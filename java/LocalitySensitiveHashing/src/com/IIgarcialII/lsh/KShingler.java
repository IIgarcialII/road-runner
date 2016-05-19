package com.IIgarcialII.lsh;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Shingles a document
 *
 * @author Alejandro Garcia
 */
final class KShingler {

        private static class ShinglerSingleton {
                private static final KShingler INSTANCE = new KShingler();
        }

        /**
         * Returns the singleton instance of KShingler
         *
         * @return KShingler
         */
        protected static KShingler getInstance() {
                return ShinglerSingleton.INSTANCE;
        }

        private KShingler() {
        }

        /**
         * Create a shingles set of length k for a given document
         *
         * @param k        int
         * @param document String
         * @return Set
         */
        protected Set<String> shingle(final int k, final String document) {
                final Map<String, Boolean> keyShingle = new HashMap<String, Boolean>();
                final char[] chars = document.toCharArray();
                String currentShingle = null;
                for (int i = 0; i < chars.length - (k - 1); i++) {
                        if (i + 1 <= chars.length - 1) {
                                currentShingle = chars[i] + "" + chars[i + 1];
                        }
                        keyShingle.put(currentShingle, true);
                }
                return keyShingle.keySet();
        }
}
