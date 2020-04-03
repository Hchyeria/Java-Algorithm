package SortingAlgorithms;

import java.util.Arrays;

public class TestSort {
    private static void test(Sort sort) {
        int[] testArray = { 9, 0, 6, -2, 3, 2, -10, 1 };
        sort.sort(testArray);
        System.out.println(Arrays.toString(testArray));

        int[] array = null;
        sort.sort(array);
        System.out.println(Arrays.toString(array));

        array = new int[0];
        sort.sort(array);
        System.out.println(Arrays.toString(array));

        array = new int[] { 1, 2, 3, 4 };
        sort.sort(array);
        System.out.println(Arrays.toString(array));

        array = new int[] { 4, 3, 2, 1 };
        sort.sort(array);
        System.out.println(Arrays.toString(array));

        array = new int[] { 2, 4, 1, 5, 3};
        sort.sort(array);
        System.out.println(Arrays.toString(array));
    }
    public static void main(String[] args) {
//        SelectionSort selectionSort = new SelectionSort();
//        test(selectionSort);
//        MergeSort mergeSort = new MergeSort();
//        test(mergeSort);
//        QuickSortOne quickSortOne = new QuickSortOne();
//        test(quickSortOne);
//        QuickSortTwo quickSortTwo = new QuickSortTwo();
//        test(quickSortTwo);
        HeapSort heapSort = new HeapSort();
        test(heapSort);
//        MoveAllZerosToTheEnd moveAllZerosToTheEnd = new MoveAllZerosToTheEnd();
//        int[] array = null;
//        array = new int[] {1, 0, 3, 0, 1};
//        moveAllZerosToTheEnd.sort(array);
//        System.out.println(Arrays.toString(array));
//
//        array = new int[] {0, 0, 3, 4, 0 , 8, 0, 0, 5, -10, 0, 0};
//        moveAllZerosToTheEnd.sort(array);
//        System.out.println(Arrays.toString(array));
    }
}
