package String;

// Given an original string input, and two strings S and T, replace all
// occurrences of S in input with T.

// Assumption: Input S and T are not null, S is not empty.

import com.sun.deploy.util.StringUtils;

import java.util.Arrays;
import java.util.StringJoiner;

public class StringReplace {
    // Solution 1:
    public String replace(String input, String s, String t) {
        if (s == null || t ==null || t.length() <= 1) {
            return input;
        }
        StringBuilder sb = new StringBuilder();
        int left = 0, index = input.indexOf(s, left);
        while (index >= 0) {
            sb.append(input, left, index).append(t);
            // not the index + 1 !!
            left = index + s.length();
            index = input.indexOf(s, left);
        }
        sb.append(input, left, input.length());
        return sb.toString();
    }

    // Time complexity: O(n * t).
    // Space complexity: O(1).

    // Solution 2:
    public String replace2(String input, String s, String t) {
        if (s == null || t ==null || t.length() <= 1) {
            return input;
        }
        String[] splitArray = input.split(s);
        // https://howtodoinjava.com/java8/java-8-join-string-array-example/
        //
        // StringUtils.join(Arrays.asList(splitArray), t);
        // StringJoiner sj = new StringJoiner(t);
        // sj.add(element in splitArray)
        return String.join(t, splitArray);
    }

    // Solution 3:
    public String replace3(String input, String s, String t) {
        if (s == null || t ==null || t.length() <= 1) {
            return input;
        }
        char[] in = input.toCharArray(), finalInput;
        if (t.length() > s.length()) {
            int left = 0, index = input.indexOf(s, left);
            int count = 0;
            while (index >= 0) {
                count++;
                left = index + s.length();
                index = input.indexOf(s, left);
            }
            if (count == 0) {
                return input;
            }
            finalInput = new char[input.length() + count * (t.length() - s.length())];
            System.arraycopy(in, 0, finalInput, 0, in.length);
        } else {
            finalInput = in;
        }

        return processStr(finalInput, s, t, input.length());
    }

    private String processStr(char[] finalInput, String s, String t, int length) {
        int i = finalInput.length - 1, j = length - 1;
        while (j >= 0) {
            if (finalInput[j] != s.charAt(s.length() - 1)) {
                finalInput[i--] = finalInput[j--];
            } else {
                j -= s.length();
                for (int k = t.length() - 1; k >= 0; --k) {
                    finalInput[i--] = t.charAt(k);
                }
            }
        }
        return new String(finalInput, i + 1, finalInput.length - i - 1);
    }

    // Time Complexity: O(n * t) worse case
    // Space Complexity: O(n * t) worse case


    public static void main(String args[]) {
        StringReplace stringReplace = new StringReplace();
        System.out.println(stringReplace.replace("studentdents", "den", "XX"));
        System.out.println(stringReplace.replace2("studentdents", "den", "XX"));
        System.out.println(stringReplace.replace3("studentdents", "den", "XX"));
        System.out.println(stringReplace.replace("studentdents", "de", "XXXX"));
        System.out.println(stringReplace.replace2("studentdents", "de", "XXXX"));
        System.out.println(stringReplace.replace3("studentdents", "de", "XXXX"));
    }
}
