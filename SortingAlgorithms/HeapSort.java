package SortingAlgorithms;

public class HeapSort extends Sort{
    @Override
    int[] sort(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }
        // heapify
        int size = array.length;
        for (int i = size / 2 - 1; i >= 0; --i) {
            percolateDown(array, size, i);
        }
        while (size > 0) {
            swap(array, 0, size - 1);
            percolateDown(array, --size, 0);
        }

        return array;
    }

    private void percolateDown(int[] array, int size, int index) {
        while (index <= size / 2 - 1) {
            int leftChild = index * 2 + 1;
            int rightChild = index * 2 + 2;
            int swapIndex = leftChild;
            // remember to check right valid
            // because heap is a complete tree, it must has left child, not sure right child
            if (rightChild < size && array[rightChild] > array[leftChild]) {
                swapIndex = rightChild;
            }
            if (array[index] < array[swapIndex]) {
                swap(array, index, swapIndex);
            } else {
                // break necessarily
                break;
            }
            index = swapIndex;
        }
    }

    private void swap(int[] array, int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // Time: O(n + n*log(n))
    // Space: O(1)
    // Unstable

}
