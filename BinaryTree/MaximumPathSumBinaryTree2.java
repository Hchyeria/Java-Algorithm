package BinaryTree;

// Given a binary tree in which each node contains an integer number.

// Find the maximum possible sum from any node to any node (the start
// node and the end node can be the same).

// Assumption: The root of the given binary tree is not null.

public class MaximumPathSumBinaryTree2 {
    public int maximumPath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] globalMax = { Integer.MIN_VALUE };
        helper(root, globalMax);
        return globalMax[0];
    }

    private int helper(TreeNode node, int[] globalMax) {
        if (node == null) {
            return 0;
        }
        int left = helper(node.left, globalMax);
        int right = helper(node.right, globalMax);
        left = Math.max(left, 0);
        right = Math.max(right, 0);
        globalMax[0] = Math.max(globalMax[0], left + right + node.val);
        return Math.max(left, right) + node.val;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(height)
}
