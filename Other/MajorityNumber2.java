package Other;

// Given an integer array of length L, find all numbers that occur more than 1/3 * L
// times if any exists.

// Assumption: The given array is not null.

import java.util.ArrayList;
import java.util.List;

public class MajorityNumber2 {
    // Solution 1: Sort the array, then linear scan.
    // O(n * log(n)) time, O(n) space, because of quick sort (for primitive types).

    // Solution 2: Hash map.
    // O(n) time, O(n) space.

    // Solution 3: Boyer-Moore Majority Vote Algorithm
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        int candidate1 = nums[0];
        int candidate2 = nums[0];
        int count1 = 0;
        int count2 = 0;
        int n = nums.length;
        for (int value : nums) {
            if (value == candidate1) {
                count1++;
            } else if (value == candidate2) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = value;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = value;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }
        if (count1 > n / 3) {
            res.add(candidate1);
        }
        if (count2 > n / 3) {
            res.add(candidate2);
        }
        return res;

    }

    public static void main(String[] args) {
        MajorityNumber2 majorityNumber2 = new MajorityNumber2();
        int[] a = {1,1,1,3,3,2,2,2};
        System.out.println(majorityNumber2.majorityElement(a));
    }
}
