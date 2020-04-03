package BitOperation;

public class NumberOfDifferentBits {
    public int diffBits(int a, int b) {
        int count = 0;
        for (int bit = a ^ b; bit != 0; bit >>>= 1) {
            count += bit & 1;
        }
        return count;
    }
}
