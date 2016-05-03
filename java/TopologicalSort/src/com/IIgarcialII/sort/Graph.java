package com.IIgarcialII.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ugarc_000 on 8/6/2014.
 */
public class Graph {

    public List<List<Integer>> adjacencyList;
    public boolean directed;

    public Graph(final int size, final boolean directed) {
        this.directed = directed;
        this.adjacencyList = new ArrayList<List<Integer>>(size);
        for (int i = 0; i < size; i++) {
            this.adjacencyList.add(null);
        }
    }

    public void insert(Integer from, Integer to) {
        if(from >  adjacencyList.size() || to > adjacencyList.size()) {
            // make graph bigger!
        }
        final List<Integer> currentFromList = adjacencyList.get(from);
        if(currentFromList == null) {
            adjacencyList.set(from, new ArrayList<Integer>());
        }
        final List<Integer> currentToList = adjacencyList.get(to);
        if(currentToList == null) {
            adjacencyList.set(to, new ArrayList<Integer>());
        }
        adjacencyList.get(from).add(to);
        if(!directed) {
            adjacencyList.get(to).add(from);
        }
    }
}
