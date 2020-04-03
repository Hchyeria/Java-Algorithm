package Recursion;

// Given an array A with all positive integers,
// return an array of integer B with the same length,
// where B[i] represents the total number of integers in A,
// whose index is greater than i, and value is smaller than A[i].

// For example, A = [ 4, 1, 3, 2 ], B = [ 3, 0, 1, 0 ]

import java.util.Arrays;

public class CountArray {
    public int[] count(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        int n = array.length;
        int[] res = new int[n];
        int[] indexArray = buildIndexArray(n);
        int[] helper = new int[n];
        mergeSort(array, res, indexArray, helper, 0, n - 1);
        return res;
    }

    private int[] buildIndexArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = i;
        }
        return array;
    }

    private void mergeSort(int[] array, int[] res, int[] indexArray, int[] helper, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, res, indexArray, helper, left, mid);
        mergeSort(array, res, indexArray, helper, mid + 1, right);
        merge(array, res, indexArray, helper, left, mid, right);
    }

    private void merge(int[] array, int[] res, int[] indexArray, int[] helper, int left, int mid, int right) {
        System.arraycopy(indexArray, left, helper, left, right - left + 1);
        int l = left;
        int r = mid + 1;
        int cur = left;
        while (l <= mid) {
            // pay attention, array[helper[l]] <= array[helper[r]]
            // not array[helper[l]] < array[helper[r]]
            // because we need to find the smaller element on the right side, not include equal
            if (r > right || array[helper[l]] <= array[helper[r]]) {
                res[helper[l]] += r - (mid + 1);
                indexArray[cur++] = helper[l++];
            } else {
                indexArray[cur++] = helper[r++];
            }
        }
    }

    // Time Complexity: O(n * log(n))
    // Space Complexity: O(n)

    public static void main(String[] args) {
        CountArray countArray = new CountArray();
        int[] array = new int[] { 1,9,7,8,5 };
        System.out.println(Arrays.toString(countArray.count(array)));
    }
}
