package TwoPointer;

// Given a sorted integer array, remove duplicate elements.
// For each group of elements with the same value do not keep any of them.
// Do this in-place, using the left side of the original array and and maintain the relative order of the elements of the array.

// Return the array after deduplication.

import java.util.Arrays;

public class ArrayDeduplication3 {
    public int[] dedup(int[] array) {
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            int begin = fast;
            while (fast < array.length && array[fast] == array[begin]) {
                fast++;
            }
            if (fast - begin == 1) {
                array[slow++] = array[begin];
            }
        }
        return Arrays.copyOf(array, slow);
    }

    // Time complexity: O(n)
    // Space complexity: O(1)



    public static void main(String args[]) {
        ArrayDeduplication3 stringDeduplication = new ArrayDeduplication3();
        System.out.println(Arrays.toString(stringDeduplication.dedup(new int[] {1, 1, 1, 2, 2, 2, 3, 3, 4, 5, 6, 6, 7, 8, 8, 9, 10})));
    }
}
