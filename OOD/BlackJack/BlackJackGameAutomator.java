package OOD.BlackJack;

import java.util.ArrayList;
import java.util.List;

public class BlackJackGameAutomator {
    private Deck deck;
    private BlackJackHand[] players;
    private final static int HIT_UNTIL = 16;

    public BlackJackGameAutomator(int playerNum) {
        players = new BlackJackHand[playerNum];
        for (int i = 0; i < playerNum; ++i) {
            players[i] = new BlackJackHand();
        }
    }

    void initialGame() {
        deck = new Deck();
        deck.shuffle();
    }

    boolean dealInitial() {
        for (BlackJackHand player : players) {
            Card[] cards = deck.dealHand(2);
            if (cards == null) {
                return false;
            }
            player.addHand(cards);
        }
        return true;
    }

    List<Integer> getBlackJacks() {
        List<Integer> winner = new ArrayList<>();
        for (int i = 0; i < players.length; ++i) {
            if (players[i].isBlackJack()) {
                winner.add(i);
            }
        }
        return winner;
    }

    boolean playHand(BlackJackHand player) {
        while (player.score() < HIT_UNTIL) {
            Card[] cards = deck.dealHand(1);
            if (cards == null) {
                return false;
            }
            player.addHand(cards);
        }
        return true;
    }

    boolean playAllHand() {
        for (BlackJackHand player : players) {
            if (!playHand(player)) {
                return false;
            }
        }
        return true;
    }

    List<Integer> getWinner() {
        List<Integer> winner = new ArrayList<>();
        int winScore = 0;
        for (int i = 0; i < players.length; ++i) {
            BlackJackHand player = players[i];
            if (!player.busted()) {
                int s = player.score();
                if (s > winScore) {
                    winner.clear();
                    winner.add(i);
                    winScore = s;
                } else if (s == winScore) {
                    winner.add(i);
                }
            }
        }
        return winner;
    }

    void printPlayersAndScores() {
        int index = 1;
        for (BlackJackHand player : players) {
            System.out.println("Player " + index++ + "Score: " + player.score());
            player.printCards();
        }
    }

    void simulate() {
        initialGame();
        boolean success = dealInitial();
        if (!success) {
            System.out.print("Error: Run out of cards.");
        } else {
            System.out.println("---Initial----");
            printPlayersAndScores();
            List<Integer> blackJack = getBlackJacks();
            if (blackJack.size() > 0) {
                System.out.print("Black Jack lies in:");
                for (int i : blackJack) {
                    System.out.print(i + " ");
                }
                System.out.println();
                System.out.println("done.");
            } else {
                success = playAllHand();
                if (!success) {
                    System.out.println("Error: cards out of bound.");
                }
                System.out.println("---Completed Game---");
                printPlayersAndScores();
                List<Integer> winners = getWinner();
                if (winners.size() > 0) {
                    System.out.println("Winners:");
                    for (int i : winners) {
                        System.out.println(i + "");
                    }
                    System.out.println();
                } else {
                    System.out.println("Draw. All players have busted.");
                }
            }
        }
    }

    public static void main(String args[]) {
        BlackJackGameAutomator automator = new BlackJackGameAutomator(5);
        automator.simulate();
    }

}
