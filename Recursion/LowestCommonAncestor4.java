package Recursion;

// Given two nodes in a k-ary tree, find their lowest common ancestor.

// Assumptions:
// 1. There is no parent pointer for the nodes in the binary tree
// 2. The given two nodes are guaranteed to be in the binary tree

import BinaryTree.TreeNode;
import BinaryTree.TreeNodeK;


public class LowestCommonAncestor4 {
    public TreeNodeK lowestCommonAncestor(TreeNodeK root, TreeNodeK one, TreeNodeK two) {
        if (root == null || root == one || root == two) {
            return root;
        }
        int count = 0;
        TreeNodeK temp = null;
        for (TreeNodeK child : root.children) {
            TreeNodeK node = lowestCommonAncestor(child, one, two);
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
