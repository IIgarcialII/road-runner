package com.IIgarcialII.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * To work is has to be an DAG
 */
public class TopologicalSort {

    private boolean[] visited;
    private boolean[] processed;
    private int[] parent;

    private final Graph g;

    public final List<Integer> sorted;

    public TopologicalSort(final Graph g) {
        this.g = g;
        this.visited = new boolean[g.adjacencyList.size()];
        this.processed = new boolean[g.adjacencyList.size()];
        this.parent = new int[g.adjacencyList.size()];
        this.sorted = new ArrayList<Integer>();
    }

    public void sort() {
        for (int index = 0; index < g.adjacencyList.size() && g.adjacencyList.get(index) != null; ++index) {
            visited[index] = true;
            if(!processed[index]) {
                visit(index);
            }
        }
    }

    private void visit(final int pos) {
        final List<Integer> current = g.adjacencyList.get(pos);
        Integer next = null;
        for (int i = 0; i < current.size(); i++) {
            next = current.get(i);
            if(!visited[next]){
                visited[next] = true;
                parent[next] = pos;
                visit(next);
                // lets find cycles
            }else if((!processed[next] && (parent[pos] != next) || g.directed)){
                boolean dag = hasACycle(pos,next);
                if(!dag){
                 System.exit(1);
                }
            }
        }
        sorted.add(pos);
        processed[pos] = true;
    }

    private boolean hasACycle(int from, int to) {
        //Finding Cycles
        if(visited[to] && parent[from] != to) {
            System.out.println("NOT A DAG");
            // findPath(from, to); enumerate all nodes that form the cycle
            //terminate after finding first cycle
            return false;
        }
        return true;
    }

    public static void main(String[] args){
        Graph g = new Graph(11, true);
        g.insert(0,1);
        g.insert(0,2);

        g.insert(1,4);
        g.insert(1,6);

        g.insert(2,5);

        g.insert(3,0);
        g.insert(3,9);
        g.insert(9,10);

        TopologicalSort ts = new TopologicalSort(g);
        ts.sort();
        for (int current : ts.sorted) {
            System.out.print(current + " ");
        }
    }

}
