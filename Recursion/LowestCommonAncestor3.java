package Recursion;

// Given K nodes in a binary tree, find their lowest common ancestor.

// Assumptions:
// 1. K >= 2
// 2. There is no parent pointer for the nodes in the binary tree
// 3. The given K nodes are guaranteed to be in the binary tree


import BinaryTree.TreeNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LowestCommonAncestor3 {
    public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
        Set<TreeNode> set = new HashSet<>(nodes);
        return helper(root, set);
    }

    private TreeNode helper(TreeNode root, Set<TreeNode> set) {
        if (root == null || set.contains(root)) {
            return root;
        }
        TreeNode left = helper(root.left, set);
        TreeNode right = helper(root.right, set);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    // Time complexity: O(n)
    // Space complexity: O(n), when the binary tree is highly unbalanced
}
