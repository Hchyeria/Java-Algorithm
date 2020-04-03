package OOD.BlackJack;

public class Card {
    // 1 for A, 11 for J, 12 for Q, 13 for K, or we can use enum here
    private final Suit face;
    private final int value;

    public Card(Suit face, int value) {
        this.face = face;
        this.value = value;
    }

    public Suit getFace() {
        return face;
    }
    public int getValue() {
        return value;
    }

    public String toString() {
        return "Face: " + this.face + ", Value: " + this.value;
    }
}
