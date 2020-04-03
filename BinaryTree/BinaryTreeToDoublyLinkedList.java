package BinaryTree;

// Given a binary tree, convert it to a doubly linked list with its in-order sequence

import java.util.Deque;
import java.util.LinkedList;

public class BinaryTreeToDoublyLinkedList {
    private static class Node {
        Node left;
        Node right;
        int val;

        Node(int val) {
            this.val = val;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            Node left = this.left;
            Node right = this.right;
            String s = "" + this.val;
            while (left != null) {
                s = left.val + " <-> " + s;
                left = left.left;
            }
            while (right != null) {
                s = s + " <-> " + right.val;
                right = right.right;
            }
            return s;
        }
    }
    public Node convert(Node root) {
        if (root == null) {
            return null;
        }
        Node[] newHead = new Node[1];
        helper(root, new Node[1], newHead);
        return newHead[0];
    }

    // Solution 1: recursion
    private void helper(Node node, Node[] prev, Node[] newHead) {
        if (node == null) {
            return;
        }
        helper(node.left, prev, newHead);
        Node newNode = new Node(node.val);
        if (prev[0] == null) {
            newHead[0] = newNode;
        } else {
            prev[0].right = newNode;
            newNode.left = prev[0];
        }
        prev[0] = newNode;
        helper(node.right, prev, newHead);

    }
    // Time Complexity: O(n), in-order traversal
    // Space Complexity: O(height)

    // Solution 2: iterative
    public Node convert2(Node root) {
        if (root == null) {
            return null;
        }
        Node newHead = null;
        Node pre = null;
        Deque<Node> stack = new LinkedList<>();
        Node p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.offerFirst(p);
                p = p.left;
            } else {
                Node node = stack.pollFirst();
                if (pre == null) {
                    newHead = node;
                } else {
                    pre.right = node;
                    node.left = pre;
                }

                pre = node;
                p = node.right;
            }
        }
        return newHead;
    }

    // Time Complexity: O(n), in-order traversal
    // Space Complexity: O(n), no stack overflow


    public static void main(String[] args) {
        BinaryTreeToDoublyLinkedList binaryTreeToDoublyLinkedList = new BinaryTreeToDoublyLinkedList();
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(2);
        root.left.right = new Node(7);
        root.right.left = new Node(12);
        root.right.right = new Node(20);
        System.out.println(root);
        System.out.println(binaryTreeToDoublyLinkedList.convert2(root));
    }
}
