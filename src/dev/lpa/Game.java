package dev.lpa;

import java.util.*;

public class Game {
    private List<Player> players;
    private int activePlayerIndex;
    private List<Card> gameDeck;

    public static final Comparator<Card> cardComparator = Comparator.comparing(Card::rank).thenComparing(Card::suit);
    private final Comparator<Player> playerComparator =
            (p1, p2) -> cardComparator.compare(p1.getHighestCard(), p2.getHighestCard());

    private Iterator<Card> deckIterator;

    public Game(int numOfPlayers, String[] names) {
        players = new ArrayList<>(numOfPlayers + 1);
        players.add(new Dealer());
        for (int i = 0; i < numOfPlayers; i++) {
            players.add(new Player(names[i]));
        }
        activePlayerIndex = 0;
        gameDeck = Card.getStandardDeck();
        Collections.shuffle(gameDeck);
        deckIterator = gameDeck.listIterator();
    }

    public void firstDeal() {
        players.forEach(player -> {
            for (int i = 0; i < 2; i++) {
                if (deckIterator.hasNext()) {
                    player.draw(deckIterator.next());
                } else {
                    resetRound();
                }
            }
        });
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
        deckIterator = gameDeck.listIterator();
    }

    public Player getRoundWinner() {
        List<Player> winnerList = new ArrayList<>(players.size());
        players.forEach(player -> {
            if (!player.isBust()) winnerList.add(player);
        });
        Collections.sort(winnerList, playerComparator);
        return winnerList.get(0);
    }

    public boolean hasPlayerBlackJack() {
        for (Player p : players) {
            if (p.isBlackJack()) return true;
        }
        return false;
    }
}
