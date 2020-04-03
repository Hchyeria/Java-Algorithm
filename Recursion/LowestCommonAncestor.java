package Recursion;

// Given two nodes in a binary tree, find their lowest common ancestor.

// Assumptions:
// 1. There is no parent pointer for the nodes in the binary tree.
// 2. The given two nodes are guaranteed to be in the binary tree.

import BinaryTree.TreeNode;

public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    // Time complexity: O(n)
    // Space complexity: O(n), when the binary tree is highly unbalanced

    // Follow up from Facebook: What if the binary tree contains billions of nodes?
    // Answer:
    // Map-Reduce, with the following situations:
    // 1. Both a and b are very shallow;
    // 2. One of them is very shallow: BFS to determine which one, and map
    //    reduce to find which machine contains the other, then LCA;
    // 3. Both of them are very deep: map reduce LCA.

    // Follow up: The given two nodes are not guaranteed to be in the binary tree
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode one, TreeNode two) {
        if (root == null || root == one || root == two) {
            return root;
        }
        if (find(root, one) && find(root, two)) {
            return lowestCommonAncestor(root, one, two);
        }
        return null;
    }

    private boolean find(TreeNode root, TreeNode node) {
        if (root == null || node == null) {
            return false;
        }
        if (root == node) {
            return true;
        }
        return find(root.left, node) || find(root.right, node);
    }

    public TreeNode lowestCommonAncestor22(TreeNode root, TreeNode one, TreeNode two) {
        if (root == null || root == one || root == two) {
            return root;
        }
        TreeNode node = lowestCommonAncestor(root, one, two);
        if (node == one) {
            return lowestCommonAncestor(node, two, two) == null ? null : node;
        } else if (node == two) {
            return lowestCommonAncestor(node, one, one) == null ? null : node;
        }
        return node;
    }

}
