package BinaryTree;

// Check if a given binary tree is balanced. A balanced binary tree is one
// in which the depths of every node’s left and right subtree differ by at most 1.

public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = getHeight(root.left);
        if (left == -1) {
            return -1;
        }

        int right = getHeight(root.right);
        if (right == -1) {
            return -1;
        }

        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}

// Time complexity is O(n).
// Space complexity is O(n), when the binary tree is highly unbalanced.
