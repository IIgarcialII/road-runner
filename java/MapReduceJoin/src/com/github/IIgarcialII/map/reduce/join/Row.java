package com.github.IIgarcialII.map.reduce.join;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alejandro Garcia on 4/30/16.
 */
public class Row {
    private List<Integer> values;

    public Row(final Integer ...values) {
        this.values = new ArrayList<>();
        Collections.addAll(this.values, values);
    }

    public String toString() {
        String result = "";
        for (final Integer value : values) {
            result += value + " ";
        }
        return result;
    }
}
