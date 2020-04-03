package DynamicProgramming;

// Find the longest common substring of two given strings.

// Assumption: The two given strings are not null.

public class LongestCommonSubstring {
    // dp
    // dp[i][j]: represents the longest common substring of first i size of s and first j size of t, including i and j
    // Induction rule:
    //              dp[i][j] = dp[i-1][j-1] + 1,    if s[i] == t[j]
    //              dp[i][j] = 0,                   otherwise
    // maintain a globalMax to record the value in process
    public String longestCommon(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        int end = 0, length = 0;
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {

                    dp[i][j] = dp[i-1][j-1] + 1;
                    if (dp[i][j] > length) {
                        length = dp[i][j];
                        end = i;
                    }
                }
            }
        }
        return s.substring(end - length, end);
    }
    // Time Complexity: O(m*n)
    // Space Complexity: O(m*n) -> can optimise to O(min(m, n)) -> O(1)

    public static void main(String[] args) {
        LongestCommonSubstring longestCommonSubstring = new LongestCommonSubstring();
        System.out.println(longestCommonSubstring.longestCommon("adabce", "abc"));
    }
}
