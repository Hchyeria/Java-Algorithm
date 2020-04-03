package SortingAlgorithms;

/**
 * Given an array of integers, move all the 0s to the right end of the array.
 * The relative order of the elements in the original array does not need to be maintained.
 *
Assumptions:
The given array is not null.

Examples:

{1} --> {1}
{1, 0, 3, 0, 1} --> {1, 3, 1, 0, 0} or {1, 1, 3, 0, 0} or {3, 1, 1, 0, 0}

 */

public class MoveAllZerosToTheEnd {
    public int[] sort(int[] array) {
        if (array == null || array.length == 1) {
            return array;
        }

        int left = 0, right = array.length - 1;

        while (left <= right) {
            if (array[right] == 0) {
                right--;
            } else if (array[left] != 0) {
                left++;
            } else {
                swap(array, left++, right--);
            }
        }
        return array;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

/*
  Time complexity O(n)
  Space complexity O(1)
*/
/*
* Follow up:
*  write a function to move all 0's to the end of array while maintaining the relative order of the non-zero elements.
* Answer:
*  use tow pointer, slow and fast
*  [0, slow) -> the left side of slow ie the result
*  [slow, fast) -> the all 0's
*/
/*
    int slow = 0, fast = 0;

    while (fast != nums.length) {
        if (nums[fast] != 0) {
            swap(nums, fast++, slow++);
        } else {
            fast++;
        }
    }
*/
