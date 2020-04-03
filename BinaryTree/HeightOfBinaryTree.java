package BinaryTree;

public class HeightOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

// Time complexity is O(n).
// Space complexity is O(n), when the binary tree is highly unbalanced.
