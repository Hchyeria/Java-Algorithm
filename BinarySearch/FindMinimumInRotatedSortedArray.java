package BinarySearch;

public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int n = nums.length;
        int left = 0, right = n - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            int l = mid == 0 ? Integer.MAX_VALUE : nums[mid - 1];
            int r = mid == n - 1 ? Integer.MAX_VALUE : nums[mid + 1];

            if (nums[mid] <= l && nums[mid] <= r) {
                return nums[mid];
            } else if (nums[mid] >= nums[0] && nums[mid] > nums[n - 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray findMinimumInRotatedSortedArray = new FindMinimumInRotatedSortedArray();
        int[] a = {4,5,6,7,0,1,2};
        System.out.println(findMinimumInRotatedSortedArray.findMin(a));
    }
}
