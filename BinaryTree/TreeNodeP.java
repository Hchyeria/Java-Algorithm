package BinaryTree;

public class TreeNodeP {
    public int val;
    public TreeNodeP left;
    public TreeNodeP right;
    public TreeNodeP parent;

    public TreeNodeP(int key, TreeNodeP parent) {
        this.val = key;
        this.parent = parent;
    }
}

