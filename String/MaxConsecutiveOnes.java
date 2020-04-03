package String;

// Given an array A of 0s and 1s, we may change up to K values from 0 to 1
// Return the length of the longest (contiguous) subarray that contains only 1s

public class MaxConsecutiveOnes {

    public int longestOnes(int[] A, int K) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int zeros = 0;
        int res = 0;
        int left = 0, right = 0;
        while (right < A.length) {
            if (A[right] == 0) {
                zeros++;
            }

            while (zeros > K) {
                zeros = A[left++] == 0 ? zeros - 1: zeros;
            }
            right++;
            res = Math.max(res, right - left);
        }
        return res;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
}
