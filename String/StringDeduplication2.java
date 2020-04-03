package String;


import java.lang.reflect.Array;
import java.util.Arrays;

public class StringDeduplication2 {
    public String deDup(String input) {
        if (input == null || input.length() <= 1) {
            return input;
        }
        char[] in = input.toCharArray();
        int slow = 1, fast = 1;
        while (fast < in.length) {
            if (slow >= 1 && in[fast] == in[slow - 1]) {
                // remember check the bound!!
                while (fast < in.length && in[fast] == in[slow - 1]) {
                    fast++;
                }
                slow--;
            } else {
                in[slow++] = in[fast++];
            }
        }
        return new String(in, 0, slow);
    }

    // Time complexity: O(n)
    // Space complexity: O(1)

    public static void main(String args[]) {
        StringDeduplication2 stringDeduplication = new StringDeduplication2();
        System.out.println(stringDeduplication.deDup("abbbbcdddeefghiijjkk"));
        System.out.println(stringDeduplication.deDup("adeeaddvffvsdfs"));
    }
}
