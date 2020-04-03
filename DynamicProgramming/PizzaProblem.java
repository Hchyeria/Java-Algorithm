package DynamicProgramming;

public class PizzaProblem {
    // dp
    // do[i][j]: represents the maximum sum, from i-th to j-th pizza, including i and j
    // Initial:
    //      dp[i][i] = pizza[i]
    //      dp[i][i+1] = max(pizza[i], pizza[j])
    // Induction rule:
    //              Case 1: pick left
    //                  dp[i][j] = pizza[i] + dp[i+2][j], if pizza[i+1] > pizza[j]
    //                  dp[i][j] = pizza[i] + dp[i+1][j-1], else
    //              Case 2: pick right
    //                  dp[i][j] = pizza[j] + dp[i][j-2], if pizza[j-1] > pizza[i]
    //                  dp[i][j] = pizza[j] + dp[i+1][j-1], else
    //              dp[i][j] = max(case 1, case 2)
    public int maximumTotalSum(int[] pizza) {
        // valid check
        int n = pizza.length;
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                if (j - i == 0) {
                    dp[i][j] = pizza[i];
                } else if (j - i == 1) {
                    dp[i][j] = Math.max(pizza[i], pizza[j]);
                } else {
                    int temp1 = pizza[i];
                    if (pizza[i + 1] > pizza[j]) {
                        temp1 += dp[i + 2][j];
                    } else {
                        temp1 += dp[i + 1][j - 1];
                    }

                    int temp2 = pizza[j];
                    if (pizza[j - 1] > pizza[i]) {
                        temp2 += dp[i][j - 2];
                    } else {
                        temp2 += dp[i + 1][j - 1];
                    }
                    dp[i][j] = Math.max(temp1, temp2);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        PizzaProblem pizzaProblem = new PizzaProblem();
        int[] a = {2, 3, 4, 1, 6, 5};
        System.out.println(pizzaProblem.maximumTotalSum(a));
    }
}
