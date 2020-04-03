package BinaryTree;

// Binary Tree Inorder Traversal

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class inOrder {
    // solution1: recursion way
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<Integer> left = inorderTraversal(root.left);
        result.addAll(left);
        result.add(root.val);
        List<Integer> right = inorderTraversal(root.right);
        result.addAll(right);
        return result;
    }
    // Time Complexity: O(n), because the recursive function is T(n) = 2⋅T(n/2) + 1
    // Space Complexity: The worst case space required is O(n),
    // and in the average case it's O(log(n)) where n is number of nodes
    // Stack might overflow

    // solution 2: iterative way, use explicit stack
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                // don't use offer(), poll() pair
                // you will meet unexpected problem
                // because  - offer at the tail
                // - poll at the head (the same with pollFirst())
                stack.offerFirst(cur);
                cur = cur.left;
            } else {
                cur = stack.pollFirst();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    // No Stack overflow

    // Solution3: Morris Traversal, use Threaded Binary Tree
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root, pre, node;
        while (cur != null) {
            if (cur.left == null) {
                result.add(cur.val);
                cur = cur.right;
            } else {
                node = cur.left;
                while (node.right != null) {
                    node = node.right;
                }
                node.right = cur;
                pre = cur;
                cur = cur.left;
                pre.left = null; // avoid the loop
            }
        }
        return result;
    }
    // Time Complexity: O(n)
    // the biggest problem lies in finding the predecessor nodes of all the nodes in the binary tree
    // Intuitively, the complexity is O(n*log(n)),
    // because to find the predecessor node for a single node related to the height of the tree.
    // But in fact, finding the predecessor nodes for all nodes only needs O(n) time
    // Because a binary Tree with n nodes has n−1 edges,
    // the whole processing for each edges up to 2 times,
    // one is to locate a node, and the other is to find the predecessor node. So the complexity is O(n).
    // Space Complexity: O(1), not use stack
    // No Stack overflow
}
