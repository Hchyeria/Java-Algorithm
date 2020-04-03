package Stack;

// Given a non-negative integer array representing the heights of a list of adjacent bars.
// Suppose each bar has a width of 1. Find the largest rectangular area that can be formed
// in the histogram.

import DynamicProgramming.LargestSubMatrixSum;

import java.util.Deque;
import java.util.LinkedList;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }
        int n = heights.length;
        int globalMax = Integer.MIN_VALUE;
        Deque<Integer> stack = new LinkedList<>();

        for (int i = 0; i <= n; ++i) {
            while (!stack.isEmpty() && (i == n || heights[stack.peekFirst()] > heights[i])) {
                int item = stack.pollFirst();
                int left = stack.peekFirst() == null ? 0 : stack.peekFirst() + 1;
                globalMax = Math.max(globalMax, (i - left) * heights[item]);
            }
            stack.offerFirst(i);
        }
        return globalMax;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public static void main(String[] args) {
        LargestRectangleInHistogram largestRectangleInHistogram = new LargestRectangleInHistogram();
        System.out.println(largestRectangleInHistogram.largestRectangleArea(new int[] {2,1,5,6,2,3}));
    }
}
