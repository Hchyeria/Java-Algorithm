package String;

import java.util.Deque;
import java.util.LinkedList;

public class DecodeString {
    public String decodeString(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        Deque<Integer> countStack = new LinkedList<>();
        Deque<StringBuilder> sequenceStack = new LinkedList<>();
        int fast = 0;
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (fast < s.length()) {
            if (Character.isDigit(s.charAt(fast))) {
                System.out.println(s.charAt(fast) - '0');
                // not the count += 10 * count + (s.charAt(fast) - '0'); !!!
                count = 10 * count + (s.charAt(fast) - '0');
            } else if (s.charAt(fast) == '[') {
                // we must creat a new StringBuilder(sb)
                // otherwise the changes of sb will also lead to the change of it in the stack
                // if we change the sb to String
                // and string += otherString will not affect
                sequenceStack.offerFirst(new StringBuilder(sb));
                countStack.offerFirst(count);
                count = 0;
                sb.setLength(0);
            } else if (s.charAt(fast) == ']') {
                StringBuilder temp = new StringBuilder(sequenceStack.pollFirst());
                int n = countStack.pollFirst();
                while (n-- > 0) {
                    temp.append(sb);
                }
                sb = temp;
            } else {
                sb.append(s.charAt(fast));
            }
            fast++;
        }
        return sb.toString();
    }
    // Time Complexity: O(n)
    // Space Complexity: O(n)

    public static void main(String args[]) {
        DecodeString decompressString = new DecodeString();
        System.out.println(decompressString.decodeString("10[leetcode]"));
    }
}
