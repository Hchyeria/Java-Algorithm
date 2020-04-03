package DynamicProgramming;

import java.util.Arrays;

public class InterleavingString {
    // dp[i][j][k] i + j == k
    // dp[i][j][k] = dp[i - 1][j][k - 1], if s3[k] = s1[i]
    //              dp[i][j - 1][k - 1], if s3[k] == s2[j]
    //              false
    // dp[0][0][0] = true
    // dp[1][0][1], dp[0][1][1]
    // dp[1][1][2]
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s1 == null || s3 == null) {
            return false;
        }
        int m = s1.length();
        int n = s2.length();
        int len = s3.length();
        if (m + n != len) {
            return false;
        }
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n && i + j <= len; ++j) {
                int k = i + j;

                if (i > 0 && s3.charAt(k - 1) == s1.charAt(i - 1)) {
                    dp[i][j] |= dp[i - 1][j];
                }
                if (j > 0 && s3.charAt(k - 1) == s2.charAt(j - 1)) {
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        }
        return dp[m][n];

    }
    // Time = O(m * n)
    // Space = O(m * n) -> optimise to O(n)

    // optimise space
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1 == null || s1 == null || s3 == null) {
            return false;
        }
        int m = s1.length();
        int n = s2.length();
        int len = s3.length();
        if (m + n != len) {
            return false;
        }
        boolean[][] dp = new boolean[2][n + 1];
        int old = 1, now = 1;
        dp[1 - now][0] = true;
        for (int i = 0; i <= m; ++i) {
            old = now;
            now = 1 - now;
            for (int j = 0; j <= n && i + j <= len; ++j) {
                int k = i + j;
                // remember i==0 && j == 0
                // dp[0][0] == true
                // so it should jump
                if (i == 0 && j == 0) {
                    continue;
                }
                dp[now][j] = false;
                if (i > 0 && s3.charAt(k - 1) == s1.charAt(i - 1)) {
                    dp[now][j] |= dp[old][j];
                }
                if (j > 0 && s3.charAt(k - 1) == s2.charAt(j - 1)) {
                    dp[now][j] |= dp[now][j - 1];
                }
            }
        }
        return dp[now][n];

    }

    // optimise space
    public boolean isInterleave3(String s1, String s2, String s3) {
        if (s1 == null || s1 == null || s3 == null) {
            return false;
        }
        int m = s1.length();
        int n = s2.length();
        int len = s3.length();
        if (m + n != len) {
            return false;
        }
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 0; j <= n && i + j <= len; ++j) {
                int k = i + j;
                // remember i==0 && j == 0
                // dp[0][0] == true
                // so it should jump
                if (i == 0 && j == 0) {
                    continue;
                }
                boolean temp = dp[j];
                dp[j] = false;
                if (i > 0 && s3.charAt(k - 1) == s1.charAt(i - 1)) {
                    dp[j] |= temp;
                }
                if (j > 0 && s3.charAt(k - 1) == s2.charAt(j - 1)) {
                    dp[j] |= dp[j - 1];
                }
            }
        }
        return dp[n];

    }

    // DFS
    // caching valid[i][j] takes much longer time than invalid[i][j]
    // the efficiency of using cache dfs is case-by-case. Here because most of the cases are invalide
    // so choosing to cache the invalid cases is very efficient and it avoids most of the recursion calls.
    public boolean isInterleave4(String s1, String s2, String s3) {
        if (s1 == null || s1 == null || s3 == null) {
            return false;
        }
        int m = s1.length();
        int n = s2.length();
        int len = s3.length();
        if (m + n != len) {
            return false;
        }
        return dfs(s1, m, s2, n, s3, len, new boolean[m + 1][n + 1]);
    }

    private boolean dfs(String s1, int start1, String s2, int start2, String s3, int start3, boolean[][] invalid) {
        if (start1 == 0 && start2 == 0 && start3 == 0) {
            return true;
        }
        if (invalid[start1][start2]) {
            return false;
        }
        if (start1 > 0 && start3 > 0 && s1.charAt(start1 - 1) == s3.charAt(start3 - 1)) {
            if (dfs(s1, start1 - 1, s2, start2, s3, start3 - 1, invalid)) {
                return true;
            }
        }
        if (start2 > 0 && start3 > 0 && s2.charAt(start2 - 1) == s3.charAt(start3 - 1)) {
            if (dfs(s1, start1, s2, start2 - 1, s3, start3 - 1, invalid)) {
                return true;
            }
        }
        invalid[start1][start2] = true;
        return false;
    }

    public static void main(String[] args) {
        InterleavingString interleavingString = new InterleavingString();
        System.out.println(interleavingString.isInterleave("aabcc", "dbbca" ,"aadbbcbcac"));
        System.out.println(interleavingString.isInterleave4("aabcc", "dbbca" ,"aadbbcbcac"));
    }
}
