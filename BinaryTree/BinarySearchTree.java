package BinaryTree;

public class BinarySearchTree {
    private TreeNode root;

    // get or search method
    public TreeNode search(int key) {
        if (root == null) {
            return null;
        }
        return search1(root, key);
    }

    // recursion way
    private TreeNode search1(TreeNode root, int key) {
        if (root == null || root.val == key) {
            return root;
        }
        if (root.val > key) {
            return search1(root.left, key);
        } else {
            return search1(root.right, key);
        }
    }

    // iterative way
    private TreeNode search2(TreeNode root, int key) {
        while (root != null && root.val != key) {
            if (root.val > key) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }

    // put or insert method
    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
        }
        root = insert1(root, key);
    }

    // recursive solution

    private TreeNode insert1(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        }
        if (root.val < key) {
            root.right = insert1(root.right, key);
        } else if (root.val > key) {
            root.left = insert1(root.left, key);
        }
        // must return root to the upper stack
        return root;
    }

    // recursive solution2
    private void insert2(TreeNode root, int key) {
        if (root.val == key) {
            return;
        } else if (root.val > key) {
            if (root.left == null) {
                root.left = new TreeNode(key);
            } else {
                insert2(root.left, key);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(key);
            } else {
                insert2(root.right, key);
            }
        }
    }

    // iterative solution
    private TreeNode insert3(TreeNode root, int key) {
        TreeNode returnRoot = root;
        TreeNode pre = null;
        while (root != null) {
            pre = root;
            if (root.val > key) {
                root = root.left;
            } else if (root.val < key) {
                root = root.right;
            } else {
                return returnRoot;
            }
        }
        if (pre.val > key) {
            pre.left = new TreeNode(key);
        } else if (pre.val < key) {
            pre.right = new TreeNode(key);
        }
        return returnRoot;
    }

    // iterative solution
    private TreeNode insert4(TreeNode root, int key) {
        TreeNode cur = root;
        while (cur.val != key) {
            if (cur.val < key) {
                if (cur.right == null) {
                    cur.right = new TreeNode(key);
                    break;
                } else {
                    cur = cur.right;
                }
            } else {
                if (cur.left == null) {
                    cur.left = new TreeNode(key);
                    break;
                } else {
                    cur = cur.left;
                }
            }
        }
        return root;
    }

    // delete or remove method
    public void delete(int key) {
        if (root == null) {
            return;
        }
        root = delete1(root, key);
    }

    // recursive solution
    private TreeNode delete1(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        // find
        if (root.val > key) {
            root.left = delete1(root.left, key);
            return root;
        } else if (root.val < key) {
            root.right = delete1(root.right, key);
            return root;
        }

        // root != null && root.val == key
        return deleteRootNode(root);
    }

    private TreeNode deleteRootNode(TreeNode root) {
        if (root == null) {
            return null;
        }

        // case 1: have no left child or right child
        if (root.left == null) {
            // root = root.right; no!!!!
            return root.right;
        } else if (root.right == null) {
            // root = root.left;
            return root.left;
        }

        // case 2: have both left and right child
        // we choose the smallest node of right subtree to replace root
        // case 2.1: right subtree have no left child
        if (root.right.left == null) {
            root.right.left = root.left;
            return root.right;
        }

        // case 2.2:
        TreeNode swapNode = deleteSmallest(root.right);
        swapNode.left = root.left;
        swapNode.right = root.right;
        return swapNode;
    }

    // given a TreeNode cur, find and delete the smallest node
    // from its right sub-tree
    private TreeNode deleteSmallest(TreeNode root) {
        TreeNode pre = null;
        while (root.left != null) {
            pre = root;
            root = root.left;
        }

        // root.left == null
        pre.left = root.right;
        return root;
    }

    // iterative solution
    private TreeNode delete2(TreeNode root, int key) {
        TreeNode cur = root, pre = null;
        while (cur != null && cur.val != key) {
            pre = cur;
            if (cur.val < key) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }

        // cur == null || cur.cal == key
        // cur is the root
        if (pre == null) {
            return deleteRootNode(cur);
        }

        if (pre.left == cur) {
            pre.left = deleteRootNode(cur);
        } else {
            pre.right = deleteRootNode(cur);
        }
        return root;
    }

    // For all of the three operations, the following complexity analysis holds:

    // Time complexity is O(n), when the binary tree is highly unbalanced.
    // Space complexity is O(n), when the binary tree is highly unbalanced.

    // Time complexity is O(n), when the binary tree is highly unbalanced.
    // Space complexity is O(1).

}
