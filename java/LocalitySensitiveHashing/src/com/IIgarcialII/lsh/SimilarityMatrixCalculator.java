package com.IIgarcialII.lsh;

/**
 * @author Alejandro Garcia
 */
public class SimilarityMatrixCalculator {

        /**
         * Calculates all document min hash similarity matrix
         *
         * @param similarityMatrix
         * @return
         */
        public double[][] calculate(final int[][] similarityMatrix) {
                // squared
                double[][] result = new double[similarityMatrix.length][similarityMatrix.length];

                for (int doci = 0; doci < result.length; ++doci) {
                        for (int docj = doci + 1; docj < result.length; ++docj) {
                                result[doci][docj] = computeSimilarity(similarityMatrix[doci], similarityMatrix[docj]);
                                // puff, make it better ... what a waste
                                result[docj][doci] = result[doci][docj];
                        }
                }
                return result;
        }

        /**
         * Similarity is defined as the ratio of elements rows agree on
         *
         * @param rowi
         * @param rowj
         * @return
         */
        private double computeSimilarity(int[] rowi, int[] rowj) {
                double intersect = 0.0;
                for (int i = 0; i < rowi.length; ++i) {
                        if (rowi[i] == rowj[i]) {
                                intersect++;
                        }
                }
                return intersect / rowi.length;
        }
}
