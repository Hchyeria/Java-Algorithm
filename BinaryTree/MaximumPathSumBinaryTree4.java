package BinaryTree;

// Given a binary tree in which each node contains an integer number.
// Find the maximum possible sub-path sum from root node to leaf node.

// Assumption: root != null

public class MaximumPathSumBinaryTree4 {
    public int maxPathSum(TreeNode root) {
        int[] globalMax = { Integer.MIN_VALUE };
        if (root == null) {
            return globalMax[0];
        }
        helper(root, 0, globalMax);
        return globalMax[0];
    }

    private void helper(TreeNode node, int sum, int[] globalMax) {
        if (node == null) {
            return;
        }
        if (sum < 0) {
            sum = node.val;
        } else {
            sum += node.val;
        }
        // this is actually pre-order traversal
        globalMax[0] = Math.max(globalMax[0], sum);
        helper(node.left, sum, globalMax);
        helper(node.right, sum, globalMax);
    }

    // from bottom to up solution
    private int helper2(TreeNode node, int[] globalMax) {
        if (node == null) {
            return 0;
        }
        int left = helper2(node.left, globalMax);
        int right = helper2(node.right, globalMax);
        left = Math.max(left, 0);
        int temp = Math.max(left, right);

        globalMax[0] = Math.max(globalMax[0], temp + node.val);
        return temp + node.val;
    }

    // Time complexity: O(n)
    // Space complexity: O(height)
}
