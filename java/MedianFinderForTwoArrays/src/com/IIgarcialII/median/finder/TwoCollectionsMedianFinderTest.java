package com.IIgarcialII.median.finder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collection;

public class TwoCollectionsMedianFinderTest {
    TwoCollectionsMedianFinder finder;
    Collection first, second;

    @Before
    public void setUp() throws Exception {
        this.finder = new TwoCollectionsMedianFinder();
        this.first = new ArrayList();
        this.second = new ArrayList();
    }

    @Test
    public void testFindWithANullCollection() {
        first = null;
        second.add(1);
        final long actual = finder.find(first, second);
        final long expected = 1;
        Assert.assertEquals("Regardless that first is null we should return the second collection median value", actual, expected);
    }

    @Rule
    public final ExpectedException thrown = ExpectedException.none();


    @Test
    public void testFindWithBothNullCollections() {
        first = null;
        second = null;
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Both Collections are null.");
        final long actual = finder.find(first, second);
    }

    @Test
    public void testFindWithBothEmptyCollections() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Both Collections have no values.");
        final long actual = finder.find(first, second);
    }

    @Test
    public void testFindWithAnEmptyCollection() {
        second.add(1);
        final long actual = finder.find(first, second);
        final long expected = 1;
        Assert.assertEquals("Regardless that first has no values we should return the second collection median value", actual, expected);
    }

    @Test
    public void testFindWithBothSizeOneCollections() {
        first.add(1);
        second.add(2);
        final long actual = finder.find(first, second);
        final long expected = 1;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindWithBothCollectionsHavingTheSameValues() {
        first.add(1);
        second.add(1);
        final long actual = finder.find(first, second);
        final long expected = 1;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFindWithBothCollectionsHavingTheSameValuesTwo() {
        first.add(1);
        first.add(2);
        second.add(1);
        second.add(2);
        final long actual = finder.find(first, second);
        final long expected = 1;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testFind() {
        first.add(1);
        first.add(2);
        first.add(3);
        second.add(4);
        second.add(5);
        final long actual = finder.find(first, second);
        final long expected = 3;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDoubleCallToFind() {
        first.add(1);
        first.add(2);
        first.add(3);
        second.add(4);
        second.add(5);
        long actual = finder.find(first, second);
        actual = finder.find(first, second);
        final long expected = 3;
        Assert.assertEquals(actual, expected);
    }
}