package DynamicProgramming;

// Given a non-negative integer array representing the heights of a list of adjacent bars.
// Suppose each bar has a width of 1. Find the largest amount of water that can be trapped in the histogram.

// Assumption: The given array is not null

public class MaxWaterTrapped {
    // dp
    public int trap(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int n =height.length;
        int[] dpLeft = new int[n];
        int[] dpRight = new int[n];
        dpLeft[0] = height[0];
        dpRight[n - 1] = height[n - 1];
        for (int i = 1; i < n; ++i) {
            dpLeft[i] = Math.max(dpLeft[i - 1], height[i]);
        }

        for (int i = n - 2; i >= 0; --i) {
            dpRight[i] = Math.max(dpRight[i + 1], height[i]);
        }
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += Math.min(dpLeft[i], dpRight[i]) - height[i];
        }
        return sum;
    }
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public int trap2(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int n = height.length;
        int left = 0, right = n - 1;
        int leftMax = 0, rightMax = 0;
        int sum = 0;
        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                sum += leftMax - height[left++];
            } else {
                sum += rightMax - height[right--];
            }
        }
        return sum;
    }
    // Time Complexity: O(n)
    // Space Complexity: O(1)
}
