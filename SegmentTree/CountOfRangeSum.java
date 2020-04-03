package SegmentTree;

public class CountOfRangeSum {
    private static class SegmentNode {
        long l;
        long r;
        int k;
        long lazy;
        SegmentNode left, right;

        long mid() {
            return l + (r - l) / 2;
        }

        SegmentNode(long l, long r, int k) {
            this.l = l;
            this.r = r;
            this.k = k;
        }
    }

    private int query(SegmentNode node, long start, long end) {
        if (start > end || node == null || node.l > end || node.r < start) {
            return 0;
        }
        if (start <= node.l && node.r <= end) {
            return node.k;
        }
        normalize(node);
        return query(node.left, start, end) + query(node.right, start, end);
    }

    private void insert(SegmentNode node, long start, long end, int val) {
        if (start > end || node == null || node.l > end || node.r < start) {
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
            long mid = node.mid();
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

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        long[] preSum = new long[n + 1];
        long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = i == 0 ? nums[i] : preSum[i] + nums[i];
            min = Math.min(min, preSum[i + 1]);
            max = Math.max(max, preSum[i + 1]);
        }

        SegmentNode root = new SegmentNode(Math.min(min, 0), Math.max(max, 0), 0);
        int res = 0;

        for (long i : preSum) {
            res += query(root, i - upper, i - lower);
            insert(root, i, i, 1);
        }

        return res;
    }

    static class Solution {

        private int query(int[] tree, long i) {
            int sum = 0;
            if (i <= 0) {
                return 0;
            }
            if (i >= tree.length) {
                i = tree.length - 1;
            }
            while (i > 0) {
                sum += tree[(int) i];
                i -= (i & (-i));
            }
            return sum;
        }

        private void insert(int[] tree, long i, int val) {
            while (i < tree.length) {
                tree[(int) i] += val;
                i += (i & (-i));
            }
        }

        public int countRangeSum(int[] nums, int lower, int upper) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int n = nums.length;
            long[] preSum = new long[n + 1];
            long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
            for (int i = 0; i < n; ++i) {
                preSum[i + 1] = i == 0 ? nums[i] : preSum[i] + nums[i];
                min = Math.min(min, preSum[i + 1]);
                max = Math.max(max, preSum[i + 1]);
            }
            max = Math.max(max, 0);
            min = Math.min(min, 0);
            int[] tree = new int[(int) (max - min + 2)];

            int res = 0;
            for (long i : preSum) {
                res += query(tree, i - lower - min + 1) - query(tree, i - upper - min);
                insert(tree, i - min + 1, 1);
            }

            return res;
        }


    }

    public static void main(String[] args) {
        CountOfRangeSum.Solution countOfRangeSum = new Solution();
        int[] a = {-1,-3,1,1,0,0};
        System.out.println(countOfRangeSum.countRangeSum(a, -2, 1));
    }
}
