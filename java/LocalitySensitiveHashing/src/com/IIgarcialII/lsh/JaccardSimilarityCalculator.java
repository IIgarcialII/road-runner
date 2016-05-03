/**
 * 
 */
package com.IIgarcialII.lsh;

/**
 * @author Alejandro
 *
 */
public class JaccardSimilarityCalculator {
	
	public double[][] calculate(final boolean[][] matrix) {
		double[][] result = new double[matrix[0].length][matrix[0].length];
		boolean[] docOne = null, docTwo = null;
		for(int i = 0; i < result.length; ++i) {
			for(int j = i + 1; j < result.length; ++j) {
				docOne = getShinglesArray(matrix, i);
				docTwo = getShinglesArray(matrix, j);
				result[i][j] = computeJaccard(docOne, docTwo);
				result[j][i] = result[i][j];
			}
		}
		return result;
	}
	
	private boolean[] getShinglesArray(final boolean[][] matrix, int docIndex) {
		final boolean[] result = new boolean[matrix.length];
		for(int i = 0; i < matrix.length; ++i) {
			result[i] = matrix[i][docIndex];
		}
		return result;
	}
	
	private double computeJaccard(boolean[] docOne, boolean[] docTwo) {
		double intersect = 0;
		double union = 0;
		for(int i = 0; i < docOne.length; ++i) {
			if(docOne[i] == docTwo[i] && (docOne[i] != false || docTwo[i] != false)) {
				intersect++;
				union++;
			}else if(docOne[i] != false || docTwo[i] != false) {
				union++;
			}
		}
		return union != 0 ? intersect/union : 0;
	}
}
