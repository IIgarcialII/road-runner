package com.github.IIgarcialII;

/**
 * Created by Alejandro Garcia on 4/27/16.
 */
public class HanoiTower {

    // move n smallest discs from one pole to another, using the temp pole
    public static void hanoi(int n, String from, String temp, String to) {
        if (n == 0) return;
        hanoi(n-1, from, to, temp);
        System.out.println("Move disc " + n + " from " + from + " to " + to);
        hanoi(n-1, temp, from, to);
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        System.out.println("Moving " + N + " disk(es)");
        hanoi(N, "A", "B", "C");
    }
}
