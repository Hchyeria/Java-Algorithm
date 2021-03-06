package DynamicProgramming;

// Given two strings of alphanumeric characters, determine the minimum number of
// Replace, Delete, and Insert operations needed to transform one string into the other.

// Assumption: Both strings are not null.

import java.util.Arrays;

public class EditDistance {
    // Solution 1: recursion
    // Time Limit Exceeded
    public int minDistance(String word1, String word2) {
        if (word1.length() == 0) {
            return word2.length();
        }
        if (word2.length() == 0) {
            return word1.length();
        }
        int nothing = Integer.MAX_VALUE;
        if (word1.charAt(0) == word2.charAt(0)) {
            nothing = minDistance(word1.substring(1), word2.substring(1));
        }
        int replace = 1 + minDistance(word1.substring(1), word2.substring(1));
        int delete = 1 + minDistance(word1.substring(1), word2);
        int insertion = 1 + minDistance(word1, word2.substring(1));
        return min(new int[] {nothing, replace, delete, insertion});
    }

    // Time Complexity: O(4^(m + n)), worse case, ignore substring API
    // Space Complexity: O(m + n), but obviously can be optimized to O(1)

    private int min(int[] array) {
        int res = array[0];
        for (int i : array) {
            res = Math.min(res, i);
        }
        return res;
    }

    // Solution: DP
    // dp[i][j]: represents the minimum value of the edit distance
    // from the first i chas of word1 convert to first j chars word2
    // dp[i][j] = dp[i-1][j-1] where word1[0] == word2[0]
    //            min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1, otherwise
    public int minDistance2(String word1, String word2) {
        if (word1.length() == 0) {
            return word2.length();
        }
        if (word2.length() == 0) {
            return word1.length();
        }

        // the index 0 is when empty string exit
        int m = word1.length(), n = word2.length();
        int[] pre = new int[m + 1];
        int[] cur = new int[m + 1];
        for (int i = 0; i < m + 1; ++i) {
            pre[i] = i;
        }
        for (int i = 1; i < n + 1; ++i) {
            for (int j = 0; j < m + 1; ++j) {
                if (j == 0) {
                    cur[j] = i;
                } else {
                    int nothing = word1.charAt(j - 1) == word2.charAt(i - 1) ? pre[j - 1] : Integer.MAX_VALUE;
                    cur[j] = min(new int[] {nothing, pre[j] + 1, pre[j - 1] + 1, cur[j - 1] + 1});
                }
            }
            pre = Arrays.copyOfRange(cur, 0, cur.length);
        }
        return cur[m];
    }

    // m: word1.length()
    // n: word2.length()
    // Time Complexity: O(m * n)
    // Space Complexity: O(m)
}
