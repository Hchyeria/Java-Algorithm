package SortingAlgorithms;

public class SelectionSort extends Sort {
    // Time complexity: O(n^2)
    // Space complexity: O(1)
    @Override
    public int[] sort(int[] array) {
        // check if the argument is null or []
        if (array == null || array.length == 0) {
            return array;
        }
        // find the min element int every loop
        // and then let the min-element be in the ith position
        for (int i = 0; i < array.length - 1; ++i) {
            int min = i;
            for (int j = i + 1; j < array.length; ++j) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            swap(array, i, min);
        }
        return array;
    }
    // swap two elements in order to let the min element change to the ith position
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
