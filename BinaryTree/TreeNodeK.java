package BinaryTree;

import java.util.List;

public class TreeNodeK {
    int val;
    public List<TreeNodeK> children;

    public TreeNodeK(int val, List<TreeNodeK> children) {
        this.val = val;
        this.children = children;
    }
}