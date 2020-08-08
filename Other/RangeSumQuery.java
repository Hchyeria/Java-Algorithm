package Other;

public class RangeSumQuery {

    // Binary Indexed Tree start at index 1
    private long[] tree;
    private int[] A;
    private void add(int i, int val) {
        int n = tree.length;
        while (i < n) {
            tree[i] += val;
            i += (i & (-i));
        }
    }

    private long query(int i) {
        long sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= (i & -i);
        }
        return sum;
    }

    public RangeSumQuery(int[] nums) {
        int n = nums.length;
        tree = new long[n + 1];
        A = new int[n];
        for (int i = 0; i < n; ++i) {
            add(i + 1, nums[i]);
            A[i] = nums[i];
        }
    }

    public void update(int i, int val) {
        add(i + 1, val - A[i]);
        A[i] = val;
    }

    public int sumRange(int i, int j) {
        return (int) (i == 0 ? query(j + 1) : query(j + 1) - query(i));
    }

    public static void main(String[] args) {
        int[] a = {-1};
        RangeSumQuery rangeSumQuery = new RangeSumQuery(a);
        System.out.println(rangeSumQuery.sumRange(0, 0));
        rangeSumQuery.update(0, 1);
        System.out.println(rangeSumQuery.sumRange(0, 0));
    }
}
