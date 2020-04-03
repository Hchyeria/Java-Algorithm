package DynamicProgramming;

// Determine the largest square of 1s in a binary matrix (a binary matrix only contains
// 0 and 1), return the length of the largest square.

// Assumption: The given matrix is not null and guaranteed to be of size N * N, N >= 0.

import java.util.Arrays;

public class LargestSquareOfOnes {
    // DP
    // dp[i][j]: represents the largest length of square, from position (0, 0) to (i, j)
    // dp[i][j] = min(dp[i-1][j-1], dp[i-1][j]. dp[i][j-1]) + 1 if matrix[i][j] == 1
    //              0 , otherwise
    public int largest(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] cur = new int[n];
        int globalMax = Integer.MIN_VALUE;
        int[] pre = Arrays.copyOfRange(matrix[0], 0, n);
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                if (j == 0) {
                    cur[j] = temp;
                } else {
                    if (temp == 0) {
                        cur[j] = 0;
                    } else {
                        cur[j] = min(new int[] {cur[j - 1] + 1, pre[j] + 1, pre[j - 1] + 1});
                    }
                }
                globalMax = Math.max(globalMax, cur[i]);
            }
            pre = Arrays.copyOfRange(cur, 0, n);
        }
        return globalMax;
    }

    private int min(int[] array) {
        int res = array[0];
        for (int i : array) {
            res = Math.min(res, i);
        }
        return res;
    }

    // Time complexity is O(m * n)
    // Space complexity is O(n)
}
