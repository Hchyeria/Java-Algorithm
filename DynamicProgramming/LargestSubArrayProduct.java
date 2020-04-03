package DynamicProgramming;

// Given an unsorted array of doubles, find the sub-array that has the greatest
// product. Return the product.

// Assumption: The given array is not null and has length of at least 1.

public class LargestSubArrayProduct {
    public double largestProduct(double[] array) {
        double preMax = 1, preMin = 1;
        double curMax = 0, curMin = 0;
        double globalMax = array[0];
        for (double i : array) {
            if (i < 0) {
                curMax = Math.max(i, preMin * i);
                curMin = Math.min(i, preMax * i);
            } else {
                curMax = Math.max(i, preMax * i);
                curMin = Math.min(i, preMin * i);
            }
            preMax = curMax;
            preMin = curMin;
            globalMax = Math.max(globalMax, preMax);
        }
        return globalMax;
    }

    // Time Complexity: O(n)
    // Space Complexity: O(1)
}
