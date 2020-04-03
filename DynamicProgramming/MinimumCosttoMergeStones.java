package DynamicProgramming;


// There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.
// A move consists of merging exactly K consecutive piles into one pile
// and the cost of this move is equal to the total number of stones in these K piles
// Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1

public class MinimumCosttoMergeStones {
    // dp[i][j] represent the minimum cost of merge stones and the isValid(j - i + 1) is true
    // dp[i][j] =
    //            0, when j - i + 1 < k
    //            min(dp[i][k], dp[k + 1][j]), for k in range(i, j, step), step = K - 1, if isValid(j - i + 1) is false
    //            min(dp[i][k], dp[k + 1][j]) + sum(i to j), if isValid(j - i + 1) is true
    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if (!isValid(n, K)) {
            return -1;
        }
        int[] preSum = buildPreSum(stones, n);
        int[][] dp = new int[n][n];
        int step = K - 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + step; j < n; ++j) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k += step) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
                if (isValid(j - i + 1, K)) {
                    int sum = i == 0 ? preSum[j] : preSum[j] - preSum[i - 1];
                    dp[i][j] += sum;
                }
            }
        }
        return dp[0][n - 1];

    }

    private int[] buildPreSum(int[] array, int n) {
        int[] preSum = new int[n];
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += array[i];
            preSum[i] = sum;
        }
        return preSum;

    }

    private boolean isValid(int length, int k) {
        return (length - 1) % (k - 1) == 0;
    }

    // Time Complexity: O(n ^ 3)
    // Space Complexity: O(n ^ 2)

    public static void main(String[] args) {
        MinimumCosttoMergeStones minimumCosttoMergeStones = new MinimumCosttoMergeStones();
        System.out.println(minimumCosttoMergeStones.mergeStones(new int[] {3,2,4,1,6,7,5,1,2,3}, 4));
    }
}
