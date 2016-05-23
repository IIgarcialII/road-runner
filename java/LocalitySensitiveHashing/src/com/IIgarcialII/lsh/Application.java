package com.IIgarcialII.lsh;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Alejandro Garcia
 */
public final class Application {


        /**
         * @param args
         */
        public static void main(final String[] args) {
                final String docOne = "Hello World";
                final String docTwo = "Hello and good morning my dear world";
                final String docThree = "Hello dear world";
                final String docFour = "World world hello";
                final String docSix = "This is a test";
                final String docSeven = "Hello World";
                final String docEight = "Test Hello World";

                final String[] docs = new String[]{docOne, docTwo, docThree, docFour, docSix, docSeven, docEight};

                final Map<Integer, Set<String>> docsShinglesMap = new HashMap<Integer, Set<String>>();
                final KShingler shingler = KShingler.getInstance();

                System.out.println("Shingles");
                Set<String> shingles = null;
                for (int i = 0; i < docs.length; ++i) {
                        shingles = shingler.shingle(5, docs[i].toLowerCase());
                        docsShinglesMap.put(i, shingles);
                }

                System.out.println("Boolean Matrix");
                final boolean[][] matrix = new BooleanMatrixCreator().createMatrix(docsShinglesMap);
                for (int row = 0; row < matrix.length; ++row) {
                        for (int col = 0; col < matrix[0].length; ++col) {
                                System.out.print(matrix[row][col] + "\t");
                        }
                        System.out.println();
                }

                System.out.println();

                System.out.println("Jaccard Similarity");
                // We can compute the JS because in this example all data fits in mem,
                // but if we had a much bigger data set then we shoud jump directly to min-hashing
                final double[][] jaccardDistances = new JaccardSimilarityCalculator().calculate(matrix);
                for (int row = 0; row < jaccardDistances.length; ++row) {
                        for (int col = 0; col < jaccardDistances.length; ++col) {
                                System.out.printf("%.2f\t", jaccardDistances[row][col]);
                        }
                        System.out.println();
                }
                System.out.println();

                System.out.println("Similarity Matrix");
                // Min-hashing will give a very approx result as JS using much less space
                // Here we are using the matrix form but in practice this should be an adjacency list
                // This matrix is way to sparse and we can save a lot of mem with the adjancency list
                int numberOfHasFunctions = (int) (matrix[0].length * 0.1);
                final int[][] minHashingMatrix = new SimilarityMatrixCreator(numberOfHasFunctions).create
                        (matrix);
                final double[][] simMatrix = new SimilarityMatrixCalculator().calculate(minHashingMatrix);
                for (int row = 0; row < simMatrix.length; ++row) {
                        for (int col = 0; col < simMatrix.length; ++col) {
                                System.out.printf("%.2f\t", simMatrix[row][col]);
                        }
                        System.out.println();
                }


        }

}
