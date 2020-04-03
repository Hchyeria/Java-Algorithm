package Recursion;

/*
 *  Evaluate a to the power of b, assuming both a and b are integers and b is non-negative.

Examples

power(2, 0) = 1
power(2, 3) = 8
power(0, 10) = 0
power(-2, 5) = -32
Corner Cases

 */

public class PowerAB {
    public long power(int a, int b) {
        // // Write your solution here
        // if(a == 0) {
        // return 0;
        // }
        // if(a == 1) {
        // return 1;
        // }
        // if(b == 0) {
        // return 1;
        // }
        // return a * power(a,b-1);

        if (a == 0) {
            return 1;
        }
        if (b == 0) {
            return 1;
        }
        // if you not store the halfResult
        // return power(a, b / 2) * power(a, b / 2)
        // there will still cause more time to do redundant recursion
        long halfResult = power(a, b / 2);
        if (b % 2 == 0) {
            return halfResult * halfResult;
        } else {
            return halfResult * halfResult * a;
        }
    }

    public double power2(int a, int b) {
        if (a == 0) {
            if (b <= 0) {
                throw new Error("invalid a and b");
            }
            return 0;
        }
        if (b < 0) {
            return 1 / (double)power(a, -b);
        } else {
            return power(a, b);
        }
    }
}
