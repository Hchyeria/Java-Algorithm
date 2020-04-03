package Other;

// Given an integer array of length L, find the number that occurs more than 0.5 * L times.

// Assumptions:
// 1. The given array is not null or empty
// 2. It is guaranteed there exists such a majority number

public class MajorityNumber {
    // Solution 1: Sort the array, then return the middle element.
    // O(n * log(n)) time, O(n) space, because of quick sort (for primitive types).

    // Solution 2: Hash map.
    // O(n) time, O(n) space.

    // Solution 3: Boyer-Moore Majority Vote Algorithm
    // Time = O(n), Space = O(1)
    public int majorityElement(int[] nums) {
        int res = nums[0];
        int count = 1;
        int n = nums.length;
        for (int i = 1; i < n; ++i) {

            if (nums[i] == res) {
                count++;
            } else if (count != 0) {
                count--;
            } else {
                count = 1;
                res = nums[i];
            }
        }
        return res;
    }
}
