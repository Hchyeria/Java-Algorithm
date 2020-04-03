package String;

// Reverse a given string.

public class ReverseString {
    // Solution 1: iterative solution
    public String reverse(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] in = input.toCharArray();
        int left = 0, right = in.length - 1;
        while (left < right) {
            swap(in, left++, right--);
        }
        return new String(in);
    }

    private void swap(char[] array, int i, int j) {
        if (i != j) {
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // Time Complexity: O(n)
    // Space Complexity: O(n)

    // Solution 2: recursive way
    public String reverse2(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] in = input.toCharArray();
        helper(in, 0, in.length - 1);
        return new String(in);
    }

    private void helper(char[] in, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(in, left++, right--);
        helper(in, left, right);
    }
}
