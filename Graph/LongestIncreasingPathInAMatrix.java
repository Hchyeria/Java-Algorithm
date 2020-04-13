package Graph;

public class LongestIncreasingPathInAMatrix {
    private int m;
    private int n;
    private int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int longestIncreasingPath(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        m = matrix.length;
        n = matrix[0].length;
        int[][] cache = new int[m][n];
        int max = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                max = Math.max(max, dfs(matrix, i, j, cache));
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] > 0) {
            return cache[i][j];
        }

        int len = 1;
        for (int[] d : DIRS) {
            int x = i + d[0];
            int y = j + d[1];
            if (isArea(x, y) && matrix[x][y] > matrix[i][j]) {
                len = Math.max(len, 1 + dfs(matrix, x, y, cache));
            }
        }
        cache[i][j] = len;

        return len;
    }

    private boolean isArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args) {
        LongestIncreasingPathInAMatrix longestIncreasingPathInAMatrix = new LongestIncreasingPathInAMatrix();
        int[][] a = {{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(longestIncreasingPathInAMatrix.longestIncreasingPath(a));
    }
}
