package HashTable;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

// Given an array of integers, find out whether there are two distinct indices i and j in the array
// such that the absolute difference between nums[i] and nums[j] is at most t
// and the absolute difference between i and j is at most k.
public class ContainsDuplicate3 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0
                || k < 1 || t < 0) {
            return false;
        }

        int n = nums.length;
        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < n; ++i) {
            if (i > k) {
                set.remove((long) nums[i - k - 1]);
            }
            // [nums[i] - t, nums[t] + t]
            Long min = set.ceiling((long) nums[i] - t);
            if (min != null && min <= (long) nums[i] + t) {
                return true;
            }
            set.add((long) nums[i]);
        }
        return false;
    }
    // Time = O(n*log(k))
    // Space= O(k)

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0
                || k < 1 || t < 0) {
            return false;
        }

        int n = nums.length;

        Map<Long, Long> bucket = new HashMap<>();

        for (int i = 0; i < n; ++i) {
            if (i > k) {
                // use long !!!
                // even t also must use long !!
                // when t == Integer.MAX_VALUE, t + 1 will overflow
                long removeKey = ((long) nums[i - k - 1] - Integer.MIN_VALUE) / ((long) t + 1);
                bucket.remove(removeKey);
            }

            long b = ((long) nums[i] - Integer.MIN_VALUE) / ((long) t + 1);

            Long c = 0L;
            if (bucket.containsKey(b)
                    || (((c = bucket.get(b - 1)) != null) && nums[i] - c <= t)
                    || (((c = bucket.get(b + 1)) != null) && c - nums[i] <= t)) {
                return true;
            }

            bucket.put(b, (long) nums[i]);
        }

        return false;
    }
    // Time = O(n)
    // Space = O(k)

    public static void main(String[] args) {
        ContainsDuplicate3 containsDuplicate3 = new ContainsDuplicate3();
        int[] a = {1,5,9,1,5,9};
        System.out.println(containsDuplicate3.containsNearbyAlmostDuplicate(a, 2, 3));
    }
}
