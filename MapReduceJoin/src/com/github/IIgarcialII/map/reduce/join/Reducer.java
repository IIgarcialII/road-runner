package com.github.IIgarcialII.map.reduce.join;

import java.util.*;

/**
 * Just a smple and naive reducer implementation
 * Created by Alejandro Garcia on 4/30/16.
 */
public class Reducer {

    // both data structures has synchronized access
    private final Map<Integer, List<Pair<String, Integer>>> data;
    private final List<Row> rows;

    // Init Reducer singleton, lazy thread safe
    public static class Initializer {
        public final static Reducer INSTANCE = new Reducer();

    }

    public static Reducer getInstance() {
        return Initializer.INSTANCE;
    }

    private Reducer() {
        this.rows = Collections.synchronizedList(new ArrayList<Row>());
        this.data = Collections.synchronizedMap(new HashMap<Integer, List<Pair<String, Integer>>>());
    }

    /**
     * Adding to data all values from grouper
     * @param groupedData
     */
    public void add(final Map<Integer, List<Pair<String, Integer>>> groupedData) {
        for (final Integer key : groupedData.keySet()) {
            if(!data.containsKey(key)) {
                data.put(key, new ArrayList<Pair<String, Integer>>());
            }
            data.get(key).addAll(groupedData.get(key));
        }
    }

    /**
     * Very naive, and yikes so complex :(
     * @return
     */
    public List<Row> reduce() {
        rows.clear();
        for (final Integer key: data.keySet()) {
            // for the key, group values per table
            final Map<String, List<Integer>> tableNameValue = new HashMap<>();
            for (final Pair<String, Integer> p : data.get(key)) {
                final String tableName = p.getKey();
                if(!tableNameValue.containsKey(tableName)) {
                    tableNameValue.put(tableName, new ArrayList<Integer>());
                }
                tableNameValue.get(tableName).add(p.getValue());
            }

            if(!tableNameValue.isEmpty() && tableNameValue.size() == 2) {
                final Iterator<String> keys = tableNameValue.keySet().iterator();
                final String tableA = keys.next();
                final String tableB = keys.next();
                for (final Integer i : tableNameValue.get(tableA)) {
                    for (final Integer j : tableNameValue.get(tableB)) {
                        rows.add(new Row(i, key, j));
                    }
                }
            }
        }
        return rows;
    }
}
