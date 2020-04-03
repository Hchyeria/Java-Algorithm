package BinarySearch;


import java.lang.reflect.Array;
import java.util.List;

public class KClosestInSortedArray {
    // K closest in sorted array
    // Assumption:
    //      k is not equal zero and k <= array.length

    private int largestSmaller(int target, int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
/*
        can't write like this
        if (array[right] <= target) {
            return right;
        } else {
            return left;
        }
        you should consider the target is not include ub the array
        target maybe smaller or larger than all elements in array
*/
        // when the target is bigger than all elements in array
        // then we should consider the right pointer
        if (array[right] <= target) {
            return right;
        }
        if (array[left] <= target) {
            return left;
        }
        // if not found
        // when the target is smaller than all elements in array so we can not find a element
        // that smaller than target
        return -1;
    }

    // Sample version
    // Time complexity is O(log(n) + k)
    // O(log(n)) run binary search
    // O(k) run find the k closet
    // Space complexity: O(k)
    public int[] kClosest(int[] array, int target, int k) {
        if (array == null || array.length == 0) {
            return array;
        }
        if (k == 0) {
            return new int[0];
        }
        // left is the index of the largest smaller or equal element
        // right = left + 1
        // These two should be the closet to target
        int left = largestSmaller(target, array);
        int right = left + 1;

        int[] res = new int[k];
        int index = 0;
        while (index  < k) {
            // don't forget to check the bound
            // we can use the trick: write every conditions that are true to move left
            // so other that be false only can move right

            // we can advance the left pointer when:
            // 1. right pointer is already out of bound
            // 2. right pointer is not out of bound, left is not out of bound
            //    and array[left] is closer to target
            if (right >= array.length ||
                    left >= 0 && (target - array[left]) <= (array[right] - target)) {
                res[index++] = array[left--];
            } else {
                res[index++] = array[right++];
            }
        }

        return res;
    }

}



// Follow up: The returned integer array is ordered by the closeness to target, could you
// return them in ascending order?
// Answer: Use a linked list: Add to first when left element is picked, and add to last
// when right element is picked
/*
int index = 0;
while (index++  < k) {
    if (right >= arr.length ||
        left >= 0 && (x - arr[left]) <= (arr[right] - x)) {
        //insert the element to the first
        res.add(0, arr[left--]);

    } else {
        res.add(arr[right++]);
    }
}
*/
