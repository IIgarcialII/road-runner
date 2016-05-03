package com.IIgarcialII.lsh;

public class SimilarityMatrixCreator {
	private final static int NUMBER_OF_HASH_FUNCTIONS = 600;
	
	public int[][] create(final String[] docs, final boolean[][] matrix) {
		final int[][] result = new int[NUMBER_OF_HASH_FUNCTIONS][docs.length];
		// int result to in max value
		for(int row = 0; row < result.length; ++row) {
			for(int col = 0; col < result[0].length; ++col) {
				result[row][col] = Integer.MAX_VALUE;
			}
		}
		
		// number of buckets == the total number of shingles
		final int numberOfBuckets = matrix.length;
		int[] hashFunctionsCurrentValue = null;
		// rows == shingles
		for(int row = 0; row < matrix.length; ++row) {
			// compute hash functions
			// We make the row param non zero index
			hashFunctionsCurrentValue = calculateHashFunctions(row + 1, numberOfBuckets);
			// matrix cols == docs		
			for(int col = 0; col < docs.length; ++col) {
				if(matrix[row][col]) {
					for(int resultRow = 0; resultRow < result.length; ++resultRow) {
						if(result[resultRow][col] > hashFunctionsCurrentValue[resultRow]) {
							result[resultRow][col] = hashFunctionsCurrentValue[resultRow];
						}
					}
				}
			}
		}		
		return result;
	}
	
	// Generate NUMBER_OF_HASH_FUNCTIONS
	private int[] calculateHashFunctions(final int rowNumber, final int numberOfBuckets) {
		final int[] result = new int[NUMBER_OF_HASH_FUNCTIONS];
		int h = rowNumber;
		result[0] = (h ^ 1) % numberOfBuckets;
		for(int i = 1; i < NUMBER_OF_HASH_FUNCTIONS; ++i) {
			result[i] = (h ^ (i + 1)) % numberOfBuckets;
		}
		return result;
	}

}
