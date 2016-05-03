package com.IIgarcialII.lsh;

/*
 * The short answer is that you XOR the value returned by String.hashCode() with 199 random numbers to generate the 199 other hash code values. Just make sure that you are using the same 199 random numbers across all the documents.
 * If you want some more detail on why this works, see http://stackoverflow.com/a/19711615/157605.
 * 
 */
public class SimilarityMatrixCalculator {
	
	public double[][] calculate(final int[][] similarityMatrix) {
		// squared
		double[][] result = new double[similarityMatrix[0].length][similarityMatrix[0].length];
		int[] docOne = null, docTwo = null;
		for(int i = 0; i < result.length; ++i) {
			for(int j = i + 1; j < result.length; ++j) {
				docOne = getValues(similarityMatrix, i);
				docTwo = getValues(similarityMatrix, j);
				result[i][j] = computeSimilarity(docOne, docTwo);
				result[j][i] = result[i][j];
			}
		}
		return result;
	}
	
	private int[] getValues(final int[][] similarityMatrix, int docIndex) {
		final int[] result = new int[similarityMatrix.length];
		for(int i = 0; i < similarityMatrix.length; ++i) {
			result[i] = similarityMatrix[i][docIndex];
		}
		return result;
	}
	
	private double computeSimilarity(int[] docOne, int[] docTwo) {
		double intersect = 0;
		for(int i = 0; i < docOne.length; ++i) {
			if(docOne[i] == docTwo[i]) {
				intersect++;
			}
		}
		return intersect / docOne.length; 
	}
}
