package Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakingALargeIsland {
    private static class UnionFind {
        int[] parent;
        int[] rank;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; ++i) {
                parent[i] = i;
            }
        }

        int find(int index) {
            while (parent[index] != index) {
                parent[index] = parent[parent[index]];
                index = parent[index];
            }

            return index;
        }

        boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }

        void union(int x, int y) {
            int p = find(x);
            int q = find(y);
            if (rank[p] < rank[q]) {
                parent[p] = q;
            } else if (rank[p] > rank[q]) {
                parent[q] = parent[p];
            } else {
                parent[p] = q;
                rank[q] += 1;
            }
        }

    }

    private int m;
    private int n;
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int largestIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        UnionFind unionFind = new UnionFind(m * n);
        int globalMax = 0;
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1
                        && !visited.contains(i * n + j)) {
                    int size = dfs(grid, visited, unionFind, i, j);
                    map.put(unionFind.find(i * n + j), size);
                    globalMax = Math.max(globalMax, size);
                }
            }
        }


        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0) {
                    int curSize = 0;
                    Set<Integer> set = new HashSet<>();
                    for (int[] d : dirs) {
                        int x = i + d[0];
                        int y = j + d[1];
                        if (isArea(x ,y) && grid[x][y] == 1) {
                            set.add(unionFind.find(x * n + y));
                        }
                    }

                    for (int c : set) {
                        curSize += map.get(c);
                    }
                    curSize++;
                    globalMax = Math.max(globalMax, curSize);
                }
            }
        }
        return globalMax;
    }

    private int dfs(int[][] grid, Set<Integer> visited,
                    UnionFind unionFind, int x, int y) {
        int count = 1;
        visited.add(x * n + y);
        for (int[] d : dirs) {
            int i = x + d[0];
            int j = y + d[1];
            if (isArea(i ,j)
                    && grid[i][j] == 1
                    && !visited.contains(i * n + j)) {
                count += dfs(grid, visited, unionFind, i, j);
                unionFind.union(x * n + y, i * n + j);
            }
        }

        return count;
    }

    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        MakingALargeIsland makingALargeIsland = new MakingALargeIsland();
        int[][] a = {
                        {0,0,0,0,0,0,0},
                        {0,1,1,1,1,0,0},
                        {0,1,0,0,1,0,0},
                        {1,0,1,0,1,0,0},
                        {0,1,0,0,1,0,0},
                        {0,1,0,0,1,0,0},
                        {0,1,1,1,1,0,0}
                    };
        System.out.println(makingALargeIsland.largestIsland(a));
    }
}
