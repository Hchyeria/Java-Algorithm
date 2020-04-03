package Graph;

// Given a non-negative integer 2D array representing the heights of bars in a matrix.
// Suppose each bar has length and width of 1.
// Find the largest amount of water that can be trapped in the matrix.

// The water can flow into a neighboring bar if the neighboring bar's height is smaller
// than the water's height. Each bar has 4 neighboring bars to the left, right, up and
// down side.

// Assumption:
// The given matrix is not null and has size of M * N, where M > 0 and N > 0, all the values
// are non-negative integers in the matrix.

import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

public class MaxWaterTrapped2 {
    private static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int height;

        Pair(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }

        @Override
        public int compareTo(Pair other) {
            if (height == other.height) {
                return 0;
            }
            return height < other.height ? -1 : 1;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


    private int m, n;
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        m = heightMap.length;
        n = heightMap[0].length;
        PriorityQueue<Pair> minHeap = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();

        addBoard(heightMap, visited, minHeap);
        int sum = 0;
        while (!minHeap.isEmpty()) {
            Pair pair = minHeap.poll();
            sum += pair.height - heightMap[pair.x][pair.y];
            for (int[] d : dirs) {
                int x = pair.x + d[0];
                int y = pair.y + d[1];
                if (isArea(x, y)
                        && visited.add(x * n + y)) {
                    minHeap.offer(new Pair(x, y, Math.max(heightMap[x][y], pair.height)));
                }
            }
        }
        return sum;
    }

    private void addBoard(int[][] heightMap, Set<Integer> visited, PriorityQueue<Pair> minHeap) {
        for (int i = 0; i < n; ++i) {
            minHeap.offer(new Pair(0, i, heightMap[0][i]));
            visited.add(i);
            minHeap.offer(new Pair(m - 1, i, heightMap[m - 1][i]));
            visited.add((m - 1) * n + i);
        }
        for (int i = 1; i < m - 1; ++i) {
            minHeap.offer(new Pair(i, 0, heightMap[i][0]));
            visited.add(i * n);
            minHeap.offer(new Pair(i, n - 1, heightMap[i][n - 1]));
            visited.add(i * n + (n - 1));
        }

    }

    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y <n;
    }

    // Time complexity: O(m*n * log(m*n)).
    // Space complexity: O(m*n)

    public static void main(String[] args) {
        MaxWaterTrapped2 maxWaterTrapped2 = new MaxWaterTrapped2();
        int[][] a = {{1,4,3,1,3,2},{3,2,1,3,2,4},{2,3,3,2,3,1}};
        System.out.println(maxWaterTrapped2.trapRainWater(a));
    }
}
