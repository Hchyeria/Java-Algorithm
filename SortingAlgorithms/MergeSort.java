package SortingAlgorithms;

public class MergeSort extends Sort {
    // Time complexity: O(n*log(n))
    // n == the comparative times on each level of call stack
    // log(n) == the depth of recursion call stack (reduce half each time)
    // Space complexity: O(n)
    @Override
    public int[] sort(int[] array) {
        // check if the argument is null or []
        if (array == null || array.length == 0) {
            return array;
        }
        // allocate helper array to help merge step
        // so that we guarantee no more than O(n) space is used
        // the space complexity is O(n) in this case
        int[] helper = new int[array.length];
        mergeSort(helper, 0, array.length - 1, array);
        return array;
    }

    private void mergeSort(int[] helper, int left, int right, int[] array) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(helper, left, mid, array);
        mergeSort(helper, mid + 1, right, array);
        merge(helper, left, right, array);
    }

    private void merge(int[] helper, int left, int right, int[] array) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        // copy the meta array element to helper array and we will merge from the helper
        for (int i = left; i <= right; ++i) {
            helper[i] = array[i];
        }
        int lStart = left;
        int rStart = mid + 1;
        while (lStart <= mid && rStart <= right) {
            if (helper[lStart] <= helper[rStart]) {
                array[left++] = helper[lStart++];
            }
            else {
                array[left++] = helper[rStart++];
            }
        }
        // if we still have elements at left side, we need to copy them
        while (lStart <= mid) {
            array[left++] = helper[lStart++];
        }
        // if there are some elements at right side, we do not need to copy them
        // because they are already at their correct position
    }

}
