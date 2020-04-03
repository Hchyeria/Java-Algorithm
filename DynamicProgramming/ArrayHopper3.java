package DynamicProgramming;

// Given an array A of non-negative integers, you are initially positioned at an arbitrary index of the array.
// A[i] means the maximum jump distance from that position (you can either jump left or jump right).
// Determine the minimum jumps you need to reach the right end of the array.
// Return -1 if you can not reach the right end of the array.

// Assumption:
// 1. The given array is not null and has length of at least 1.
// 2. index is legal, in other words, it is within range [0, array.length - 1].

import java.util.*;

public class ArrayHopper3 {
    public int minJump(int[] array, int index) {
        if (array.length == 1){
            return 0;
        }
        int[] dp = new int[array.length];
        Arrays.fill(dp, array.length);
        Set<Integer> set = new HashSet<>();
        Deque<Integer> queue = new LinkedList<>();
        dp[index] = 0;
        set.add(index);
        queue.offerLast(index);
        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();
            int left = Math.max(0, cur - array[cur]);
            int right = Math.min(array.length - 1, cur + array[cur]);
            for (int i = left; i <= right; ++i) {
                dp[i] = Math.min(dp[i], dp[cur] + 1);
                if (set.add(i)) {
                    queue.offerLast(i);
                }
            }
        }
        return dp[array.length - 1] >= array.length ? -1 : dp[array.length - 1];
    }

    // Time Complexity: O(n^2)
    // Space Complexity: O(n)
}
