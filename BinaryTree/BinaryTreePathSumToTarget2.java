package BinaryTree;

// Given a binary tree in which each node contains an integer number.

// Determine if there exists a path (the path can only be from one node to itself or to any of its
// descendants), the sum of the numbers on the path is the given target number.

import java.util.HashSet;
import java.util.Set;

public class BinaryTreePathSumToTarget2 {
    public boolean exitsPath(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        Set<Integer> prefixSum = new HashSet<>();
        return helper(root, prefixSum, target, 0);
    }

    private boolean helper(TreeNode node, Set<Integer> prefixSum, int target, int sum) {
        if (node == null) {
            return false;
        }
        sum += node.val;
        if (sum == target || prefixSum.contains(sum - target)) {
            return true;
        }
        // wrong!!
        // prefixSum.add(sum);
        // return helper(node.left, prefixSum, target, sum) || helper(node.right, prefixSum, target, sum);

        boolean needRemove = prefixSum.add(sum);

        boolean left = helper(node.left, prefixSum, target, sum);
        if (left) {
            return true;
        }
        boolean right = helper(node.right, prefixSum, target, sum);

        // wrong!!
        // prefixSum.remove(sum);

        if (needRemove) {
            prefixSum.remove(sum);
        }

        return right;

    }

    // Time Complexity: O(n)
    // Space Complexity: O(n), if binary tree is highly unbalanced
    // (SubarraySumToTarget) LeetCode #523, #560)

    // Follow up: return the total number of paths that sum to target (LeetCode #437 Path Sum III).
}
