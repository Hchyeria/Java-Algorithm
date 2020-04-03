package BitOperation;

// Determine if a given integer is power of 2.

public class PowerOfTwo {
    public boolean isPowerOfTwo(int number) {
        if (number <= 0) {
            return false;
        }
        int count = 0;
        while (number > 0) {
            count += number & 1;
            // >>> logic shift: fill 0
            // >> arithmetic shift: fill sign number
            number >>>= 1;
        }
        return count == 1;
    }
    // number:  00010000
    // num - 1: 00001111
    // &      : 00000000

    // number:  01010000
    // num - 1: 01001111
    // &      : 01000000 not 0
    public boolean isPowerOfTwo2(int number) {
        if (number <= 0) {
            return false;
        }
        return (number & (number - 1)) == 0;
    }
}
