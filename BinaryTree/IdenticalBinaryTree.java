package BinaryTree;

// Check if two given binary trees are identical. Identical means
// the equal valued keys are at the same position in the two binary trees.


import java.util.LinkedList;

public class IdenticalBinaryTree {
    public boolean isIdentical(TreeNode one, TreeNode two) {
        if (one == null && two == null) {
            return true;
        } else if (one == null || two == null) {
            return false;
        } else if (one.val != two.val) {
            return false;
        } else {
            return isIdentical(one.left, two.left) && isIdentical(one.right, two.right);
        }
    }

    // Time complexity is O(n).
    // Space complexity is O(n), when the binary tree is highly unbalanced.
    // O(log(n)) if the tree is balanced

    // tail recursion, can be converted to an iterative solution
    public boolean isIdentical2(TreeNode one, TreeNode two) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(one);
        stack.offerFirst(two);
        while (!stack.isEmpty()) {
            TreeNode right = stack.pollFirst();
            TreeNode left = stack.pollFirst();
            if (left == null || right == null) {
                if (left != right) {
                    return false;
                }
            } else if (left.val != right.val) {
                return false;
            } else {
                stack.offerFirst(left.left);
                stack.offerFirst(right.left);
                stack.offerFirst(left.right);
                stack.offerFirst(right.right);
            }
        }

        return true;
    }

    // Time complexity is O(n).
    // Space complexity is O(n).

    public static void main(String[] args) {
        IdenticalBinaryTree identicalBinaryTree = new IdenticalBinaryTree();
        TreeNode one = new TreeNode(1);
        one.left = new TreeNode(2);
        one.right = new TreeNode(3);
        TreeNode two = new TreeNode(1);
        two.left = new TreeNode(2);
        two.right = new TreeNode(3);
        System.out.println(identicalBinaryTree.isIdentical2(one, two));
    }
}
