package Other;

// Given an integer array of length L, find all numbers that occur more than 1/K * L times if any exist.

// Assumption:
// 1. The given array is not null or empty.
// 2. K >= 2.

import java.util.*;

public class MajorityNumber3 {
    // Solution 1: Sort the array
    // O(n * log(n)) time, O(n) space, because of quick sort (for primitive types).

    // Solution 2: Hash map.
    // O(n) time, O(n) space.

    // Solution 3: Boyer-Moore Majority Vote Algorithm
    public List<Integer> majority(int[] array, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> candidate = new HashMap<>();
        int count = 0;
        for (int num : array) {
            if (candidate.containsKey(num)) {
                count = candidate.get(num);
                candidate.put(num, count + 1);
            } else if (candidate.size() < k - 1) {
                candidate.put(num, 1);
            } else {
                // Use iterator to avoid ConcurrentModificationException
                Iterator<Map.Entry<Integer, Integer>> iterator = candidate.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, Integer> entry = iterator.next();
                    count = entry.getValue();
                    if (count - 1 == 0) {
                        iterator.remove();
                    } else {
                        entry.setValue(count - 1);
                    }
                }
            }
        }
        candidate.replaceAll((key, v) -> 0);
        for (int num : array) {
            Integer counter = candidate.get(num);
            if (counter != null) {
                candidate.put(num, counter + 1);
            }
        }

        int level = array.length / k;
        for (Map.Entry<Integer, Integer> entry : candidate.entrySet()) {
            if (entry.getValue() > level) {
                res.add(entry.getKey());
            }
        }
        return res;
    }

    // Time complexity: O(n * k), amortize time O(n)
    // Space complexity: O(k)
}
