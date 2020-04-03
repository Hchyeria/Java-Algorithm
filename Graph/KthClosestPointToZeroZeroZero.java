package Graph;

// Given three arrays sorted in ascending order.

// Pull one number from each array to form a coordinate <x, y, z> in a 3D space.

// Find the coordinates of the points that is k-th closest to <0, 0, 0>. We are using euclidean distance
// here.

// Assumption:
// 1. The three given arrays are not null or empty.
// 2. K >= 1 and K <= a.length * b.length * c.length.

import java.util.*;

public class KthClosestPointToZeroZeroZero {
    public List<Integer> closest(final int[] a, final int[] b, final int[] c, int k) {
        Set<List<Integer>> visited = new HashSet<>();
        PriorityQueue<List<Integer>> minHeap = new PriorityQueue<>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                long distance1 = getDistance(o1, a, b, c);
                long distance2 = getDistance(o2, a, b, c);
                if (distance1 == distance2) {
                    return 0;
                }
                return distance1 < distance2 ? -1 : 1;
            }
        });

        List<Integer> cur = Arrays.asList(0, 0, 0);
        minHeap.offer(cur);
        visited.add(cur);

        for (int i = 0; i < k - 1; ++i) {
            cur = minHeap.poll();
            List<Integer> next = Arrays.asList(cur.get(0) + 1, cur.get(1), cur.get(2));
            if (cur.get(0) + 1 < a.length && visited.add(next)) {
                minHeap.offer(next);
            }
            next = Arrays.asList(cur.get(0), cur.get(1) + 1, cur.get(2));
            if (cur.get(1) + 1 < b.length && visited.add(next)) {
                minHeap.offer(next);
            }
            next = Arrays.asList(cur.get(0), cur.get(1), cur.get(2) + 1);
            if (cur.get(2) + 1 < c.length && visited.add(next)) {
                minHeap.offer(next);
            }
        }
        cur = minHeap.poll();
        return Arrays.asList(a[cur.get(0)], b[cur.get(1)], c[cur.get(2)]);

    }

    private Long getDistance(List<Integer> point, int[] a, int[] b, int[] c) {
        long distance = 0;
        distance += a[point.get(0)] * a[point.get(0)];
        distance += b[point.get(1)] * b[point.get(1)];
        distance += c[point.get(2)] * c[point.get(2)];
        return distance;
    }

    // Time complexity: O(k*log(k)
    // Space complexity: O(k)
}
