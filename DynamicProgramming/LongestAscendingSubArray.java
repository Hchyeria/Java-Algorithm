package DynamicProgramming;

// Given an unsorted array
// find the length of the longest subarray in which the numbers are in ascending order.
// Assumption: The given array is not null

public class LongestAscendingSubArray {
    // Solution 1: DP solution
    // dp[i]: represents the value of longest ascending sub-array. from 0 to i, including i
    // dp[i] = dp[i - 1] + 1, if nums[i] > nums[i - 1]
    // dp[i] = 1, otherwise
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null) {
            return -1;
        }
        if (nums.length == 0) {
            return 0;
        }
        int globalMax = 1, n = nums.length;
        int cur = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] > nums[i - 1]) {
                cur++;
                globalMax = Math.max(globalMax, cur);
            } else {
                cur = 1;
            }
        }
        return globalMax;
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)


    // Solution 2: sliding window
    public int findLengthOfLCIS2(int[] nums) {
        if (nums == null) {
            return -1;
        }
        if (nums.length == 0) {
            return 0;
        }
        int globalMax = 1, n = nums.length;
        for (int left = 0, i = 0; i < n; ++i) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                globalMax = Math.max(globalMax, i - left + 1);
            } else {
                left = i;
            }
        }
        return globalMax;
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)
}
