package BinaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class PostOrderTraversalOfBinaryTree {
    public List<Integer> postorderTraversal(TreeNode root) {
        // use the pre-order, then reverse
        // you can use two stack or Collections.reverse(result))
        // or append these items from the reverse order!
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            cur = stack.pollFirst();
            // this order of cur.left and cur.right also need to change
            // pre-order is right > left
            // post-order id left => right
            assert cur != null;
            if (cur.left != null) {
                stack.offerFirst(cur.left);
            }
            if (cur.right != null) {
                stack.offerFirst(cur.right);
            }
            res.add(0, cur.val); // so amazing, isn't it ?
        }
        return res;
    }
    // What is the drawback of Solution 1 if we print the nodes on the fly?
    // Need to store every node in memory before we can get the whole post order traversal sequence.
    // Not real time print

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        TreeNode cur = null, pre = null;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            cur = stack.peekFirst();
            if (pre == null || pre.left == cur || pre.right == cur) {
                if (cur.left != null) {
                    stack.offerFirst(cur.left);
                } else if (cur.right != null) {
                    stack.offerFirst(cur.right);
                } else {
                    res.add(cur.val);
                    stack.pollFirst();
                }
            } else if (pre == cur.left) {
                if (cur.right != null) {
                    stack.offerFirst(cur.right);
                } else {
                    res.add(cur.val);
                    stack.pollFirst();
                }
            } else {
                res.add(cur.val);
                stack.pollFirst();
            }
            pre = cur;
        }
        return res;
    }
}
