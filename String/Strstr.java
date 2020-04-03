package String;

// Determine if a small string is a substring of another large string.
// Return the index of the first occurrence of the small string in the large string.
// Return -1 if the small string is not a substring of the large string.

// Assumptions:
// 1. Both large and small are not null
// 2. If small is empty string, return 0

import java.util.Arrays;

public class Strstr {
    // Solution 1: rude solution
    public int strstr(String large, String small) {
        if (large == null && small == null) {
            return -1;
        }

        if (small == null) {
            return 0;
        }
        if (large == null) {
            return -1;
        }
        if (small.length() > large.length()) {
            return -1;
        }
        // What should we return when small is an empty string?
        // This is a great question to ask during an interview
        // For the purpose of this problem, we will return 0 when needle is an empty string
        // This is consistent to C's strstr() and Java's indexOf()
        // String compare can't use ==
        // should use .equals()
        // == compare their reference or address location
        if (small.equals("")) {
            return 0;
        }
        for (int i = 0; i <= large.length() - small.length(); ++i) {
            if (equal(large, small, i)) {
                return i;
            }
        }
        return -1;
    }

    private boolean equal(String large, String small, int index) {
        for (int i = index; i < index + small.length(); ++i) {
            if (large.charAt(i) != small.charAt(i - index)) {
                return false;
            }
        }
        return true;
    }

    // Time Complexity: O(m * n)
    // Space Complexity: O(1)

    // Solution 2: Rabin-Karp algorithm
    public int strstr2(String large, String small) {
        if (large == null && small == null) {
            return -1;
        }
        if (small == null) {
            return 0;
        }
        if (large == null) {
            return -1;
        }
        if (small.length() > large.length()) {
            return -1;
        }
        if (small.equals("")) {
            return 0;
        }
        int largePrime = 101;
        int prime = 31;
        int seed = 1;;
        int targetHash = small.charAt(0) % largePrime;
        for (int i = 1; i < small.length(); ++i) {
            seed = moduleHash(seed, 0, prime, largePrime);
            targetHash = moduleHash(targetHash, small.charAt(i), prime, largePrime);
        }
        int hash = 0;
        for (int i = 0; i < small.length(); ++i) {
            hash = moduleHash(hash, large.charAt(i), prime, largePrime);
        }
        if (hash == targetHash && equal(large, small, 0)) {
            return 0;
        }
        for (int i = 1; i <= large.length() - small.length(); ++i) {
            hash = nonNegative(hash - seed * large.charAt(i - 1) % largePrime, largePrime);
            hash = moduleHash(hash, large.charAt(i + small.length() - 1), prime, largePrime);
            if (hash == targetHash && equal(large, small, i)) {
                return i;
            }
        }
        return -1;
    }
    private int moduleHash(int hash, int addition, int prime, int largePrime) {
        return (hash * prime % largePrime + addition) % largePrime;
    }

    private int nonNegative(int hash, int largePrime) {
        if (hash < 0) {
            hash += largePrime;
        }
        return hash;
    }
    // Time Complexity: O(m + n)
    // Space complexity: O(1)

    // Solution 3: KMP algorithm
    public int strstr3(String large, String small) {
        if (large == null || small == null) {
            return -1;
        }
        if (small.length() > large.length()) {
            return -1;
        }
        if (small.equals("")) {
            return 0;
        }
        char[] in = small.toCharArray();
        int[] kmp = buildKmp(in);
        int i = 0, j = 0;
        while (i < large.length() && j < in.length) {
            if (large.charAt(i) == in[j]) {
                j++;
                i++;
            } else if (j == 0){
                i++;
            } else {
                // kmp[k -1] not the kmp[k] !!!
                j = kmp[j - 1];
            }
        }
        if (j == in.length) {
            return i - in.length;
        }
        return -1;
    }

    private int[] buildKmp(char[] small) {
        int[] temp = new int[small.length];
        if (small.length <= 1) {
            return temp;
        }
        int i = 0, j = 1;
        while (j < small.length) {
            if (small[i] == small[j]) {
                temp[j++] = ++i;
            } else {
                // when you want to write a nested while loop
                // you should consider avoid to to this
                // because it may cause the repeated code logic
                if (i == 0) {
                    temp[j] = 0;
                    j++;
                } else {
                    i = temp[i - 1];
                }
            }
        }

        return temp;
    }

    public static void main(String args[]) {
        Strstr strstr = new Strstr();
        String a = "";
        System.out.println(strstr.strstr3("ababcaababcaabc","ababcaabc"));
    }
}
