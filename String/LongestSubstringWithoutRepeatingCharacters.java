package String;

// Given a string, find the longest substring without any repeating characters
// and return the length of it.

// The input string is guaranteed to be not null.

// For example, the longest substring without repeating letters for "bcdfbd" is
// "bcdf", we should return 4 in this case.


import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {
    public int longest(String input) {
        if (input == null || input.length() == 0) {
            return 0;
        } else if (input.length() == 1) {
            return 1;
        }
        HashSet<Character> distinct = new HashSet<>();
        int fast = 0, slow = 0;
        int result = 0;
        while (fast < input.length()) {
            if (distinct.add(input.charAt(fast))) {
                fast++;
                // result = Math.max(result, distinct.size());
                result = fast - slow;
            } else {
                distinct.remove(input.charAt(slow));
                slow++;
            }
        }
        return result;
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
}
