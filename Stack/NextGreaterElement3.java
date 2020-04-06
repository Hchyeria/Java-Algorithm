package Stack;

import java.util.LinkedList;
import java.util.List;

public class NextGreaterElement3 {
    public int nextGreaterElement(int n) {
        int[] nums = toArray(n);
        int len = nums.length;
        int index = -1;
        for (int i = len - 2; i >= 0; --i) {
            if (nums[i] < nums[i + 1]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return -1;
        }
        swap(nums, index, index + 1);
        reverse(nums, index + 1, len - 1);
        return (int) toLong(nums);
    }

    private int[] toArray(int n) {
        List<Integer> res = new LinkedList<>();
        while (n != 0) {
            res.add(0, n % 10);
            n /= 10;
        }
        int[] array = new int[res.size()];
        int index = 0;
        for (int i : res) {
            array[index++] = i;
        }
        return array;
    }

    private long toLong(int[] nums) {
        long sum = 0L;
        for (int i : nums) {
            sum *= 10;
            sum += i;
        }
        return sum;
    }

    private void swap(int[] nums, int i, int j) {
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left++, right--);
        }
    }
    // Time = O(n)
    // Space = O(n)

    public static void main(String[] args) {
        NextGreaterElement3 nextGreaterElement3 = new NextGreaterElement3();
        System.out.println(nextGreaterElement3.nextGreaterElement(12));
    }
}
