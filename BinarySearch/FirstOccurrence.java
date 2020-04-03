package BinarySearch;

/*
 * Given a target integer T and an integer array A sorted in ascending order,
 * find the index of the first occurrence of T in A or return -1 if there is no such index.

    Assumptions

    There can be duplicate elements in the array.
    Examples

    A = {1, 2, 3}, T = 2, return 1
    A = {1, 2, 3}, T = 4, return -1
    A = {1, 2, 2, 2, 3}, T = 2, return 1
 */

/*
 * To find the first or last occurrence, better use double-pointer method
 * which is to make sure the real target(first or last occurrence) will not
 * be ruled out by the algorithm
 *
 *  So for the first occurrence, whenever array[mid] == target, we must let end = mid;
 *  if let start = mid, it is possible to rule out the first occurrence which is located
 *  before the original mid
 *
 */

public class FirstOccurrence {
    public int firstOccur(int[] array, int target) {
        if (array == null || (array.length == 1 && array[0] != target)) {
            return -1;
        }
        int left = 0, right = array.length -1;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (array[left] == target) {
            return left;
        } else if (array[right] == target) {
            return right;
        }
        return -1;
    }
}
