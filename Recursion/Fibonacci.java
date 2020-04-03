package Recursion;
/**
 *  Get the Kth number in the Fibonacci Sequence. (K is 0-indexed, the 0th Fibonacci number is 0 and the 1st Fibonacci number is 1).

Examples

0th fibonacci number is 0
1st fibonacci number is 1
2nd fibonacci number is 1
3rd fibonacci number is 2
6th fibonacci number is 8
 */

public class Fibonacci {
    public long fibonacci(int K) {
        // Write your solution here

        //Recursion whose time complexity is quite big O(n^2)
        // if(K < 0) {
        //   return 0;
        // }
        // if(K == 0) {
        //   return 0;
        // } else if(K == 1) {
        //   return 1;
        // } else {
        //   return fibonacci(K - 2 ) + fibonacci(K - 1);
        // }

        //DP
        long a = 0;
        long b = 1;
        for (int i = 0; i < K; i++) {
            long c = a + b;
            a = b;
            b = c;
        }
        return a;
    }
}
