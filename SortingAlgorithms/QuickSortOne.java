package SortingAlgorithms;

import java.util.Arrays;

public class QuickSortOne extends Sort {

    @Override
    public int[] sort(int[] array) {
        // check if the argument is null or []
        if (array == null || array.length == 0) {
            return array;
        }
        quickSort(array, 0, array.length-1);
        return array;
    }

    private void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int last = end;
        int target = array[start];
        while (start < end) {
            while (start < end && array[end] > target) {
                end--;
            }
            array[start] = array[end];
            while (start < end && array[start] <= target) {
                start++;
            }
            array[end] = array[start];
        }
        array[start] = target;
        quickSort(array, 0, start-1);
        // not the quickSort(array, start+1, array.length)
        // and not the  quickSort(array, start+1, last-1);
        // the last means the last element's index not the length
        quickSort(array, start+1, last);
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
