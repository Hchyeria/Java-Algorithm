package SegmentTree;

import java.util.LinkedList;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf {
    private static class SegmentNode {
        int l;
        int r;
        int k;
        int lazy;

        SegmentNode left, right;

        int mid() {
            return l + (r - l) / 2;
        }

        SegmentNode(int l, int r, int k) {
            this.l = l;
            this.r = r;
            this.k = k;
        }

    }

    private int query(SegmentNode node, int start, int end) {
        if (start > end || node == null || start > node.r || end < node.l) {
            return 0;
        }

        if (start <= node.l && node.r <= end) {
            return node.k;
        }
        normalize(node);

        return query(node.left, start, end) + query(node.right, start, end);
    }

    private void insert(SegmentNode node, int start, int end, int val) {
        if (start > end || node == null || start > node.r || end < node.l) {
            return;
        }
        if (start <= node.l && node.r <= end) {
            node.k += val;
            node.lazy += val;
            return;
        }
        normalize(node);

        insert(node.left, start, end, val);
        insert(node.right, start, end, val);

        node.k = node.left.k + node.right.k;
    }

    private void normalize(SegmentNode node) {
        if (node.left == null || node.right == null) {
            int mid = node.mid();
            node.left = new SegmentNode(node.l, mid, 0);
            node.right = new SegmentNode(mid + 1, node.r, 0);
        } else {
            node.left.k += node.lazy;
            node.left.lazy += node.lazy;

            node.right.k += node.lazy;
            node.right.lazy += node.lazy;
        }
        node.lazy = 0;
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int n = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i : nums) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }

        SegmentNode root = new SegmentNode(min, max, 0);
        for (int i = n - 1; i >= 0; --i) {
            res.add(0, query(root, min, nums[i] - 1));
            insert(root, nums[i], nums[i], 1);
        }

        return res;
    }

    public static void main(String[] args) {
        CountOfSmallerNumbersAfterSelf countOfSmallerNumbersAfterSelf = new CountOfSmallerNumbersAfterSelf();
        int[] a = {5,2,6,1};
        System.out.println(countOfSmallerNumbersAfterSelf.countSmaller(a));
    }
}
