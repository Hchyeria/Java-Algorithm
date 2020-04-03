package DynamicProgramming;

// Given a rope with positive integer-length n
// how to cut the rope into minteger-length parts with length p[0], p[1], ...,p[m-1]
// in order to get the maximal product of p[0] * p[1] *  ... * p[m-1]?

// m is determined by you and it must be greater than 0 (at least one cut must be made).
// Return the max product you can have.

// Assumption: n >= 2

import java.sql.Time;

public class MaxProductOfCuttingRope {
    // Solution 1: recursion
    public int maxProduct(int length) {
        // base rule
       if (length <= 1) {
           return 0;
       }
       int curMax = 0;
       for (int i = 1; i < length; ++i) {
           int best = Math.max(i, maxProduct(i));
           curMax = Math.max(curMax, (length - i) * best);
       }
       return curMax;
    }

    // Time Complexity: O(n!) -> O(2^n)
    // Space Complexity: O(n)

    // Solution 2: DP
    // dp[i]: represents th maximum product of cutting rope at length i
    // dp[i] = maximum max(j, dp[j]) * (i - j), for i in range [1, length)
    public int maxProduct2(int length) {
        if (length <= 1) {
            return 0;
        }
        int[] dp = new int[length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= length; ++i) {
            int curMax = 0;
            for (int j = 1; j < i; ++j) {
                curMax = Math.max(curMax, Math.max(j, dp[j]) * (i - j));
            }
            dp[i] = curMax;
        }
        return dp[length];
    }

    // Time Complexity: O(n^2)
    // Space Complexity: O(n)

}
