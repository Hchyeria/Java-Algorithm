package Math;

// Given a list of integers representing the lengths of urls,
// find the 95 percentile of all lengths (95% of the urls have lengths <= returned length).

// Assumptions:
// 1. The maximum length of valid url is 4096
// 2. The list is not null, is not empty, and does not contain null

import java.util.List;

public class NinetyFivePercentile {
    public int percentile95(List<Integer> lengths) {
        if (lengths == null) {
            return -1;
        }
        int n = lengths.size();
        int[] buckect = new int[4096 + 1];
        for (int len : lengths) {
            buckect[len]++;
        }
        int sum = 0, index = buckect.length - 1;
        while (sum < n * 0.05) {
            sum += buckect[index--];
        }
        return sum == n * 0.05 ? index : index + 1;
    }

    // Time complexity: O(n)
    // Space complexity: O(maximum length of valid url)
}
