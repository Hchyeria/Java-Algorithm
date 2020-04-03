package BinaryTree;

// Given a binary tree where all the right nodes are leaf nodes,
// flip it upside down and turn it into a tree with left leaf nodes
// as the root.

public class ReverseBinaryTreeUpsideDown {
    // Solution 1: recursion
    public TreeNode reverse(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode newHead = reverse(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null; // avoid loop
        root.right = null;
        return newHead;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)

    // Solution 2: iterative
    public TreeNode reverse2(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode pre = null, right = null, temp = null;
        while (root != null) {
            TreeNode leftNode = root.left;
            temp = root.right;
            root.left = right;
            root.right = pre;
            pre = root;
            right = temp;
            root = leftNode;
        }
        return pre;
    }
    // Time complexity: O(n)
    // Space complexity: O(1)

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        ReverseBinaryTreeUpsideDown reverseBinaryTreeUpsideDown = new ReverseBinaryTreeUpsideDown();
        TreeNode node = reverseBinaryTreeUpsideDown.reverse2(root);
        inOrder inOrder = new inOrder();
        System.out.println(inOrder.inorderTraversal(node));
        PreOrderTraversalOfBinaryTree preOrderTraversalOfBinaryTree = new PreOrderTraversalOfBinaryTree();
        System.out.println(preOrderTraversalOfBinaryTree.preorderTraversal(node));
    }
}
