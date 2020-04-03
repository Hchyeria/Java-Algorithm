package DynamicProgramming;

// Determine the largest square surrounded by 1s in a binary matrix
// (a binary matrix only contains 0 and 1), return the length of the square.

// Assumption: The given matrix is not null and guaranteed to be of size M * N, M >= 0, N >= 0.

public class LargestSquareSurroundedByOnes {
    public int largest(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int globalMax = 0;
        int m = matrix.length, n = matrix[0].length;
        // leftRight[i][j]: represents longest arm length of contiguous ones from left to right
        // include [i][j]
        int[][] leftRight = new int[m][n];
        // upBottom[i][j]: represents longest arm length of contiguous ones from up to bottom
        // include [i][j]
        int[][] upBottom = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                    if (matrix[i][j] == 1) {
                        leftRight[i][j] = j == 0 ? 1 : leftRight[i][j - 1] + 1;
                        upBottom[i][j] = i == 0 ? 1 : upBottom[i - 1][j] + 1;

                        for (int k = Math.min(leftRight[i][j], upBottom[i][j]); k >= 0; --k) {
                            if (leftRight[i - k + 1][j] >= k && upBottom[i][j - k + 1] >= k) {
                                globalMax = Math.max(globalMax, k);
                                break;
                            }
                        }
                    }
            }
        }

        return globalMax;
    }

    // Time complexity: O(m*n*min(m,n))
    // Space complexity: O(m*n)

    public static void main(String args[])  {
        int[][] temp = new int[][] {
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 0, 1, 0},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0},
        };
        LargestSquareSurroundedByOnes largestSquareSurroundedByOnes = new LargestSquareSurroundedByOnes();

        System.out.println(largestSquareSurroundedByOnes.largest(temp));

    }
}
