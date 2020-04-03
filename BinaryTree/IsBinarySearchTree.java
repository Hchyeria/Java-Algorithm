package BinaryTree;

// Determine if a given binary tree is binary search tree.

import java.util.LinkedList;

public class IsBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean helper(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.val < min || root.val > max) {
            return false;
        }
        // when case [-2147483648, -2147483648], should return false
        if (root.val == Integer.MIN_VALUE && root.left != null
                || root.val == Integer.MAX_VALUE && root.right != null) {
            return false;
        }
        // root.val >= min && root.val <= max
        // when case [-1, -1], should return false
        return helper(root.left, min, root.val - 1) && helper(root.right, root.val + 1, max);
    }
    // Time complexity is O(n).
    // Space complexity is O(n), worse case.


    // in-order traversal to guarantee the monotonically increasing order
    public boolean isValidBST2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root, pre = null;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            } else {
                cur = stack.pollFirst();
                assert cur != null;
                // notice >= 
                if (pre != null && pre.val >= cur.val) {
                    return false;
                }
                pre = cur;
                cur = cur.right;

            }
        }

        return true;
    }

    // Time complexity is O(n).
    // Space complexity is O(n), but since object is stored on heap, iterative solution
    // avoids stack overflow.
}
