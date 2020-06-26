package BinarySearch;


public class FindInMountainArray {
    public static class MountainArray {
        int[] array;

        MountainArray(int[] array) {
            this.array = array;
        }

        int get(int index) {
            return array[index];
        }

        int length() {
            return array.length;
        }
    }
    public int findInMountainArray(int target, MountainArray mountainArr) {

        int n = mountainArr.length();
        return helper(target, mountainArr, 0, n - 1);
    }

    private int helper(int target, MountainArray array, int left, int right) {
        int start = left;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int l = mid == 0 ? Integer.MIN_VALUE : array.get(mid - 1);
            int r = mid == right ? Integer.MIN_VALUE : array.get(mid + 1);
            int midValue = array.get(mid);
            if (midValue == target) {
                int beforeIndex = helper(target, array, start, mid - 1);
                if (beforeIndex == -1) {
                    return mid;
                } else {
                    return beforeIndex;
                }
            } else if (midValue > l && midValue > r) {
                if (target > midValue) return -1;
                int leftIndex = helper(target, array, left , mid - 1);
                if (leftIndex != -1) {
                    return leftIndex;
                }
                return helper(target, array, mid + 1, right);
            } else if ((target > midValue && r > l)
                    || (target < midValue && r < l)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    // Time Complexity: O(log(n))
    // Space Complexity: O(1)

    public static void main(String[] args) {
        FindInMountainArray findInMountainArray = new FindInMountainArray();
        int[] a = {179,181,183,185,187,189,191,199,201,199,187,185,183,181,179,177,175,173,171,169,167,165,163};
        MountainArray mountainArray = new FindInMountainArray.MountainArray(a);
        System.out.println(findInMountainArray.findInMountainArray(181, mountainArray));
    }
}
