package Recursion;

// Given two nodes in a binary tree (with parent pointer available),
// find their lowest common ancestor.

// Assumptions:
// 1. There is parent pointer for the nodes in the binary tree
// 2. The given two nodes are not guaranteed to be in the binary tree

import BinaryTree.TreeNodeP;

public class LowestCommonAncestor2 {
    public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
        if (one == null || two == null) {
            return null;
        }
        int l1 = getLength(one);
        int l2 = getLength(two);
        if (l1 >= l2) {
            return helper(one, two, l1 - l2);
        } else {
            return helper(two, one, l2 - l1);
        }
    }

    private TreeNodeP helper(TreeNodeP longer, TreeNodeP shorter, int diff) {
        while (diff-- > 0) {
            longer = longer.parent;
        }
        while (shorter != longer) {
            shorter = shorter.parent;
            longer = longer.parent;
        }
        return longer;
    }

    private int getLength(TreeNodeP node) {
        int count = 0;
        while (node != null) {
            node = node.parent;
            count++;
        }
        return count;
    }

    // Time complexity: O(n)
    // Space complexity: O(1)

}
