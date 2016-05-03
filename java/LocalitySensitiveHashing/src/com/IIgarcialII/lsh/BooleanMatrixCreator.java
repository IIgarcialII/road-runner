/**
 * 
 */
package com.IIgarcialII.lsh;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Alejandro
 *
 */
public final class BooleanMatrixCreator {
	
	public boolean[][] createMatrix(final Map<Integer,Set<String>> documentShingles) {
		final Set<String> allShingles = allShinglesSet(documentShingles);
		final int maxNumberOfShingles = allShingles.size();		
		final boolean[][] result = new boolean[maxNumberOfShingles][documentShingles.size()];
		final Map<String, Integer> allShinglesIndexes = allShinglesIndex(documentShingles, allShingles);
		//place documents in cols and shingles by row
		//Shingles are alphabetically ordered
		String currentShingle = null;
		int shingleIndex = 0;
		for(int docIndex = 0; docIndex < documentShingles.size(); ++docIndex) {
			for(final Iterator<String> shingles = documentShingles.get(docIndex).iterator(); shingles.hasNext();) {
				currentShingle = shingles.next();
				shingleIndex = allShinglesIndexes.get(currentShingle);
				result[shingleIndex][docIndex] = true;
			}
		}
		return result;
	}
	
	
//	private Map<String,List<Integer>> getShingleInDocs(final Map<Integer,Set<String>> documentShingles) {
//		final Map<String,List<Integer>> result = new HashMap<String, List<Integer>>();
//		String currentShingle = null;
//		for(int i = 0; i < documentShingles.size(); ++i) {
//			for(final Iterator<String> shingles = documentShingles.get(i).iterator(); shingles.hasNext();) {
//				currentShingle = shingles.next();
//				if(result.get(currentShingle) == null) {
//					result.put(currentShingle, new ArrayList<Integer>());
//				}
//				result.get(currentShingle).add(i);
//			}
//		}
//		
//		return result;
//	}
	
//	private int getMaxNumberOfShingles(final Map<Integer,Set<String>> documentShingles) {
//		int result = 0;
//		int currentSize = 0;
//		for(int i = 0; i < documentShingles.size(); ++i) {
//			currentSize = documentShingles.get(i).size();
//			if(currentSize > result) {
//				result = currentSize;
//			}
//		}
//		return result;
//	}
	
	private Set<String> allShinglesSet(final Map<Integer,Set<String>> documentShingles) {
		final Set<String> result = new TreeSet<String>();
		for(int i = 0; i < documentShingles.size(); ++i) {
			result.addAll(documentShingles.get(i));
		}
		return result;
	}
	
	private Map<String,Integer> allShinglesIndex(final Map<Integer,Set<String>> documentShingles, final Set<String> allShingles) {
		final Map<String,Integer> result = new HashMap<String, Integer>();
		int counter = 0;
		String currentShingle = null;
		for(final Iterator<String> it = allShingles.iterator(); it.hasNext();) {
			currentShingle = it.next();
			result.put(currentShingle, counter++);
		}
		return result;
	}

}
