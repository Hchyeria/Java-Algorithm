package Graph;

// Given a set of characters represented by a String, return a list containing all subsets of the characters.
// Assumption: There are no duplicate characters in the original set.

import java.util.*;

public class AllSubsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        List<Integer> item = new ArrayList<>();
        DFS(nums, res, item, 0);
        return res;
    }

    private void DFS(int[] nums, List<List<Integer>> res, List<Integer> item, int index) {
        if (index == nums.length) {
            res.add(new ArrayList<Integer>(item));
            return;
        }
        item.add(nums[index]);
        DFS(nums, res, item, index + 1);
        item.remove(item.size() - 1);
        DFS(nums, res, item, index + 1);
    }

    // Time complexity is O(2^n).
    // Space complexity is O(n), because of call-stack.

    // Notice: If DFS uses a for loop (as in permutations), then
    // append-DFS-remove. If no for loop, then append-DFS-remove-DFS.
}
