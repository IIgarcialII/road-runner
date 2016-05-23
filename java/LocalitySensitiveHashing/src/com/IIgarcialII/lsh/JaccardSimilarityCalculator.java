/**
 *
 */
package com.IIgarcialII.lsh;

/**
 * @author Alejandro Garcia
 */
public class JaccardSimilarityCalculator {

        /**
         * @param matrix
         * @return
         */
        public double[][] calculate(final boolean[][] matrix) {
                // documents number are in rows
                double[][] result = new double[matrix.length][matrix.length];
                // shingles boolean array
                boolean[] docOne = null, docTwo = null;
                for (int i = 0; i < result.length; ++i) {
                        docOne = matrix[i];
                        for (int j = i + 1; j < result.length; ++j) {
                                docTwo = matrix[j];
                                result[i][j] = computeJaccard(docOne, docTwo);
                                // symmetric matrix
                                result[j][i] = result[i][j];
                        }
                }
                return result;
        }

        /**
         * @param docOne
         * @param docTwo
         * @return
         */
        private double computeJaccard(boolean[] docOne, boolean[] docTwo) {
                double intersect = 0;
                double union = 0;
                for (int i = 0; i < docOne.length; ++i) {
                        if (docOne[i] && docTwo[i]) {
                                intersect++;
                                union++;
                        } else if (docOne[i] || docTwo[i]) {
                                union++;
                        }
                }
                return union != 0 ? intersect / union : 0;
        }
}
