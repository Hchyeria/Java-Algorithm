package Graph;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (k == 0 || n == 0) {
            return res;
        }
        dfs(res, k, n, new ArrayList<>(), 1);
        return res;
    }

    private void dfs(List<List<Integer>> res, int k, int sum, List<Integer> cur, int num) {
        if (sum <= 0 || cur.size() == k || num > 9) {
            if (sum == 0 && cur.size() == k) {
                res.add(new ArrayList<>(cur));
            }
            return;
        }
        cur.add(num);
        dfs(res, k, sum - num, cur, num + 1);

        cur.remove(cur.size() - 1);
        dfs(res, k, sum, cur, num + 1);
    }

    public static void main(String[] args) {
        CombinationSum3 combinationSum3 = new CombinationSum3();
        System.out.println(combinationSum3.combinationSum3(3, 7));
    }
}
