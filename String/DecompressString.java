package String;

// Given a string in compressed form, decompress it to the original string.

// The adjacent repeated characters in the original string are compressed to
// have the character followed by the number of repeated occurrences.

// If the character does not have any adjacent repeated occurrences, it is not compressed.

// Assumptions:
// 1. The string is not null.
// 2. The characters used in the original string are guaranteed to be 'a' - 'z'.
// 3. There are no adjacent repeated characters with length > 9.


public class DecompressString {
    public String decompress(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] in = input.toCharArray();
        StringBuilder res = new StringBuilder();
        int fast = 0;
        int pre = 0;
        while (fast < in.length) {
            if (Character.isDigit(in[fast])) {
                int n = getDigit(in[fast]);
                while (--n > 0) {
                    res.append(in[pre]);
                }
            } else {
                res.append(in[fast]);
                pre = fast;
            }
            fast++;
        }
        return res.toString();
    }

    private int getDigit(char num) {
        return num - '0';
    }

    // Time complexity: O(n)
    // Space complexity: O(n)
    // not convert String to char[] is cause less space

    public static void main(String args[]) {
        DecompressString decompressString = new DecompressString();
        System.out.println(decompressString.decompress("abc3d5f2gh4ij2"));
    }
}
