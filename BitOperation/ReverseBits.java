package BitOperation;

//Reverse all bits of an integer.

public class ReverseBits {
    public int reverseBits(int num) {
        for (int left = 31, right = 0; left > right; --left, ++right) {
            int leftBit = (num >>> left) & 1;
            int rightBit = (num >>> right) & 1;
            if (leftBit != rightBit) {
                num ^= (1 << left) | (1 << right);
            }
        }
        return num;
    }
}
