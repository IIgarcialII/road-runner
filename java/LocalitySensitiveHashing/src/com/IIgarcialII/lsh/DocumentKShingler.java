package com.IIgarcialII.lsh;

import java.util.Set;
import java.util.TreeSet;

/**
 * 2 size Shingles a document
 * 
 * @author Alejandro
 *
 */
public final class DocumentKShingler implements Shingler {
	
	private final int kShingle;
	
	public DocumentKShingler(final int kShingle) {
		this.kShingle = kShingle;
	}
	
	/* (non-Javadoc)
	 * @see test.Shingler#shingle(java.lang.String)
	 */
	@Override
	public Set<String> shingle(final String document) {
		final Set<String> result = new TreeSet<String>();
		final char[] chars = document.toCharArray();
		String currentShingle = null;
		for(int i = 0; i < chars.length - (kShingle-1); i++) {
			if(i + 1 <= chars.length - 1) {
				currentShingle = chars[i] + "" + chars[i+1];
			}
			result.add(currentShingle);
		}
		return result;
	}

}
