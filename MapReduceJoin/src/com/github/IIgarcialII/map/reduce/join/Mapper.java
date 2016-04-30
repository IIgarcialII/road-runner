package com.github.IIgarcialII.map.reduce.join;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alejandro Garcia on 4/30/16.
 */
public class Mapper {

    private final Grouper grouper;

    public Mapper(Grouper grouper) {
        this.grouper = grouper;
    }

    /**
     *
     * @param table
     * @return
     */
    public void map(final List<Pair<Integer,Integer>> table, final String tableName) {
        // the key is the right value of table, the value is a set of <tableName, left value>
        final List<Pair<Integer, Pair<String,Integer>>> keyPairs = new ArrayList<>();

        for (final Pair<Integer, Integer> p: table) {
            keyPairs.add(new Pair<Integer, Pair<String, Integer>>(p.getValue(), new Pair<String, Integer>(tableName,p.getKey())));
        }
        grouper.group(keyPairs);
    }

}
