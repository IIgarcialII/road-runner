package com.github.IIgarcialII.map.reduce.join;

import java.util.*;

/**
 * Naive implementation of table join operation, using a "map reduce" round wannabe.
 * Solution is not even parallelized.
 * 2 Table with 2 columns only.
 *
 * Created by Alejandro Garcia on 1/8/16.
 */
public class Join {


    public static void main(final String[] args) {

        final Join j = new Join();

        final List<Pair<Integer,Integer>> r = new ArrayList<>();
        r.add(new Pair<Integer, Integer>(1,2));
        r.add(new Pair<Integer, Integer>(3,2));
        r.add(new Pair<Integer, Integer>(4,5));


        final List<Pair<Integer,Integer>> s = new ArrayList<>();
        s.add(new Pair<Integer, Integer>(6,2));
        s.add(new Pair<Integer, Integer>(7,5));
        s.add(new Pair<Integer, Integer>(8,5));
        s.add(new Pair<Integer, Integer>(10,9));

        // Let's say the "system :P" creates as set of mappers
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Mapper mone =  new Mapper(new Grouper());
                mone.map(r, "R");
            }
        }).run();

        final Mapper mtwo =  new Mapper(new Grouper());
        mtwo.map(s, "S");

        final Reducer reducer = Reducer.getInstance();
        final List<Row> joinedTable = reducer.reduce();

        for (final Row row : joinedTable) {
            System.out.println(row.toString());
        }
    }
}
