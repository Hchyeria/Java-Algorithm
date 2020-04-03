package BinaryTree;

// Get the list of keys in a given binary search tree in a given range [min, max]
// in ascending order, both min and max are inclusive.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GetKeysInGivenRangeInBST {

    // recursive solution
    public List<Integer> getRange(TreeNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        helper(root, min, max, result);
        return result;
    }

    private void helper(TreeNode root, int min, int max, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (root.val > min) {
            helper(root.left, min, max, result);
        }

        if (root.val >= min && root.val <= max) {
            result.add(root.val);
        }
        if (root.val < max) {
            helper(root.right, min, max, result);
        }
    }
    // Time complexity is O(n).
    // Space complexity is O(n), when the BST is highly unbalanced.


    // in-order traversal to guarantee the monotonically increasing order
    public List<Integer> getRange2(TreeNode root, int min, int max) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            } else {
                cur = stack.pollFirst();
                // don't forget add this break condition
                // the stack pop order is the in-order ascending order
                // so the later integer is 100% larger than the cur
                assert cur != null;
                if (cur.val > max) {
                    break;
                }

                if (cur.val >= min && cur.val <= max) {
                    result.add(cur.val);
                }
                cur = cur.right;

            }
        }
        return result;
    }

    // Time complexity is O(n).
    // Space complexity is O(n), but since object is stored on heap, iterative solution
    // avoids stack overflow.

}
