package Heap;

import java.util.NoSuchElementException;

public class MaxHeap {
    // array starts from index 0,
    // indices of the two children are 2*i+1, and 2*i+2
    // index of parent is (i-1)/2
    private int[] array;
    private int size;

    public MaxHeap(int[] array) {
        if (array == null || array.length <= 0) {
            throw new IllegalArgumentException("the input array can't be null or []");
        }
        this.array = array;
        this.size = array.length;
        heapify();
    }

    public MaxHeap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("the capacity can't <= 0");
        }
        this.array = new int[capacity];
        this.size = 0;
    }

    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; --i) {
            percolateDown(i);
        }
    }

    private void percolateUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (array[index] > array[parent]) {
                swap(index, parent);
            } else {
                break;
            }
            index = parent;
        }
    }

    private void percolateDown(int index) {
        while (index <= size / 2 - 1) {
            int leftChild = index * 2 + 1;
            int rightChild = index * 2 + 2;
            int swapChild = leftChild;
            if (rightChild < size && array[rightChild] > array[swapChild]) {
                swapChild = rightChild;
            }
            if (array[index] < array[swapChild]) {
                swap(index, swapChild);
            } else {
                break;
            }
            index = swapChild;
        }
    }

    public int peek() {
        if (size == 0) {
            throw new NoSuchElementException("the heap is empty");
        }
        return array[0];
    }

    public int poll() {
        if (size == 0) {
            throw new NoSuchElementException("the heap is empty");
        }
        int root = array[0];
        swap(0, size - 1);
        percolateDown(0);
        size--;
        return root;
    }

    public void offer(int value) {
        if (isFull()) {
            int[] newArray = new int[size * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        array[size++] = value;
        percolateUp(size - 1);
    }

    public int update(int index, int value) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is out of bound");
        }
        int res = array[index];
        array[index] = value;
        if (value > res) {
            percolateUp(index);
        } else {
            percolateDown(index);
        }
        return res;
    }

    private void swap(int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }
}
