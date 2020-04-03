package String;

// Given a string, replace adjacent repeated characters with the character followed
// by the number of repeated occurrences.

// If the character does not has any adjacent repeated occurrences, it is not changed.

// Assumptions:
// 1. The string is not null.
// 2. The characters used in the original string are guaranteed to be 'a' - 'z'.
// 3. There are no adjacent repeated characters with length > 9.

public class CompressString {
    public String compress(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] in = input.toCharArray();
        int fast = 0, slow = 0;
        while (fast < in.length) {
            int pre = fast;
            while (fast < in.length && in[pre] == in[fast]) {
                fast++;
            }
            in[slow++] = in[pre];
            if (fast - pre > 1) {
                String intToString = Integer.toString(fast - pre);
                for (int i = 0; i < intToString.length(); ++i) {
                    in[slow++] = intToString.charAt(i);
                }
            }
        }
        // return [0, slow)
        return new String(in, 0, slow);
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
}
