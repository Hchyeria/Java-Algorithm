package BinarySearch;

public class Sqrtx {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        int left = 1;
        int right = x;

        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            long power = (long) mid * mid;
            if (power == x) {
                return mid;
            } else if (power < x) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        long rightPower = (long) right * right;
        if (rightPower <= x) {
            return right;
        } else {
            return left;
        }

    }

    public static void main(String[] args) {
        Sqrtx sqrtx = new Sqrtx();
        System.out.println(sqrtx.mySqrt(2147395599));
    }
}
