package BinaryTree;

// Determine whether two given binary trees are identical assuming any number of ‘tweak’s
// are allowed. A tweak is defined as a swap of the children of one node in the tree.

public class TweakedIdenticalBinaryTrees {
    public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
        if (one == null && two == null) {
            return true;
        } else if (one == null || two == null) {
            return false;
        } else if (one.val != two.val) {
            return false;
        } else {
            return isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right)
                    || isTweakedIdentical(one.right, two.left) && isTweakedIdentical(one.left, two.right);
        }
    }

    // Time complexity: O(n^2)
    // Space complexity: O(n) when the tree is highly unbalanced
}
