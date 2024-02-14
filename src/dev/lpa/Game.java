package dev.lpa;

import java.util.*;

public class Game {
    private List<Player> players;
    private int activePlayerIndex;
    private List<Card> gameDeck;

    public static final Comparator<Card> cardComparator = Comparator.comparing(Card::rank).thenComparing(Card::suit);
    private Iterator<Card> iterator;

    public Game(int numOfPlayers, String[] names) {
        players = new ArrayList<>(numOfPlayers + 1);
        players.add(new Dealer());
        for (int i = 0; i < numOfPlayers; i++) {
            players.add(new Player(names[i]));
        }
        activePlayerIndex = 0;
        gameDeck = Card.getStandardDeck();
        Collections.shuffle(gameDeck);
        iterator = gameDeck.listIterator();
    }

    boolean firstDeal() {
        for (Player p : players) {
            for (int i = 0; i < 2; i++) {
                if (iterator.hasNext()) {
                    p.draw(iterator.next());
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }

    boolean isGameOver() {
        for (Player p : players) {
            if (p.getScore() >= 3) {
                return true;
            }
        }
        return false;
    }

    void resetRound() {
        Collections.shuffle(gameDeck);
        iterator = gameDeck.listIterator();
    }
}
