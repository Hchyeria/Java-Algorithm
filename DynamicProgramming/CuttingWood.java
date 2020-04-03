package DynamicProgramming;

// There is a wooden stick with length L >= 1, we need to cut it into pieces,
// where the cutting positions are defined in an int array A.
// The positions are guaranteed to be in ascending order in the range of [1, L - 1].
// The cost of each cut is the length of the stick segment being cut.
// Determine the minimum total cost to cut the stick into the defined pieces.

// Example:
// L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at 4 first
// then cut at 2 and cut at 7)

// Assumptions:
// 1. cuts is not null, length >= 0, all cuts are valid numbers in ascending order

public class CuttingWood {
    // dp[i][j]: represents the minimum cost of cut wooden between index from A[i] to A[j] (including j)
    // dp[i][j] = 0, when j == i + 1, we cannot cut further
    //            min(dp[i][k] + dp[k][j] + A[j] - A[i]), for k in range [i+1, j-1]
    public int minCost(int[] cuts, int length) {
        int n = cuts.length + 2;
        int[][] dp = new int[n][n];
        int[] helper = new int[n];
        helper[0] = 0;
        System.arraycopy(cuts, 0, helper, 1, cuts.length);
        helper[n - 1] = length;
        // from bottom to up, from left to right
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 2; j < n; ++j) {
                dp[i][j] = Integer.MAX_VALUE; // remember initial !!!
                for (int k = i + 1; k < j; ++k) {
                    int temp = dp[i][k] + dp[k][j] + helper[j] - helper[i];
                    dp[i][j] = Math.min(dp[i][j], temp);
                }
            }
        }

        return dp[0][n - 1];
    }

    // Time complexity: O(n^2 * n)
    // Space complexity: O(n^2)

    public static void main(String[] args) {
        CuttingWood cuttingWood = new CuttingWood();
        System.out.println(cuttingWood.minCost(new int[] {2, 6}, 9));
    }
}
