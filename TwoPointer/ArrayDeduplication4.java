package TwoPointer;

// Given an unsorted integer array, remove adjacent duplicate elements repeatedly, from left to right.
// For each group of elements with the same value do not keep any of them
// Do this in-place, using the left side of the original array

// Return the array after deduplication

import java.util.Arrays;

public class ArrayDeduplication4 {
    public int[] dedup(int[] array) {
        int slow = 1;
        int fast = 1;
        for ( ; fast < array.length; ++fast) {
            if (slow == 0 || (slow >= 1 && array[fast] != array[slow - 1])) {
                array[slow++] = array[fast];
            } else {
                slow--;
            }
        }
        return Arrays.copyOf(array, slow);
    }

    // Time complexity: O(n)
    // Space complexity: O(1)

    public static void main(String args[]) {
        ArrayDeduplication4 stringDeduplication = new ArrayDeduplication4();
        System.out.println(Arrays.toString(stringDeduplication.dedup(new int[] {1, 2, 3, 6, 7, 6, 6, 7, 6, 8, 9, 10})));
    }
}
