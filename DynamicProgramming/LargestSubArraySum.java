package DynamicProgramming;

// Given an unsorted integer array, find the subarray that has the greatest sum.
// Return the sum.

// Assumption: The given array is not null and has length of at least 1.

import java.util.Arrays;

public class LargestSubArraySum {
    public int largestSum(int[] array) {
        if (array.length == 1) {
            return array[0];
        }
        int preSum = 0;
        int globalMax = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; ++i) {
            preSum = Math.max(preSum + array[i], array[i]);
            globalMax = Math.max(globalMax, preSum);
        }
        return globalMax;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)

    // Follow up: return the start and end indices of the largest subarray?
    public int[] largestSum2(int[] array) {
        if (array.length == 1) {
            return new int[] {array[0], 0, 0};
        }
        int preSum = 0;
        int globalMax = Integer.MIN_VALUE;
        int curStart = 0;
        int globalStart = 0, globalEnd = 0;

        for (int i = 0; i < array.length; ++i) {
            if (preSum < 0) {
                preSum = array[i];
                curStart = i;
            } else {
                preSum = preSum + array[i];
            }

            if (preSum > globalMax) {
                globalStart = curStart;
                globalEnd = i;
                globalMax = preSum;
            }
        }
        return new int[] {globalMax, globalStart, globalEnd};
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)

    public static void main(String[] args) {
        LargestSubArraySum largestSubArraySum = new LargestSubArraySum();
        System.out.println(Arrays.toString(largestSubArraySum.largestSum2(new int[] {1, 4, 5, -5, -4, 10, 2})));
    }
}
