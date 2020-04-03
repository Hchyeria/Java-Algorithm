package DynamicProgramming;

// Given a matrix that contains integers, find the sub-matrix with the largest sum.
// Return the sum of the sub-matrix.

// Assumption: The given matrix is not null and has size of M * N, where M >= 1 and N >= 1

public class LargestSubMatrixSum {
    // Solution 1: DP 1
    // dp[i][j]: represents from [0][0] to [i][j], the sum of the subMatrix
    // dp[i][j] = prefix-sum1D[i][j], prefix sum 1D array at row i, if i == 0
    //              dp[i-1][j] + prefix-sum1D[i][j], otherwise
    public int largest(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] prefixSum = prefixSum2D(matrix, m, n);
        int globalMax = Integer.MIN_VALUE;
        for (int up = 0; up < m; ++up) {
            for (int down = up; down < m; ++down) {
                for (int left = 0; left < n; ++left) {
                    for (int right = left; right < n; ++right) {
                        int sum;
                        if (left == 0 || up == 0) {
                            sum = prefixSum[down][right];
                        } else {
                            sum = prefixSum[down][right] - prefixSum[up - 1][right]
                                    - prefixSum[down][left - 1] + prefixSum[up - 1][left - 1];
                        }
                        globalMax = Math.max(globalMax, sum);
                    }
                }

            }
        }
        return globalMax;
    }

    private int[][] prefixSum2D(int[][] matrix, int m, int n) {
        int[][] res = new int[m][n];
        for (int i = 0; i < m; ++i) {
            int preSum = 0;
            for (int j = 0; j < n; ++j) {
                preSum += matrix[i][j];
                if (i == 0) {
                    res[i][j] = preSum;
                } else {
                    res[i][j] = res[i - 1][j] + preSum;
                }
            }
        }
        return res;
    }

    // Time Complexity: O(m*n + m^2*n^2)
    // Space Complexity: O(m*n)

    // Solution 1: DP 2
    // dp[i][j]: prefix sum 1D array each column, from up tp bottom
    public int largest2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] prefixSum = prefixSum1D(matrix, m, n);
        int globalMax = Integer.MIN_VALUE;
        for (int up = 0; up < m; ++up) {
            for (int down = up; down < m; ++down) {
                int[] input = compressByCol(prefixSum, up, down, n);
                int pre = 0;
                for (int num : input) {
                    pre = Math.max(num, pre + num);
                    globalMax = Math.max(globalMax, pre);
                }
            }
        }
        return globalMax;
    }

    private int[][] prefixSum1D(int[][] matrix, int m, int n) {
        int[][] res = new int[m][n];
        for (int i = 0; i < n; ++i) {
            int preSum = 0;
            for (int j = 0; j < m; ++j) {
                preSum += matrix[j][i];
                res[j][i] = preSum;
            }
        }
        return res;
    }

    private int[] compressByCol(int[][] prefixSum, int up, int down, int n) {
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            if (up == 0) {
                res[i] = prefixSum[down][i];
            } else {
                res[i] = prefixSum[down][i] - prefixSum[up - 1][i];
            }
        }
        return res;
    }

    // Time Complexity: O(m*n + m*n*(n + n))
    // Space Complexity: O(m*n)

    public static void main(String args[])  {
        int[][] temp = new int[][] {
                {1, -2, 3, -4, 5},
                {2, 1, -1, -1, 1},
                {2, 3, 1, 1, -2}
        };
        LargestSubMatrixSum largestSubMatrixSum = new LargestSubMatrixSum();

        System.out.println(largestSubMatrixSum .largest(temp));
        System.out.println(largestSubMatrixSum .largest2(temp));
    }

}
