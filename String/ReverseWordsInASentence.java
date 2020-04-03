package String;

// Reverse the words in a sentence.

// Assumptions:
// 1. Words are separated by single space
// 2. There are no heading or tailing white spaces

public class ReverseWordsInASentence {
    public String reverseWords(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] in = input.toCharArray();
        int slow = 0, fast = 0;
        for ( ; fast < in.length; ++fast) {
            if (in[fast] != ' ' && (fast == 0 || in[fast - 1] == ' ')) {
                slow = fast;
            }
            if (in[fast] != ' ' && (fast == in.length - 1 || in[fast + 1] == ' ')) {
                reverse(in, slow, fast);
            }

        }
        reverse(in, 0, in.length - 1);
        return new String(in);
    }

    private void reverse(char[] in, int left, int right) {
        while (left < right) {
            char temp = in[left];
            in[left] = in[right];
            in[right] = temp;
            left++;
            right--;
        }
    }

    // Time complexity: O(n)
    // Space complexity: O(n)

    public static void main(String args[]) {
        ReverseWordsInASentence reverseWordsInASentence = new ReverseWordsInASentence();
        System.out.println(reverseWordsInASentence.reverseWords("I Love Google"));
    }
}
