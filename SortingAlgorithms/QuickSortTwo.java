package SortingAlgorithms;

public class QuickSortTwo extends Sort {

    @Override
    public int[] sort(int[] array) {
        if (array == null || array.length == 1) {
            return array;
        }
        quickSort(array, 0, array.length-1);
        return array;
    }

    private int randomPivotIndex(int left, int right) {
        // Math.random() method returns a pseudorandom double type number 
        // greater than or equal to 0.0 and less than 1.0. .
        // so plus 1 let the index will get the value of right
        return left + (int) (Math.random() * (right - left + 1));
    }

    private void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int partitionIndex = partition(array, left, right);
        quickSort(array, partitionIndex + 1, right);
        quickSort(array, 0 , partitionIndex - 1);
    }

    private int partition(int[] array, int left, int right) {
        int pivotIndex = randomPivotIndex(left, right);
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, right);
        int leftBound = left;
        int rightBound = right - 1;
        while (leftBound <= rightBound) {
            if (array[leftBound] < pivot) {
                leftBound++;
            } else if (array[rightBound] >= pivot) {
                rightBound--;
            } else {
                swap(array, leftBound, rightBound);
            }
        }
        swap(array, leftBound, right);
        return leftBound;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

// Time complexity is O(n^2) in the worst case
// O(n*log(n)) in the best and average cases
// Space complexity is O(n) in the worst case
// O(log(n)) in the best and average cases, because of call-stack.