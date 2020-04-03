package String;

// Right shift a given string by n characters.

// Assumptions:
// 1. The given string is not null.
// 2. n >= 0.

public class RightShiftByNCharacters {
    public String rightShift(String input, int n) {
       if (input == null || input.length() <= 1) {
           return input;
       }
       char[] in = input.toCharArray();
       reverse(in, 0, n - 1);
       reverse(in, n, in.length - 1);
       reverse(in, 0, in.length - 1);
       return new String(in);

    }

    private void reverse(char[] input, int left, int right) {
        while (left < right) {
            char temp = input[left];
            input[left] = input[right];
            input[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String args[]) {
        RightShiftByNCharacters rightShiftByNCharacters = new RightShiftByNCharacters();
        System.out.println(rightShiftByNCharacters.rightShift("abcdefghijk", 3));
    }
}
