package String;

// "ABCDE12345" -> "A1B2C3D4E5" (in place)

public class StringShuffling {
    public String backMergeSort(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] in = input.toCharArray();
        convert(in, 0, in.length - 1);
        return new String(in, 0, in.length);
    }

    private void convert(char[] input, int left, int right) {
        if (right - left <= 1) {
            return;
        }
        int size = right - left + 1;
        int mid = left + size / 2;
        int leftMid = left + size / 4;
        // if here is
        // int rightMid = left + (int)(size * (3 / 4));
        // the result will be ABEDC21345, it's very strange!!
        // because 3 / 4 always equal 0
        // it can be
        // int rightMid = left + (int)(size * 3 / 4); is OK
        // int rightMid = left + (int)(size * (3 / 4.0)); is OK too
        int rightMid = leftMid + size / 2;
        reverse(input, leftMid, mid - 1);
        reverse(input, mid, rightMid - 1);
        reverse(input, leftMid, rightMid - 1);
        convert(input, left, left + 2 * (leftMid - left) - 1);
        convert(input, left + 2 * (leftMid - left), right);
    }

    private void reverse(char[] input, int left, int right) {
        while (left < right) {
            char temp = input[left];
            input[left++] = input[right];
            input[right--] = temp;
        }
    }

    public static void main(String[] args) {
        StringShuffling stringShuffling = new StringShuffling();
        //System.out.println(stringShuffling.backMergeSort("ABCDEF123456"));
        System.out.println(stringShuffling.backMergeSort("ABCDE12345"));
        System.out.println(stringShuffling.backMergeSort("ABCDEFG1234567"));
    }
}
