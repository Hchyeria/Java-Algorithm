package Math;

// Given a random generator random5(), the return value of random5() is 0 - 4
// with equal probability. Use random5() to implement random1000()

public class RandomThousandUsingRandomFive {
    static class RandomTwo {
        static int random2() {
            return (int)(Math.random() * 2);
        }
    }

    public int random1000000() {
        while (true) {
            int value = 0;
            for (int i = 0; i < 20; ++i) {
                value = (value << 1) + RandomTwo.random2();
            }
            if (value < 1000000) {
                return value;
            }
        }
    }
}
