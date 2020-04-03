package BinaryTree;

// Given a binary tree in which each node contains an integer number.

// Find the maximum possible sub-path sum (both the starting and ending node
// of the sub-path should be on the same path from root to one of the leaf nodes,
// and the sub-path is allowed to contain only one node).

public class MaximumPathSumBinaryTree3 {
    public int maximum(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int[] globalMax = { Integer.MIN_VALUE };
        helper(root, 0, globalMax);
        return globalMax[0];
    }

    private void helper(TreeNode node, int sum, int[] globalMax) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            globalMax[0] = Math.max(globalMax[0], sum + node.val);
            return;
        }
        helper(node.left, sum + node.val, globalMax);
        helper(node.right, sum + node.val, globalMax);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(height)
}
