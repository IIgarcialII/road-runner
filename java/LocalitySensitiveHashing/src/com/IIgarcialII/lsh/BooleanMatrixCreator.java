/**
 *
 */
package com.IIgarcialII.lsh;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Alejandro
 */
final class BooleanMatrixCreator {

        protected boolean[][] createMatrix(final Map<Integer, Set<String>> documentShingles) {
                final Set<String> allShingles = allShinglesSet(documentShingles);
                final int maxNumberOfShingles = allShingles.size();
                final boolean[][] result = new boolean[maxNumberOfShingles][documentShingles.size()];
                final Map<String, Integer> allShinglesIndexes = allShinglesIndex(allShingles);
                //place documents in cols and shingles by row
                //Shingles are alphabetically ordered
                String currentShingle = null;
                int shingleIndex = 0;
                for (int docIndex = 0; docIndex < documentShingles.size(); ++docIndex) {
                        for (final Iterator<String> shingles = documentShingles.get(docIndex).iterator(); shingles.hasNext(); ) {
                                currentShingle = shingles.next();
                                shingleIndex = allShinglesIndexes.get(currentShingle);
                                result[shingleIndex][docIndex] = true;
                        }
                }
                return result;
        }

        /**
         * Returns a set of all shingles
         *
         * @param documentShingles
         * @return
         */
        private Set<String> allShinglesSet(final Map<Integer, Set<String>> documentShingles) {
                // Probably faster this way than appending all values to a set
                final Map<String, Boolean> keyShingleMap = new HashMap<String, Boolean>();
                for (final Iterator<Integer> it = documentShingles.keySet().iterator(); it.hasNext(); ) {
                        for (final Iterator<String> si = documentShingles.get(it.next()).iterator(); si.hasNext(); ) {
                                keyShingleMap.put(si.next(), true);
                        }
                }
                return keyShingleMap.keySet();
        }

        /**
         * @param allShingles
         * @return
         */
        private Map<String, Integer> allShinglesIndex(final Set<String> allShingles) {
                final Map<String, Integer> result = new HashMap<String, Integer>();
                int counter = 0;
                String currentShingle = null;
                for (final Iterator<String> it = allShingles.iterator(); it.hasNext(); ) {
                        currentShingle = it.next();
                        result.put(currentShingle, counter++);
                }
                return result;
        }

}
