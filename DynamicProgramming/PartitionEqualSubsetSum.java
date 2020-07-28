package DynamicProgramming;

import java.util.HashSet;
import java.util.Set;

public class PartitionEqualSubsetSum {

    // Solution 1: DFS
    // TTL
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        return dfs(nums, new HashSet<>(), target, 0);
    }

    private boolean dfs(int[] nums, Set<Integer> visited, int sum, int index) {
        // remember to check the boundary
        if (index >= nums.length || sum < nums[index]) {
            return false;
        }

        if (sum == nums[index]) {
            return true;
        }

        // visited.add(index), not visited.add(nums[index])
        if (!visited.add(index)) {
            return false;
        }
        boolean add = dfs(nums, visited, sum - nums[index], index + 1);
        if (add) return true;

        visited.remove(index);
        boolean noAdd = dfs(nums, visited, sum, index + 1);
        return noAdd;
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum partitionEqualSubsetSum = new PartitionEqualSubsetSum();
        int[] a = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,100};
        System.out.println(partitionEqualSubsetSum.canPartition(a));
    }
}
