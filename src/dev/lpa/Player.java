package dev.lpa;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> hand;
    private int handValue;
    private int score;
    private boolean stand;
    private boolean bust;

    public Player() {
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
    }

    /**
     * Resets whole Player for a new game
     */
    public void resetPlayer() {
        resetHand();
        score = 0;
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

    public void setStand(boolean stand) {
        this.stand = stand;
    }

    void draw(Card card) {
        hand.add(card);
//        update hand value here
    }


}
