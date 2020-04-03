package BinaryTree;

import java.util.LinkedList;
import java.util.List;

public class ClosestNumberInBinarySearchTree2 {

    // in-order traversal
    // slide window
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        TreeNode cur = root, pre, node;
        while (cur != null) {
            if (cur.left == null) {
                add(res, cur.val, k, target);
                cur = cur.right;
            } else {
                node = cur.left;
                while (node.right != null) {
                    node = node.right;
                }
                node.right = cur;
                pre = cur;
                cur = cur.left;
                pre.left = null; // avoid circle
            }
        }

        return res;
    }

    private void add(LinkedList<Integer> res, int val, int k, double target) {
        if (res.size() < k) {
            res.offerLast(val);
        } else if (Math.abs(res.peekFirst() - target) > Math.abs(val - target)) {
            res.pollFirst();
            res.offerLast(val);
        }
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
}
