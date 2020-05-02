package DynamicProgramming;

import java.util.*;

public class DifferentHats {

    private long mod = (long) (1e9 + 7);
    private int m, n;

    public int findMethods(int[][] hats) {
        n = hats.length;
        m = 40;
        Map<Long, Long> dp = new HashMap<>();
        int[] visited = new int[2];
        return dfs(visited, dp, hats, 0);
    }

    private int dfs(int[] visited, Map<Long, Long> dp, int[][] hats, int level) {
        if (level == n) {
            return 1;
        }

        long index = ((long) visited[0]) * m + visited[1];
        Long res = dp.get(index);
        if (res != null) {
            return (int) (res % mod);
        }

        res = 0L;
        for (int i : hats[level]) {
            if (isVisited(visited, i)) {
                continue;
            }

            flip(visited, i);
            res += dfs(visited, dp, hats, level + 1);
            flip(visited, i);
        }
        dp.put(index, res);
        return (int) (res % mod);
    }

    private boolean isVisited(int[] visited, int i) {
        int row = i / m;
        int col = i % m;
        return ((visited[row] >>> col) & 1) == 1;
    }

    private void flip(int[] visited, int i) {
        int row = i / m;
        int col = i % m;
        visited[row] ^= (1 << col);
    }


    public static void main(String[] args) {
        DifferentHats differentHats = new DifferentHats();
        int[][] a = {
                {1, 2, 3},
                {2, 3, 5, 6},
                {1, 3, 7, 9},
                {1, 8, 9},
                {2, 5, 7}

        };
        System.out.println(differentHats.findMethods(a));
    }
}
