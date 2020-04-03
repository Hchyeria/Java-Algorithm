package BinaryTree;

// In a binary search tree, find the node containing the largest number smaller than the given target number.
// If there is no such number, return INT_MIN.

// Assumption:
// 1. The given root is not null.
// 2. There are no duplicate keys in the binary search tree

public class LargestNumberSmallerInBinarySearchTree {
    // recursion way
    public int largestSmaller(TreeNode root, int target) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        if (root.val >= target) {
            return largestSmaller(root.left, target);
        } else {
            int right = largestSmaller(root.right, target);
            return right == Integer.MIN_VALUE ? root.val : right;
        }
    }

    // Time complexity is O(n)
    // Space complexity is O(n)

    // iterative solution
    public int largestSmaller2(TreeNode root, int target) {
        int res = Integer.MIN_VALUE;
        if (root == null) {
            return res;
        }
        while (root != null) {
            if (root.val >= target) {
                root = root.left;
            } else {
                // no need to compare, because the right subTree of BST if larger than the root
                // res = Math.max(res, root.val);
                res = root.val;
                root = root.right;
            }
        }
        return res;
    }
    // Time complexity is O(n).
    // Space complexity is O(1).
}
