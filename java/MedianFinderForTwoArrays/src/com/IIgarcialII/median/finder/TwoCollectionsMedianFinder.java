package com.IIgarcialII.median.finder;

import java.util.Collection;

/**
 *
 */
public class TwoCollectionsMedianFinder {

    public long find(final Collection first, final Collection second) throws NullPointerException, IllegalArgumentException{
        if (first == null && second == null) {
            throw new NullPointerException("Both Collections are null.");
        }else if(first == null || second == null) {
            //get median in one collection
        }else if (first.isEmpty() && second.isEmpty()) {
            throw new IllegalArgumentException("Both Collections have no values.");
        }
        long result = 0;

        return result;
    }
}
