package dev.lpa;

import dev.utils.UserInput;

import java.util.*;

public class Game {
    private List<Player> players;
    private int activePlayerIndex;
    private List<Card> gameDeck;
    public static final Comparator<Card> cardComparator = Comparator.comparing(Card::rank).thenComparing(Card::suit);
    private final Comparator<Player> playerComparator =
            (p1, p2) -> cardComparator.compare(p1.getHighestCard(), p2.getHighestCard());
    private Iterator<Card> deckIterator;

    public Game(String[] names) {
        players = new ArrayList<>( names.length + 1);
        players.add(new Dealer());
        for (String name : names) {
            players.add(new Player(name));
        }
        activePlayerIndex = 0;
        gameDeck = Card.getStandardDeck();
        Collections.shuffle(gameDeck);
        deckIterator = gameDeck.listIterator();
    }

    private boolean dealCard(Player player) {
        int count = 0;
        while (!deckIterator.hasNext()) {
            resetDeck();
            count++;
            if (count >3) return false;
        }
        player.draw(deckIterator.next());
        return true;
    }

    public boolean firstDeal() {
        for (Player player : players) {
            for (int i = 0; i < 2; i++) {
                if (!dealCard(player)) return false;
            }
        }
        return true;
    }

    public boolean deal() {
        for (Player player : players) {
            if (!player.isStand()){
                if (player instanceof Dealer dealer) {
                    if (dealer.wantDraw()) {
                        if (!dealCard(dealer)) return false;
                    }
                    else {
                        dealer.setStand();
                    }
                }
                else {
                    System.out.println("=".repeat(20));
                    player.showHand();
                    System.out.printf("%s, your hand value is %d.\n", player.getName(), player.getHandValue());
                    if (UserInput.getDrawOrStand()) {
                        if (!dealCard(player)) return false;
                    }
                    else {
                        player.setStand();
                    }
                }
            }
        }
        return true;
    }

    public boolean isGameOver() {
        for (Player p : players) {
            if (p.getScore() >= 3) {
                return true;
            }
        }
        return false;
    }

    public boolean isRoundOver() {
        if (hasPlayerBlackJack()) return true;
        for (Player player :players) {
            if (!player.isStand() && !player.isBust()) return false;
        }
        return true;
    }

    private void resetDeck() {
        Collections.shuffle(gameDeck);
        deckIterator = gameDeck.listIterator();
    }

    public void resetRound() {
        resetDeck();
        players.forEach(Player::resetHand);
    }

    public Player getRoundWinner() {
        List<Player> winnerList = new ArrayList<>(players.size());
        players.forEach(player -> {
            if (!player.isBust()) winnerList.add(player);
        });
        Collections.sort(winnerList, playerComparator);
        return winnerList.get(0);
    }

    private boolean hasPlayerBlackJack() {
        for (Player p : players) {
            if (p.isBlackJack()) return true;
        }
        return false;
    }
}
