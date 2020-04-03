package Graph;

// Make a deep copy of an undirected graph, there could be cycles in the original graph

import java.util.*;

public class DeepCopyUndirectedGraph {
    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    // Solution 1: BFS
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Node newHead = new Node(node.val);
        if (node.neighbors == null || node.neighbors.size() == 0) {
            return newHead;
        }
        Map<Node, Node> map = new HashMap<>();
        map.put(node, newHead);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            Node newNode = map.get(temp);
            for (Node n : temp.neighbors) {
                Node newN = map.get(n);
                if (newN == null) {
                    newN = new Node(n.val);
                    map.put(n, newN);
                    queue.offer(n);
                }
                newNode.neighbors.add(newN);
            }
        }
        return newHead;
    }

    // Time Complexity: O(|V|+|E|)
    // Space Complexity: O(|V|+|E|)

    // Solution 2: DFS
    public Node cloneGraph2(Node node) {
        if (node == null) {
            return null;
        }
        Node newHead = new Node(node.val);
        if (node.neighbors == null || node.neighbors.size() == 0) {
            return newHead;
        }
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (node == null) {
            return null;
        }
        Node newNode = map.get(node);
        // the node has been visited, so we return directly , no need to for-loop neighbors
        if (newNode != null) {
            return newNode;
        }

        newNode = new Node(node.val);
        map.put(node, newNode);


        for (Node n : node.neighbors) {
            newNode.neighbors.add(dfs(n, map));
        }
        return newNode;
    }
    // Time complexity: O(|V|+|E|).
    // Space complexity: O(|V|), because of call-stack.

    // Discuss: DFS vs BFS ?
}
