package DynamicProgramming;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Arrays;

public class RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        int n = envelopes.length;
        Arrays.sort(envelopes, (a, b) -> {
            if (a[1] == b[1]) {
                if (a[0] == b[0]) {
                    return 0;
                }
                return a[0] < b[0] ? -1 : 1;
            }
            return a[1] < b[1] ? -1 : 1;
        });
        int[] dp = new int[n];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; --j) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    res = Math.max(res, dp[i]);
                    break;
                }

            }
        }
        return res;
    }

    // Binary Search
    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        int n = envelopes.length;
        Arrays.sort(envelopes, (a, b) -> {
            if (a[1] == b[1]) {
                if (a[0] == b[0]) {
                    return 0;
                }
                // we should descend sort by width
                // because [4, 3] cannot contains [3, 3], so we need to put [4, 3] before [3, 3] when sorting,
                // otherwise it will be counted as an increasing number if the order is [3, 3], [4, 3]
                // and after that, [3, 3] will update [4, 3] to become refined value
                return b[0] < a[0] ? -1 : 1;
            }
            return a[1] < b[1] ? -1 : 1;
        });
        int len = 0;
        int[] cur = new int[n];
        cur[len++] = envelopes[0][0];

        for (int i = 1; i < n; ++i) {
            if (envelopes[i][0] > cur[len - 1]) {
                cur[len++] = envelopes[i][0];
            } else {
                int index = binarySearch(cur, 0, len - 1, envelopes[i][0]);
                if (index >= 0 && index < len) {
                    cur[index] = envelopes[i][0];
                }
            }
        }
        return len;
    }

    private int binarySearch(int[] envelopes, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (envelopes[mid] == target) {
                return mid;
            }
            if (envelopes[mid]<= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
    // Time = O(log(n) * n)
    // Space = O(n)

    public static void main(String[] args) {
        RussianDollEnvelopes russianDollEnvelopes = new RussianDollEnvelopes();
        int[][] a = {
                {15,8},{2,20},{2,14},{4,17},{8,19},{8,9},{5,7},{11,19},{8,11},{13,11},{2,13},{11,19},{8,11},{13,11},{2,13},{11,19},{16,1},{18,13},{14,17},{18,19}
        };
        System.out.println(russianDollEnvelopes.maxEnvelopes2(a));
    }
}
