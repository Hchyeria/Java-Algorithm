package TwoPointer;

// Determine if there exists three elements in a given array that sum to the given target number.
// Return all the triple of values that sums to target.

// Assumption:
// 1. The given array is not null and has length of at least 3.
// 2. No duplicate triples should be returned, order of the values in the tuple does not matter.

// Solutions:
// 1. Triple for loops: Time complexity is O(n^3), space complexity is O(1).
// 2. For loop + Two Sum (with hash map): Time complexity is O(n^2), space complexity is O(n).
// 3. For loop + Two Sum (sort + two pointers): Time complexity is O(n^2), space complexity is O(log(n)),
//    because of quick sort (for primitive types).
//    if we need to deduplicate, the sort will helpful

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int target = 0;
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; ++i) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            twoSum(res, nums, target - nums[i], i, i + 1, len - 1);

        }
        return res;
    }

    public void twoSum(List<List<Integer>> res, int[] nums, int target,
                       int cur, int left, int right) {
        while (left < right) {
            // the cur should not be the nums[i], it must be the index of the array
            // then left - 1 == cur will work
            // if we change it into nums[left - 1] == cur
            // then will work in deduplicate
            // because if we have {-2,-2,-2},
            //                        left
            // left - 1 != index of the first -2
            // but nums[left - 1] == cur (nums[0] == -2)
            if (nums[left] + nums[right] == target
                    && (left - 1 == cur || nums[left - 1] != nums[left])) {
                res.add(Arrays.asList(nums[cur], nums[left++], nums[right--]));
            } else if (nums[left] + nums[right] <= target) {
                left++;
            } else {
                right--;
            }
        }

    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        System.out.println(threeSum.threeSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6}));
    }
}
