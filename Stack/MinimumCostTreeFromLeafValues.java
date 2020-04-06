package Stack;

import java.util.Deque;
import java.util.LinkedList;

public class MinimumCostTreeFromLeafValues {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        Deque<Integer> stack = new LinkedList<>();
        int sum = 0;
        for (int i = 0; i <= n; ++i) {
            int right = i == n ? Integer.MAX_VALUE : arr[i];
            while (!stack.isEmpty() && ((stack.peekFirst() < right) || i == n)) {
                int cur = stack.pollFirst();
                int left = stack.isEmpty() ? Integer.MAX_VALUE : stack.peekFirst();
                sum += Math.min(left, right) * cur;
            }
            stack.offerFirst(right);
        }
        return sum;
    }

    public static void main(String[] args) {
        MinimumCostTreeFromLeafValues minimumCostTreeFromLeafValues = new MinimumCostTreeFromLeafValues();
        int[] a = {1, 2};
        System.out.println(minimumCostTreeFromLeafValues.mctFromLeafValues(a));
    }
}
