package TwoPointer;

// Given an integer array(not guaranteed to be sorted), remove adjacent repeated elements.
// For each group of elements with the same value keep at most two of them. Do this in-place,
// using the left side of the original array and maintain the relative order of the elements
// of the array.

// Return the final array.

import java.util.Arrays;

public class ArrayDeduplication2 {
    public int[] dedup(int[] array) {
        int k = 2;
        int slow = 0, fast = 0; // not including slow
        int n = array.length;
        while (fast < n) {
            int begin = fast;
            while (fast < n && array[fast] == array[begin]) {
                fast++;
            }
            if (fast - begin <= k) {
                Arrays.fill(array, slow, slow + fast - begin, array[begin]);
                slow += fast - begin;
            } else {
                Arrays.fill(array, slow, slow + k, array[begin]);
                slow += k;
            }
        }
        return Arrays.copyOf(array, slow);
    }

    public static void main(String args[]) {
        ArrayDeduplication2 stringDeduplication = new ArrayDeduplication2();
        int[] a = {1, 1, 1, 2, 2, 2, 3, 3, 4, 5, 6, 6, 7, 8, 8, 9, 10};
        System.out.println(Arrays.toString(stringDeduplication.dedup(a)));
    }
}
