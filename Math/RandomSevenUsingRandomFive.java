package Math;

// Given a random generator random5(), the return value of random5() is 0 - 4 with equal probability.
// Use random5() to implement random7()

public class RandomSevenUsingRandomFive {
    static class RandomFive {
        static int random5() {
            return (int)(Math.random() * 5);
        }
    }

    public int random7() {
        while (true) {
            int index = RandomFive.random5() * 5 + RandomFive.random5();
            if (index < 21) {
                return index % 7;
            }
        }
    }
}
