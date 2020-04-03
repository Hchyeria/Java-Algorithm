package DynamicProgramming;

public class PalindromeRemoval {
    public int minimumMoves(int[] arr) {

        if (arr == null) {
            return 0;
        }
        int n = arr.length;
        if (n <= 1) {
            return n;
        }
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            dp[i][i] = 1;
        }

        for (int i = 0; i < n - 1; ++i) {
            dp[i][i + 1] = arr[i] == arr[i + 1] ? 1 : 2;
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 2; j < n; ++j) {
                if (arr[i] == arr[j]) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i; k < j; ++k) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
    
    public static void main(String[] args) {
        PalindromeRemoval palindromeRemoval = new PalindromeRemoval();
        int[] a = {1,3, 3, 1, 2, 2, 4 , 4};
        System.out.println(palindromeRemoval.minimumMoves(a));
    }
}
