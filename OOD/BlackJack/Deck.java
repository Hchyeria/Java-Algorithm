package OOD.BlackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private static final Random random = new Random();
    private List<Card> deck;
    private int dealtIndex;

    public Deck() {
        deck = new ArrayList<>();
        dealtIndex = 0;
        for (int i = 1; i <= 13; i++) {
            for (Suit suit : Suit.values()) {
                deck.add(new Card(suit, i));
            }
        }
    }

    public void shuffle() {
        int size = deck.size();
        for (int i = 0; i < size; ++i) {
            int randomIndex = random.nextInt(size - i) + i;
            Card temp = deck.get(i);
            deck.set(i, deck.get(randomIndex ));
            deck.set(randomIndex , temp);
        }
    }

    private int remainingCards() {
        return deck.size() - dealtIndex;
    }

    public Card[] dealHand(int number) {
        if (this.remainingCards() < number) {
            return null;
        }
        Card[] cards = new Card[number];
        for (int i = 0; i < number; i++) {
            cards[i] = dealCard();
        }
        return cards;
    }

    private Card dealCard() {
        return deck.get(dealtIndex++);
    }
}
