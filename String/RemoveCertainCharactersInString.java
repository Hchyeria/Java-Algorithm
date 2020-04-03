package String;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RemoveCertainCharactersInString {
    public String remove(String input, String t) {
        if (t.length() == 0) {
            return input;
        }
        Set<Character> set = new HashSet<>();
        // String is length() function, array if .length attribute
        for (int i = 0; i < t.length(); ++i) {
            set.add(t.charAt(i));
        }
        char[] in = input.toCharArray();
        int fast = 0, slow = 0;
        for (; fast < in.length; ++fast) {
            if (!set.contains(in[fast])) {
                in[slow++] = in[fast];
            }
        }
        return new String(in, 0, slow);
    }

    // Time Complexity: O(m + n)
    // Space Complexity: O(m + n)

    public static void main(String args[]) {
        RemoveCertainCharactersInString removeCertainCharactersInString = new RemoveCertainCharactersInString();
        System.out.println(removeCertainCharactersInString.remove("student fsgsg", "un"));
    }
}
