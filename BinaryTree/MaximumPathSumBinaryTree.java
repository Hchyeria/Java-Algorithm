package BinaryTree;

// Given a binary tree in which each node contains an integer number.

// Find the maximum possible sum from one leaf node to another leaf node.
// If there is no such path available, return Integer.MIN_VALUE.

public class MaximumPathSumBinaryTree {
    private int maxVal = Integer.MIN_VALUE;
    public int search(TreeNode root) {
        helper(root);
        return maxVal;
    }

    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        // only a node that have both left and right child
        // then there is a available path from one leaf node to another
        if (root.left != null && root.right != null) {
            // then we compare max to this path sum value
            maxVal = Math.max(maxVal, root.val + left + right);
            // when we return to upper level
            // we should return the larger sum path
            return root.val  + Math.max(left, right);
        }
        return root.left == null ? root.val + right : root.val + left;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n) worse case when binaryTree is highly unbalanced
}
