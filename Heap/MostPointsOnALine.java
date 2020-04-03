package Heap;

// Given an array of 2D coordinates of points (all the coordinates are integers),
// find the largest number of points that can be crossed by a single line in 2D space.

// Assumption: The given array is not null and it has at least 2 points

import java.util.HashMap;
import java.util.Map;

public class MostPointsOnALine {
    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int n = points.length;
        if (n == 1) {
            return 1;
        }
        int globalMax = 0;
        for (int i = 0; i < n; ++i) {
            int same = 1;
            int sameX = 0;
            Map<Double, Integer> map = new HashMap<>();
            int diffrence = 0;
            for (int j = 0; j < n; ++j) {
                if (j == i) {
                    continue;
                }
                int[] source = points[i];
                int[] next = points[j];
                if (source[0] == next[0] && source[1] == next[1]) {
                    same++;
                } else if (source[0] == next[0]) {
                    sameX++;
                } else {
                    // [[0,0],[94911151,94911150],[94911152,94911151]] test case, will cause precision problem
                    // replace with BigDecimal still has this problem
                    double k = (double)(source[1] - next[1]) / (double)(source[0] - next[0]);
                    int count = map.getOrDefault(k, 0);
                    map.put(k, count + 1);
                    diffrence = Math.max(diffrence, count + 1);
                }

            }
            globalMax = Math.max(globalMax, Math.max(sameX, diffrence) + same);
        }
        return globalMax;
    }
    // Time Complexity: O(n^2)
    // Space Complexity: O(n^2)

    public static void main(String[] args) {
        MostPointsOnALine mostPointsOnALine = new MostPointsOnALine();
        int[][] points = {{-4,-4},{-8,-582},{-3,3},{-9,-651},{9,591}};
        System.out.println(mostPointsOnALine.maxPoints(points));
    }
}
