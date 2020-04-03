package BinarySearch;

// Given a integer dictionary A of unknown size, where the numbers in the dictionary are sorted in ascending order,
// determine if a given target integer T is in the dictionary.
// Return the index of T in A, return -1 if T is not in A.

// dictionary A is not null
// dictionary.get(i) will return null if index i is out of bounds

import java.util.Dictionary;

public class BinarySearchUnknownSize {
    public int search(Dictionary<Integer, Integer> dict, int target) {
        if (dict == null || dict.isEmpty()) {
            return -1;
        }

        int i = 1;
        while ((dict.get(i) != null) && (dict.get(i) < target)) {
            i = i * 2;
        }

        int left = i / 2;
        int right = i;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (dict.get(mid) == null || target < dict.get(mid)) {
                right = mid - 1;
            } else if (dict.get(mid) == target) {
                return mid;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
