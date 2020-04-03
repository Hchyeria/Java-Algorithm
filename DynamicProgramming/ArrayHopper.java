package DynamicProgramming;

// Given an array A of non-negative integers, you are initially positioned at index 0 of the array.
// A[i] means the maximum jump distance from that position (you can only jump towards the end of the array).
// Determine if you are able to reach the last index.

// Assumption: The given array is not null and has length of at least 1.

public class ArrayHopper {
    // Solution 1: DP
    // dp[i]: represents whether I can jump from the i-th element to the target
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;
        for (int i = nums.length - 2; i >= 0; --i) {
            if (i + nums[i] >= nums.length) {
                dp[i] = true;
            } else {
                boolean flag = false;
                for (int j = i; j <= i + nums[i]; ++j) {
                    if (dp[j]) {
                        flag = true;
                        break;
                    }
                }
                dp[i] = flag;
            }
        }
        return dp[0];
    }

    // Time Complexity: O(n^2)
    // or O(n * k) where k is the maximum number of jumps one can make
    // Space Complexity: O(n)

    // Solution 2: greed
    public boolean canJump2(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int curMax = 0;
        for (int i = 0; i < nums.length; ++i) {
            curMax = i <= curMax ? Math.max(curMax, i + nums[i]) : curMax;
        }
        return curMax >= nums.length - 1;
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)
}
