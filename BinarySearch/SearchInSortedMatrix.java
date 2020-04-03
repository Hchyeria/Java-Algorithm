package BinarySearch;

/*
 * Given a 2D matrix that contains integers only, which each row is sorted in an ascending order.
 * The first element of next row is larger than (or equal to) the last element of previous row.

Given a target number, returning the position that the target locates within the matrix.
If the target number does not exist in the matrix, return {-1, -1}.

Assumptions:

The given matrix is not null, and has size of N * M, where N >= 0 and M >= 0.
Examples:

matrix = { {1, 2, 3}, {4, 5, 7}, {8, 9, 10} }

target = 7, return {1, 2}

target = 6, return {-1, -1} to represent the target number does not exist in the matrix.

 */
public class SearchInSortedMatrix {
    public int[] search(int[][] matrix, int target) {
        /*
         * The better solution which only uses binary search once
         * suppose matrix is:
         *     L[0][0]
         *      1 2 3 4
         *      5 6 7 8        target == 7
         *      9 10 11 12
         *               R[n - 1][m - 1]
         *
         *      start = 0;  end = m * n - 1;   mid = start + (end - start) / 2 = 0 + 11 / 2 = 5
         *      --> map 5 back to the matrix coordinates
         *       5: row = mid / col_num = 5 / 4 = 1
         *          col = mid % col_num = 5 % 4 = 1    ==>  {1,1}
         */

        if (matrix == null || matrix.length == 0) {
            return new int[] { -1, -1 };
        }

        if (matrix[0] == null || matrix[0].length == 0) {
            return new int[] { -1, -1 };
        }

        int m = matrix.length, n = matrix[0].length;
        int start = 0, end = n * m - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int row = mid / n;
            int cloum = mid % n;
            if (matrix[row][cloum] == target) {
                return new int[] {row, cloum};
            } else if (matrix[row][cloum] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return new int[] { -1, -1 };
    }
}
