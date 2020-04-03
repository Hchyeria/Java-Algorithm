package BinaryTree;

// Given the level-order and in-order traversal sequence of a binary tree, reconstruct
// the original tree.

// Assumption:
// 1. The given sequences are not null and they have the same length.
// 2. There are no duplicate keys in the binary tree.

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ReconstructBinaryTreeWithLevelorderAndInorder {
    public TreeNode reconstruct(int[] in, int[] level) {
        if (in == null || in.length == 0
            || level == null || level.length == 0
            || level.length != in.length) {
            return null;
        }

        Map<Integer, Integer> indexMap = new HashMap<>();
        int n = in.length;
        for (int i = 0; i < n; ++i) {
            indexMap.put(in[i], i);
        }
        return helper(in, 0, n - 1, level, indexMap);
    }

    private TreeNode helper(int[] in, int inLeft, int inRight, int[] level, Map<Integer, Integer> indexMap) {
        if (inLeft > inRight) {
            return null;
        }
        TreeNode root = new TreeNode(level[0]);
        int rootIndex = indexMap.get(level[0]);
        int[] leftTree = new int[rootIndex - inLeft];
        int[] rightTree = new int[inRight - rootIndex];
        int left = 0, right = 0;
        for (int node : level) {
            if (indexMap.get(node) > rootIndex) {
                rightTree[right++] = node;
            } else if (indexMap.get(node) < rootIndex) {
                leftTree[left++] = node;
            }
        }
        root.left = helper(in, inLeft, rootIndex - 1, leftTree, indexMap);
        root.right = helper(in, rootIndex + 1, inRight, rightTree, indexMap);

        return root;
    }

    // Time Complexity: O(n*height), O(n^2) in the worst case, but O(n*log(n)) on average
    // Space complexity: O(n^2) in the worst case, but O(n*log(n)) on average, or
    // O(n) if garbage collection is ideal, although not possible in practice

    public static void main(String[] args) {
        ReconstructBinaryTreeWithLevelorderAndInorder reconstructBinaryTreeWithLevelorderAndInorder = new ReconstructBinaryTreeWithLevelorderAndInorder();
        TreeNode root = reconstructBinaryTreeWithLevelorderAndInorder.reconstruct(new int[]{4, 8, 10, 12, 14, 20, 22}, new int[]{20, 8, 22, 4, 12, 10, 14});
        inOrder inOrder = new inOrder();
        PreOrderTraversalOfBinaryTree preOrderTraversalOfBinaryTree = new PreOrderTraversalOfBinaryTree();
        PostOrderTraversalOfBinaryTree postOrderTraversalOfBinaryTree = new PostOrderTraversalOfBinaryTree();
        System.out.println(inOrder.inorderTraversal(root));
        System.out.println(preOrderTraversalOfBinaryTree.preorderTraversal(root));
        System.out.println(postOrderTraversalOfBinaryTree.postorderTraversal(root));
    }
}
