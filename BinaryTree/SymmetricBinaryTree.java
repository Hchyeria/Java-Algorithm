package BinaryTree;

// Check if a given binary tree is symmetric.

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SymmetricBinaryTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return checkSymmetric(root.left, root.right);
    }

    private boolean checkSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.val != right.val) {
            return false;
        } else {
            return checkSymmetric(left.left, right.right) && checkSymmetric(left.right, right.left);
        }
    }

    // Time complexity is O(n).
    // Space complexity is O(n).
    // Note that the worst case is when the binary tree is unbalanced.


    // tail recursion, can be converted to an iterative solution
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        // if we write like this:
        // List<TreeNode> stack = new LinkedList<>();
        // then the stack do not has the offerFirst() pollFirst() method
        // if we use Deque<TreeNode> stack = new ArrayDeque<>();
        // then will throw the Null error
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root.left);
        stack.offerFirst(root.right);
        // the while loop only return the false case, we should dont't return true case
        // because we have no proof to guarantee the tree is symmetric when a subtree fit the true case
        // if all subtree not return false then we can return true
        // not !stack.isEmpty !!!
        while (stack.isEmpty()) {
            TreeNode right = stack.pollFirst();
            TreeNode left = stack.pollFirst();
            if (left == null || right == null) {
                if (left != right) {
                    return false;
                }
            }  else if (left.val != right.val) {
                return false;
            } else {
                stack.offerFirst(right.right);
                stack.offerFirst(left.left);
                stack.offerFirst(right.left);
                stack.offerFirst(left.right);
            }
        }
        return true;
    }
}
