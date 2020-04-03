package SortingAlgorithms;

/**
 * Given an array of balls, where the color of the balls can only be Red, Green or Blue,
 * sort the balls such that all the Red balls are grouped on the left side,
 * all the Green balls are grouped in the middle and all the Blue balls are grouped on the right side.
 * (Red is denoted by -1, Green is denoted by 0, and Blue is denoted by 1).

Examples

{0} is sorted to {0}
{1, 0} is sorted to {0, 1}
{1, 0, 1, -1, 0} is sorted to {-1, 0, 0, 1, 1}
Assumptions

The input array is not null.
Corner Cases

What if the input array is of length zero? In this case, we should not do anything as well.
 *
 */

public class RainbowSort extends Sort {

    @Override
    public int[] sort(int[] array) {
        if (array == null || array.length == 1) {
            return array;
        }
        rainbowSort(array);
        return array;
    }

    private void rainbowSort(int[] array) {
        int i = 0;
        int cur = 0;
        int k = array.length - 1;
        // three bounds: i, j, k
        // [0, i): -1
        // [i, cur): 0
        // [cur, k]: to be discovered
        // (k, array.length - 1]: 1

        while (cur <= k) {
            /*
            too stupid!!!
            the cur pointer is responsible for scan the array, and manipulate the current element only!!
            don't think too complicated
            if (array[i] == -1) {
                i++;
                j++;
            } else if (array[j] == 0) {
                j++;
            } else if (array[j] == -1) {
                swap(array, i, j);
                i++;
                j++;
            } else if (array[j] == 1) {
                swap(array, j, k);
                k--;
            } else if (array[k] == 0)
            */
            if (array[cur] == -1) {
                swap(array, i++, cur++);
            } else if (array[cur] == 0) {
                cur++;
            } else {
                swap(array, cur, k--);
            }
        }
    }


    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

// Time complexity is O(n).
// Space complexity is O(1).

// Attention:
// 1. definition of three bounds, each of them exclusive/inclusive
// 2. j++ in swap(array, i++, j++)