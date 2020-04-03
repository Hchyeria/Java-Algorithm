package String;

import java.util.Arrays;

// Remove adjacent, repeated characters in a given string, leaving only one character
// for each group of such characters.
// No adjacent characters should be identified in the final string

public class StringDeduplication {
    public String deDup(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] in = input.toCharArray();
        int slow = 1, fast = 1;
        while (fast < in.length) {
            if (slow >= 1 && in[fast] == in[slow - 1]) {
                fast++;
            } else {
                in[slow++] = in[fast++];
            }
        }
        return new String(in, 0, slow);
    }

    // Time complexity: O(n)
    // Space complexity: O(n)

    public static void main(String args[]) {
        StringDeduplication stringDeduplication = new StringDeduplication();
        System.out.println(stringDeduplication.deDup("abbbbcdddeefghiijjkk"));
        System.out.println(stringDeduplication.deDup("adeefg  hs"));
        System.out.println(stringDeduplication.deDup("abcdr"));
    }

}
