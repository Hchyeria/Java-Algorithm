package Graph;

// Given a set of n integers, divide the set in two subsets (a: of size n/2 each,
// b: not necessarily of size n/2 each) such that the difference of the sum of two
// subsets is as minimum as possible.

// Return the minimum difference (absolute value).

// Assumption: The given integer array is not null and it has length of >= 2.

import java.util.ArrayList;
import java.util.List;

public class TwoSubsetsWithMinDifference {
    // Solution 1: DFS
    private int sum = 0;

    public int minDiff(int[] array) {
        int[] globalMin = { Integer.MAX_VALUE };
        List<Integer> subSet = new ArrayList<>();
        sum = getSum(array);
        dfs(array, subSet, globalMin, 0, 0);
        return globalMin[0];
    }

    private int getSum(int[] array) {
        int sum = 0;
        for (int n : array) {
            sum += n;
        }
        return sum;
    }

    // Assumption: The two subsets don't need to be of about equal size
    private void dfs(int[] array, List<Integer> subSet,
                     int[] globalMin, int cur, int level) {
        if (level == array.length) {
            globalMin[0] = Math.min(globalMin[0], Math.abs(sum - 2 * cur));
            return;
        }
        cur += array[level];
        subSet.add(array[level]);
        dfs(array, subSet, globalMin, cur, level + 1);

        cur -= array[level];
        subSet.remove(subSet.size() - 1);
        dfs(array, subSet, globalMin, cur, level + 1);
    }

    // Assumption: The two subsets need to be of about equal size.
    private void dfs2(int[] array, List<Integer> subSet,
                      int[] globalMin, int cur, int level) {
        if (array.length % 2 == 0) {
            if (subSet.size() == array.length / 2) {
                globalMin[0] = Math.min(globalMin[0], Math.abs(sum - 2 * cur));
                return;
            }
        } else {
            if (subSet.size() == array.length / 2 + 1 || subSet.size() == array.length / 2) {
                globalMin[0] = Math.min(globalMin[0], Math.abs(sum - 2 * cur));
            }
        }
        if (level == array.length) {
            return;
        }
        cur += array[level];
        subSet.add(array[level]);
        dfs2(array, subSet, globalMin, cur, level + 1);

        cur -= array[level];
        subSet.remove(subSet.size() - 1);
        dfs2(array, subSet, globalMin, cur, level + 1);

    }

    // Time complexity: O(2 ^ n).
    // Space complexity: O(n).

    // DP
    // Assumptions:
    // 1. All elements are non-negative.
    // 2. The two subsets don't need to be of about equal size.

    // dp[i][j]: represents whether subArray [0, i] can sum to j
    public int minimumDiff(int[] array) {
        boolean[][] dp = new boolean[array.length][sum + 1];
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j <= sum; ++j) {
                if (i == 0) {

                }
            }
        }
        return -1;
    }
}
