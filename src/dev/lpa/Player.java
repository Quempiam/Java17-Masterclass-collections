package dev.lpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    private int handValue;
    private int score;
    private boolean stand;
    private boolean bust;
    private boolean blackJack;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
        score = 0;
        handValue = 0;
        stand = false;
        bust = false;
    }

    /**
     * Resets Players' hand for a new round
     */
    public void resetHand() {
        hand.clear();
        handValue = 0;
        stand = false;
        bust = false;
        blackJack = false;
    }

    /**
     * Resets whole Player for a new game
     */
    public void resetPlayer() {
        resetHand();
        score = 0;
    }

    public String getName() {
        return name;
    }

    public int getHandValue() {
        return handValue;
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        score++;
    }

    public boolean isStand() {
        return stand;
    }

    public boolean isBust() {
        return bust;
    }

    public void setStand() {
        this.stand = true;
    }

    public boolean isBlackJack() {
        return blackJack;
    }

    public Card getHighestCard() {
        if (hand.isEmpty()) {
            return null;
        }
        else {
            return hand.get(0);
        }
    }

    void draw(Card card) {
        hand.add(card);
        handValue += card.rank();
        if (handValue > 21) {
            bust = true;
            System.out.printf("Bust! %s, You are out of this round.\n", name);
        }
        else if (handValue == 21) {
            blackJack = true;
            System.out.println("BLACKJACK!");
        }
        Collections.sort(hand, Game.cardComparator);
    }

    void showHand() {
        System.out.printf("%s's hand:\n", name);
        Card.printDeck(hand, null, 1);
    }

}
