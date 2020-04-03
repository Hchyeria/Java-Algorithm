package Graph;

// Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents),
// [25, 10, 5, 1]
// get all the possible ways to pay a target number of cents.

// Assumptions:
// 1. coins is not null and is not empty, all the numbers in coins are positive
// 2. target >= 0
// 3. You have infinite number of coins for each of the denominations, you can pick any number of the coins.

import java.util.ArrayList;
import java.util.List;

public class CombinationOfCoins {
    public List<List<Integer>> combinations(int target, int[] coins) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(target, coins, res, new ArrayList<>(), 0);
        return res;
    }

    private void dfs(int target, int[] coins, List<List<Integer>> res, List<Integer> tempList, int level) {
        if (level == coins.length - 1) {
            tempList.add(target);
            res.add(new ArrayList<>(tempList));
            return;
        }
        int count = target / coins[level];
        // Notice: i <= count not i < count
        for (int i = 0; i <= count; ++i) {
            tempList.add(i);
            dfs(target - coins[level] * i, coins, res, tempList, level + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    // Time complexity: O((target/min(coins))^n), where n is the number of coin types
    // Space complexity: O(n)

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (target == 0) {
            return res;
        }
        dfs2(candidates, target, res, new ArrayList<>(), 0);
        return res;
    }

    private void dfs2(int[] candidates, int target, List<List<Integer>> res, List<Integer> tempList, int index) {
        if (index == candidates.length) {
            if (target == 0) {
                res.add(new ArrayList<>(tempList));
            }
            return;
        }
        int count = target / candidates[index];
        for (int i = 0; i <= count; ++i) {
            for (int j = 0; j < i; ++j) {
                tempList.add(candidates[index]);
            }
            dfs2(candidates, target - i * candidates[index], res, tempList, index + 1);
            for (int j = 0; j < i; ++j) {
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public static void main(String args[]) {
        CombinationOfCoins combinationOfCoins = new CombinationOfCoins();
        System.out.println(combinationOfCoins.combinationSum(new int[] {2, 3, 6, 7},7));
    }
}
