package String;

// Find all occurrence of anagrams of a given string s in a given string l.
// Return the list of starting indices.

// Assumptions:
// 1. s is not null or empty.
// 2. l is not null.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AllAnagrams {
    // Solution 1: use array
    public List<Integer> allAnagrams(String s, String l) {
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            counter[s.charAt(i) - 'a']++;
        }
        int left = 0, right = 0;
        int count = s.length();
        List<Integer> result = new ArrayList<>();
        while (right < l.length()) {
            // if the char not in the s
            // then after the right pointer scan
            // this value will change to -1
            if (right - left == s.length() && counter[l.charAt(left++) - 'a']++ >= 0) {
                count++;
            }
            if (counter[l.charAt(right++) - 'a']-- > 0) {
                count--;
            }
            if (count == 0) {
                result.add(left);
            }

        }
        return result;
    }
    // Time complexity: O(m + n)
    // Space complexity: O(26)

    // Solution 2: hash table
    public List<Integer> allAnagrams2(String s, String l) {
        List<Integer> result = new ArrayList<>();
        if (l == null || l.length() == 0) {
            return result;
        }
        HashMap<Character, Integer> set = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            int k = set.getOrDefault(s.charAt(i), 0);
            set.put(s.charAt(i), k + 1);
        }
        int left = 0, right = 0;
        int count = set.size();
        while (right < l.length()) {
            Integer k = null;
            if (right - left == s.length()) {
                k = set.get(l.charAt(left));
                if (k != null) {
                    set.put(l.charAt(left), k + 1);
                    // careful !!
                    // this operation makes this char's value from 0 to 1
                    // it means we lose one matched character
                    // maybe some case 1 -> 2, -1 -> 0, it doesn't change the matched number
                    if (k + 1 == 1) {
                        count++;
                    }
                }
                left++;
            }
            k = set.get(l.charAt(right));
            if (k != null) {
                set.put(l.charAt(right), k - 1);
                if (k - 1 == 0) {
                    count--;
                }
            }
            if (count == 0) {
                result.add(left);
            }
            right++;
        }
        return result;
    }

    // Time complexity: O(m + n)
    // Space complexity: O(small.length)

    public static void main(String args[]) {
        AllAnagrams allAnagrams = new AllAnagrams();
        System.out.println(allAnagrams.allAnagrams2("abc", "cbaebabacd"));
    }
}
