package DynamicProgramming;

// Find the length of longest common subsequence of two given strings.

// Assumption: The two given strings are not null.

public class LongestCommonSubsequence {
    // dp
    // dp[i][j]: represents the longest common subsequence of first i size of text1 and first j size of text2
    // induction rule:
    //              dp[i][j] = dp[i-1][j-1], if text1[i] == text2[j]
    //              dp[i][j] = max(dp[i-1][j], dp[i][j-1]), otherwise
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];

    }

    // Time Complexity: O(m*n)
    // Space Complexity: O(m*n) -> can optimise to O(min(m, n)) -> O(1)
}
