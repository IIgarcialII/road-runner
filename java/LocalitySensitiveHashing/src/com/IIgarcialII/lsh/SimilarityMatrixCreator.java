package com.IIgarcialII.lsh;

/**
 * @author Alejandro Garcia
 */
public class SimilarityMatrixCreator {

        private final int numberOfHashFunctions;

        public SimilarityMatrixCreator(int numberOfHashFunctions) {
                this.numberOfHashFunctions = numberOfHashFunctions;
        }

        /**
         * Generates a similarity matrix
         *
         * @param matrix
         * @return
         */
        public int[][] create(final boolean[][] matrix) {
                final int[][] result = new int[matrix.length][numberOfHashFunctions];
                // int result to in max value, row docs and cols hash functions
                for (int row = 0; row < result.length; ++row) {
                        for (int col = 0; col < numberOfHashFunctions; ++col) {
                                result[row][col] = Integer.MAX_VALUE;
                        }
                }

                // number of buckets == the total number of shingles
                final int numberOfBuckets = matrix[0].length;
                int[] hashFunctionsCurrentValue = null;

                // rows == docs
                for (int row = 0; row < matrix.length; ++row) {
                        // col = shingles in boolean matrix
                        for (int col = 0; col < matrix[0].length; ++col) {
                                hashFunctionsCurrentValue = calculateHashFunctions(col + 1, numberOfBuckets);
                                if (matrix[row][col]) {
                                        for (int resultRow = 0; resultRow < numberOfHashFunctions; ++resultRow) {
                                                if (result[row][resultRow] > hashFunctionsCurrentValue[resultRow]) {
                                                        result[row][resultRow] = hashFunctionsCurrentValue[resultRow];
                                                }
                                        }
                                }
                        }
                }
                return result;
        }

        /**
         * Generate NUMBER_OF_HASH_FUNCTIONS
         *
         * @param colNumber
         * @param numberOfBuckets
         * @return
         */
        private int[] calculateHashFunctions(final int colNumber, final int numberOfBuckets) {
                final int[] result = new int[numberOfHashFunctions];
                int h = colNumber;
                result[0] = (h ^ 1) % numberOfBuckets;
                for (int i = 1; i < numberOfHashFunctions; ++i) {
                        result[i] = (h ^ (i + 1)) % numberOfBuckets;
                }
                return result;
        }

}
