package DynamicProgramming;

// Given a matrix that contains only 1s and 0s, find the largest
// cross which contains only 1s, with the same arm lengths and the
// four arms joining at the central point.
// Return the arm length of the largest cross.

// Assumption: The given matrix is not null, has size of N * M, N >= 0 and M >= 0.

public class LongestCrossOfOnes {
    public int longest(int[][] matrix) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] leftRight = leftToRight(matrix, m, n);
        int[][] upBottom = upToBottom(matrix, m, n);
        int[][] rightLeft = rightToLeft(matrix, m, n);
        int[][] bottomUp = bottomToUp(matrix, m, n);
        int globalMax = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                globalMax = Math.max(globalMax, min(new int[] {leftRight[i][j], upBottom[i][j], rightLeft[i][j], bottomUp[i][j]}));
            }
        }
        return globalMax;
    }

    private int[][] bottomToUp(int[][] matrix, int m, int n) {
        int[][] res = new int[m][n];
        for (int i = 0; i < n; ++i) {
            int pre = 0;
            for (int j = m - 1; j >= 0; --j) {
                if (matrix[j][i] == 0) {
                    pre = 0;
                } else {
                    pre++;
                }
                res[j][i] = pre;
            }
        }
        return res;
    }

    private int[][] rightToLeft(int[][] matrix, int m, int n) {
        int[][] res = new int[m][n];
        for (int i = 0; i < m; ++i) {
            int pre = 0;
            for (int j = n - 1; j >= 0; --j) {
                if (matrix[i][j] == 0) {
                    pre = 0;
                } else {
                    pre++;
                }
                res[i][j] = pre;
            }
        }
        return res;
    }

    private int[][] upToBottom(int[][] matrix, int m, int n) {
        int[][] res = new int[m][n];
        for (int i = 0; i < n; ++i) {
            int pre = 0;
            for (int j = 0; j < m; ++j) {
                if (matrix[j][i] == 0) {
                    pre = 0;
                } else {
                    pre++;
                }
                res[j][i] = pre;
            }
        }
        return res;
    }

    private int[][] leftToRight(int[][] matrix, int m, int n) {
        int[][] res = new int[m][n];
        for (int i = 0; i < m; ++i) {
            int pre = 0;
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 0) {
                    pre = 0;
                } else {
                    pre++;
                }
                res[i][j] = pre;
            }
        }
        return res;

    }


    private int min (int[] array) {
        int res = array[0];
        for (int i : array) {
            res = Math.min(res, i);
        }
        return res;
    }

    // Time complexity: O(4*m*n + m*n)
    // Space complexity: O(4*m*n)

    public static void main(String args[])  {
        int[][] temp = new int[][] {
                {0, 1, 0, 0},
                {1, 1, 1, 1},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
        };
        LongestCrossOfOnes longestCrossOfOnes = new LongestCrossOfOnes();

        System.out.println(longestCrossOfOnes.longest(temp));

    }
}
