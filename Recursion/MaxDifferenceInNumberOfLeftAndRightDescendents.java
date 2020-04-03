package Recursion;

// Given a binary tree, find the node whose left sub-tree and
// right sub-tree have the maximum difference, in terms of the
// number of nodes.


import BinaryTree.TreeNode;

public class MaxDifferenceInNumberOfLeftAndRightDescendents {
    public TreeNode maxDiff(TreeNode root) {
        if (root == null) {
            return null;
        }
        // can not be TreeNode
        // must be TreeNode[]
        TreeNode[] node = new TreeNode[1];
        int[] globalMax = new int[] { Integer.MIN_VALUE };
        helper(root, globalMax, node);
        return node[0];
    }

    private int helper(TreeNode root, int[] globalMax, TreeNode[] node) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, globalMax, node);
        int right = helper(root.right, globalMax, node);
        globalMax[0] = Math.max(globalMax[0], Math.abs(left - right));
        if (Math.abs(left - right) > globalMax[0]) {
            globalMax[0] = Math.abs(left - right);
            node[0] = root;
        }
        return left + right + 1;
    }

    // Time complexity: O(n)
    // Space complexity: O(n) if the binary tree is highly unbalanced
}
