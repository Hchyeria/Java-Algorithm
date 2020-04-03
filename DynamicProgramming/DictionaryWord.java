package DynamicProgramming;

// Given a word and a dictionary, determine if it can be composed by
// concatenating words from the given dictionary.

// Assumptions:
// 1. The given word is not null and is not empty
// 2. The given dictionary is not null and is not empty and all the words in the dictionary are not null or empty

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DictionaryWord {
    // DP
    // dp[i]: represents whether first i size chars of string can be segmented
    // dp[i]: whether no cut in the dictionary || (dp[j] && substring(j, i) whether in the dictionary) for j in range [1, i - 1]
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= i - 1; ++j) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    // n: length of s
    // m: size of dict
    // Time Complexity: O(m + n^2*n), because of substring API
    // Space Complexity: O(m + n)

    // Follow up: return all possible compositions?
    // LeetCode #140 (Word Break II)
}
