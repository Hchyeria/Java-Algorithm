package Graph;

// Determine if an undirected graph is bipartite[.baɪ'pɑr.taɪt]. A bipartite graph is one in which
// the nodes can be divided into two groups such that no nodes have direct edges to
// other nodes in the same group.

import java.lang.reflect.Array;
import java.util.*;

public class BipartiteGraph {
    // Solution 1: BFS
    public boolean isBipartite(int[][] graph) {
        if (graph == null) {
            return false;
        }
        int n = graph.length;
        // also can use the HashMap
        int[] color = new int[n];
        for (int i = 0; i < n; ++i) {
            color[i] = -1;
        }
        // be care about that we not guarantee the node 0 is root
        // maybe they don't have any neighbors
        // like this {{},{2,4,6},{1,4,8,9}} case
        // and maybe there have many graph group not only one
        // so we need to iterate all
        int index = 0;
        for (int[] node : graph) {
            if (!helper(graph, color, index++)) {
                return false;
            }
        }

        return true;
    }

    private boolean helper(int[][] graph, int[] color, int index) {
        if (color[index] >= 0) {
            return true;
        }
        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(new GraphNode(index));
        color[index] = 0;
        while (!queue.isEmpty()) {
            GraphNode p = queue.poll();
            int val = p.val;
            for (int neighbor : graph[val]) {
                if (color[neighbor] < 0) {
                    color[neighbor] = color[val] ^ 1;
                    queue.offer(new GraphNode(neighbor));
                } else {
                    if (color[neighbor] != (color[val] ^ 1)) {
                        return false;
                    }
                }
            }

        }
        return true;
    }
    // Time complexity is O(E)
    // Space complexity is O(n)

    // Solution 2: DFS
    public boolean isBipartite2(int[][] graph) {
        if (graph == null) {
            return false;
        }
        int n = graph.length;
        int[] colors = new int[n];
        int index = 0;
        for (int[] node : graph) {
            if (colors[index] == 0) {
                if (!dfs(graph, index, colors, 1)) {
                    return false;
                }
            }
            index++;
        }

        return true;
    }

    private boolean dfs(int[][] graph, int index, int[] colors, int color) {
        // (colors[index] != 0 && colors[index] != color)
        if (colors[index] == -color) {
            return false;
        }
        colors[index] = color;
        for (int neighbor : graph[index]) {
            if (colors[neighbor] == 0) {
                // recursion to implement DFS
                if (!dfs(graph, neighbor, colors, -color)) {
                    return false;
                }
            } else if (colors[neighbor] != -color) {
                return false;
            }
        }
        return true;
    }

    // Time complexity is O(E)
    // Space complexity is O(n)

    // Notice ~0 == -1
    // ~1 == -2
    public static void main(String args[]) {
        BipartiteGraph bipartiteGraph = new BipartiteGraph();
        int[][] test = new int[][] {{2,4},{2,3,4},{0,1},{1},{0,1},{7},{9},{5},{},{6},{12,14},{},{10},{},{10},{19},{18},{},{16},{15},{23},{23},{},{20,21},{},{},{27},{26},{},{},{34},{33,34},{},{31},{30,31},{38,39},{37,38,39},{36},{35,36},{35,36},{43},{},{},{40},{},{49},{47,48,49},{46,48,49},{46,47,49},{45,46,47,48}};
        System.out.println(bipartiteGraph.isBipartite(test));
    }
}
