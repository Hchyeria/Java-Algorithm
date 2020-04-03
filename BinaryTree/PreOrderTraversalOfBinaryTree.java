package BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PreOrderTraversalOfBinaryTree {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            cur = stack.pollFirst();
            if (cur.right != null) {
                stack.offerFirst(cur.right);
            }

            if (cur.left != null) {
                stack.offerFirst(cur.left);
            }

            res.add(cur.val);
        }
        return res;
    }
}
