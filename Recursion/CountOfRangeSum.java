package Recursion;

public class CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = i == 0 ? nums[i] : preSum[i] + nums[i];
        }
        long[] helper = new long[n + 1];
        return mergeSort(preSum, 0, n, lower, upper, helper);
    }

    private int mergeSort(long[] sum, int start, int end, int lower, int upper, long[] helper) {
        if (start >= end) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        int left = mergeSort(sum, start, mid, lower, upper, helper);
        int right = mergeSort(sum, mid + 1, end, lower, upper, helper);
        return merge(sum, start, mid, end, lower, upper, helper) + left + right;
    }

    private int merge(long[] sum, int start, int mid, int end, int lower, int upper, long[] helper) {
        int left = start, right = mid + 1, p = mid + 1, k = mid + 1, t = start;
        System.arraycopy(sum, start, helper, start, end - start + 1);
        int count = 0;
        while (left <= mid) {
            while (k <= end && helper[k] - helper[left] < lower) ++k;
            while (p <= end && helper[p] - helper[left] <= upper) ++p;
            while (right <= end && helper[right] < helper[left]) {
                sum[t++] = helper[right++];
            }
            sum[t++] = helper[left++];
            count += p - k;
        }

        return count;
    }

    public static void main(String[] args) {
        CountOfRangeSum countOfRangeSum = new CountOfRangeSum();
        int[] a = {-2, 5, -1};
        System.out.println(countOfRangeSum.countRangeSum(a, -2, 2));
    }
}
