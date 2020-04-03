package Heap;

import java.util.*;

// Find the K smallest numbers in an unsorted integer array A.
// The returned numbers should be in ascending order.

// Assumption: A is not null, 0 <= K <= size of A

public class KSmallestInUnsortedArray {
    public int[] kSmallest(int[] array, int k) {
        if (k == 0) {
            return new int[0];
        }

        if (k == array.length) {
            Arrays.sort(array);
            return array;
        }
        List<Integer> source = new ArrayList<>();
        for (int i : array) {
            source.add(array[i]);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(source);
        int[] res = new int[k];
        for (int j = 0; j < k; ++j) {
            res[j] = minHeap.poll();
        }
        return res;
    }

    // Time complexity is O(n + k*log(n))
    // Space complexity is O(n)

    public int[] kSmallest2(int[] array, int k) {
        if (k == 0) {
            return new int[0];
        }

        if (k == array.length) {
            Arrays.sort(array);
            return array;
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, (o1, o2) -> {
            if (o1.equals(o2)) {
                return 0;
            }
            return o1 > o2 ? -1 : 1;
        });
        for (int i = 0; i < k; ++i) {
            maxHeap.offer(array[i]);
        }
        for (int i = k; i < array.length; ++i) {
            if (array[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(array[i]);
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; ++i) {
            // be careful the order is ascending order
            res[k - i - 1] = maxHeap.poll();
        }
        return res;
    }
    // Time complexity is O(k*log(k) + (n-k)*log(k) + k*log(k)))
    // Space complexity is O(k)

    // if k, n --> infinite ， both ok
    // if n >>> k, the second one maxHeap is better
    // Finally, we can see that solution2 and solution 3 can be comparable
    // but it is hard to say which one is better (depending on the value of k vs n)

    // quick partition
    public int[] kSmallest3(int[] array, int k) {
        if (array == null || array.length == 0 || k == 0) {
            return new int[0];
        }
        helper(array, 0, array.length - 1, k);
        int[] result = Arrays.copyOfRange(array, 0, k);
        quickSort(result, 0, k - 1);
        return result;
    }

    private void helper(int[] array, int start, int end, int k) {
        if (start > end) {
            return;
        }
        int left = partition(array, start, end);
        if (left == k - 1 || left == k) {
            return;
        } else if (left > k) {
            helper(array, start, left - 1, k);
        } else {
            // don't need to change the value of k
            // helper(array, left+1, end, k - left - 1);
            // the k - left - 1 is based on the left
            // actually the index we need to find is still k
            helper(array, left + 1, end, k);
        }
    }

    private int partition(int[] array, int start, int end) {
        // why add 1?
        int p = start + (int)(Math.random() * (end - start + 1));
        int pivot = array[p];
        swap(array, p, end);
        // be careful right = end - 1 not equal end!!
        int left = start, right = end - 1;
        while (left <= right) {
            if (array[left] < pivot) {
                left++;
            } else if (array[right] >= pivot) {
                right--;
            } else {
                swap(array, left++, right--);
            }
        }
        swap(array, left, end);
        return left;
    }

    private void swap(int[] array, int i, int j) {
        if (i != j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int p = partition(array, start, end);
        quickSort(array, p + 1, end);
        quickSort(array, start, p - 1);
    }

    // Time complexity is O(n^2) in the worst case, but O(n) in the average case
    // Space complexity is O(n) in the worst case, but O(log(n)) in the average case

    // Quick Sort: Time complexity is O(n^2) in the worst case
    // but O(n*log(n)) in the average case
    // Space complexity is O(n) in the worst case,
    // but O(log(n)) in the average case, because of call-stack

}
