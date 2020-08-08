package Other;

public class RangeSumQuery2DMutable {
    private int[][] A;
    private long[][] tree;

    private void add(int row, int col, int val) {
        int m = tree.length;
        int n = tree[0].length;
        int i = row;
        while (i < m) {
            // need to reset j to col at each loop
            int j = col;
            while (j < n) {
                tree[i][j] += val;
                j += (j & -j);
            }
            i += (i & -i);
        }
    }

    private long query(int row, int col) {
        long sum = 0;
        int i = row;
        while (i > 0) {
            // need to reset j to col at each loop
            int j = col;
            while (j > 0) {
                sum += tree[i][j];
                j -= (j & -j);
            }
            i -= (i & -i);
        }
        return sum;
    }

    public RangeSumQuery2DMutable(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        A = new int[m][n];
        tree = new long[m + 1][n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                add(i + 1, j + 1, matrix[i][j]);
                A[i][j] = matrix[i][j];
            }
        }
    }

    public void update(int row, int col, int val) {
        add(row + 1, col + 1, val - A[row][col]);
        A[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        long up = row1 == 0 ? 0 : query(row1, col2 + 1);
        long left = col1 == 0 ? 0 : query(row2 + 1, col1);
        long same = row1 == 0 || col1 == 0 ? 0 : query(row1, col1);
        return (int) (query(row2 + 1, col2 + 1) - up - left + same);
    }

    public static void main(String[] args) {
        int[][] a = {
                {3,0,1,4,2},
        {5,6,3,2,1},
            {1,2,0,1,5},
                {4,1,0,1,7},
                    {1,0,3,0,5}
        };
        RangeSumQuery2DMutable rangeSumQuery2DMutable = new RangeSumQuery2DMutable(a);
        System.out.println(rangeSumQuery2DMutable.sumRegion(2, 1, 4, 3));
        rangeSumQuery2DMutable.update(3, 2, 2);
        System.out.println(rangeSumQuery2DMutable.sumRegion(2, 1, 4, 3));
    }
}
