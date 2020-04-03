package BinarySearch;

// Given an array of 2D coordinates of points (all the coordinates are integers), find
// the largest number of points that can form a set such that any pair of points in the
// set can form a line with positive slope. Return the size of such maximal set.

import java.util.Arrays;
import java.util.Comparator;

public class LargestSetOfPointsWithPositiveSlope {
    private static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "x: " + this.x + ", y: " + this.y;
        }
    }

    public int largest(Point[] points) {
        if (points == null || points.length <= 1) {
            return 0;
        }
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.x == p2.x) {
                    if (p1.y == p2.y) {
                        return 0;
                    }
                    // when the x is the same
                    // descend the order of y
                    return p2.y < p1.y ? -1 : 1;
                }
                return p1.x < p2.x ? -1 : 1;
            }
        });
        int n = points.length;
        int[] refined = new int[n];
        int len = 0;
        refined[len++] = points[0].y;
        for (int i = 1; i < n; ++i) {
            if (points[i].y > refined[len - 1]) {
                refined[len++] = points[i].y;
            } else {
                int position = binarySearchFind(refined, 0, len - 1, points[i].y);
                if (position >= 0 && position < len) {
                    refined[position] = points[i].y;
                }
            }
        }
        return len;
    }

    // find the smallest larger number
    private int binarySearchFind(int[] refined, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (refined[mid] == target) {
                return mid;
            } else if (refined[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // Time complexity: O(n*log(n) + n*log(n))
    // Space complexity: O(n)
}
