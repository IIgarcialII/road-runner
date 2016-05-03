package com.github.IIgarcialII.map.reduce.join;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Alejandro Garcia on 4/30/16.
 */
public class Grouper {

    private final Reducer reducer;

    public Grouper() {
        this.reducer = Reducer.getInstance();
    }

    /**
     *
     * @param fromMapper
     * @return
     */
    public void group(List<Pair<Integer, Pair<String,Integer>>> fromMapper) {
        // the key is the right value of table, the value is a set of <tableName, left value>
        final Map<Integer, List<Pair<String, Integer>>> kvs = new HashMap<>();

        for (final Pair<Integer,  Pair<String,Integer>> p: fromMapper) {
            if(!kvs.containsKey(p.getKey())) {
                kvs.put(p.getKey(),new ArrayList<Pair<String, Integer>>());
            }
            final List<Pair<String, Integer>> tablePairs = kvs.get(p.getKey());
            tablePairs.add(p.getValue());
        }
        reducer.add(kvs);
    }
}
