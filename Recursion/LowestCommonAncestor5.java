package Recursion;

// Given K nodes in a k-ary tree, find their lowest common ancestor.

// Assumptions:
// 1. K >= 2
// 2. There is no parent pointer for the nodes in the k-ary tree
// 3. The given K nodes are guaranteed to be in the binary tree

import BinaryTree.TreeNodeK;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LowestCommonAncestor5 {
    public TreeNodeK lowestCommonAncestor(TreeNodeK root, List<TreeNodeK> nodes) {
        Set<TreeNodeK> set = new HashSet<>();
        return helper(root, set);
    }

    private TreeNodeK helper(TreeNodeK root, Set<TreeNodeK> set) {
        if (root == null || set.contains(root)) {
            return root;
        }
        int count = 0;
        TreeNodeK temp = null;
        for (TreeNodeK child : root.children) {
            TreeNodeK node = helper(child, set);
            if (node != null) {
                if (++count == 2) {
                    return root;
                }
                temp = node;
            }
        }
        return temp;
    }

    // Time complexity: O(n)
    // Space complexity: O(n) call-stack, when the binary tree is highly unbalanced

}
