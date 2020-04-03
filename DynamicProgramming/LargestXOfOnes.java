package DynamicProgramming;

public class LargestXOfOnes {
    public int longest(int[][] matrix) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] leftUpRightBottom = leftUpToRightBottom(matrix, m, n);
        int[][] rightUpLeftBottom = rightUpToLeftBottom(matrix, m, n);
        int[][] leftBottomRightUp = leftBottomToRightUp(matrix, m, n);
        int[][] RightBottomLeftUp = RightBottomToLeftUp(matrix, m, n);
        int globalMax = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                globalMax = Math.max(globalMax, min(new int[] {leftUpRightBottom[i][j], rightUpLeftBottom [i][j], leftBottomRightUp[i][j], RightBottomLeftUp[i][j]}));
            }
        }
        return globalMax;
    }

    private int[][] leftBottomToRightUp(int[][] matrix, int m, int n) {
        int[][] res = new int[m][n];
        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 1) {
                    res[i][j]= (i == m - 1 || j == 0) ? 1 : res[i + 1][j - 1] + 1;
                }
            }
        }
        return res;
    }

    private int[][] RightBottomToLeftUp(int[][] matrix, int m, int n) {
        int[][] res = new int[m][n];
        for (int i = m - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (matrix[i][j] == 1) {
                    res[i][j] = (i == m - 1 || j == n - 1) ? 1 : res[i + 1][j + 1] + 1;
                }
            }
        }
        return res;
    }

    private int[][] rightUpToLeftBottom(int[][] matrix, int m, int n) {
        int[][] res = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = n - 1; j >= 0; --j) {
                if (matrix[i][j] == 1) {
                    res[i][j]  = (i == 0 || j == n - 1) ? 1 : res[i - 1][j + 1] + 1;
                }
            }
        }
        return res;
    }

    private int[][] leftUpToRightBottom(int[][] matrix, int m, int n) {
        int[][] res = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 1) {
                    res[i][j] = (j == 0 || i == 0) ? 1 : res[i - 1][j - 1] + 1;
                }
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
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 0},
        };
        LargestXOfOnes largestXOfOnes = new LargestXOfOnes();
        System.out.println(largestXOfOnes.longest(temp));

    }
}
