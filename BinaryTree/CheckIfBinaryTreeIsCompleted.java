package BinaryTree;

// Check if a given binary tree is completed. A complete binary tree is one in which every
// level of the binary tree is completely filled except possibly the last level. Furthermore,
// all nodes are as far left as possible.

import java.util.LinkedList;
import java.util.Queue;

public class CheckIfBinaryTreeIsCompleted {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean flag = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                queue.offer(node.left);
            }

            if (node.right == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                queue.offer(node.right);
            }
        }
        return true;
    }
    // Time complexity is O(n)
    // Space complexity is O(n)
    // since the last layer of a binary tree contains half the total number of nodes
}
