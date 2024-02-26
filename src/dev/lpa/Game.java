package dev.lpa;

import dev.utils.UserInput;

import java.util.*;

public class Game {
    private int roundNumber;
    private final List<Player> players;
    private final List<Card> gameDeck;
    public static final Comparator<Card> cardComparator =
            Comparator.comparing(Card::rank).reversed().thenComparing(Card::suit);
    private final Comparator<Player> playerByHighCardComparator =
            (p1, p2) -> cardComparator.compare(p1.getHighestCard(), p2.getHighestCard());
    private final Comparator<Player> playerByPointsComparator = Comparator.comparing(Player::getScore).reversed();
    private final Comparator<Player> playersByHandValueComparator = Comparator.comparing(Player::getHandValue).reversed();
    private Iterator<Card> deckIterator;

    public Game(String[] names) {
        roundNumber = 1;
        players = new ArrayList<>( names.length + 1);
        for (String name : names) {
            players.add(new Player(name));
        }
        players.add(new Dealer());

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

    /***
     * Deals a card to each player if they want to draw
     * @return true, if operation was successful, false otherwise
     */
    public boolean deal() {
        for (Player player : players) {
            if (player.isPlaying()){
                if (player instanceof Dealer dealer) {
                    System.out.println("=".repeat(20));
                    System.out.println("Dealer's turn");
//                    dealer.showHand();
                    if (dealer.wantDraw()) {
                        System.out.println("Dealer draws");
                        if (!dealCard(dealer)) return false;
                    }
                    else {
                        dealer.setStand();
                        System.out.println("Dealer stands");
                    }
                }
                else {
                    System.out.println("=".repeat(20));
                    player.showHand();
                    System.out.printf("%s, your hand value is %d.\n", player.getName(), player.getHandValue());
                    if (UserInput.getDrawOrStand()) {
                        if (!dealCard(player)) return false;
                        player.showHand();
                    }
                    else {
                        player.setStand();
                    }
                }
            }
        }
        return true;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void addRoundNumber() {
        roundNumber++;
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
        for (Player player : players) {
            if (player.isPlaying()) return false;
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
        Collections.sort(winnerList, playersByHandValueComparator.thenComparing(playerByHighCardComparator));
        return winnerList.isEmpty() ? null : winnerList.get(0);
    }

    public Player getGameWinner() {
        Collections.sort(players, playerByPointsComparator);
        return players.get(0);
    }
    private boolean hasPlayerBlackJack() {
        for (Player p : players) {
            if (p.isBlackJack()) return true;
        }
        return false;
    }

    public void printScore() {
        System.out.println(("*").repeat(20));
        System.out.println("Actual score:");
        players.forEach(player -> System.out.printf("%s - %d\n", player.getName(), player.getScore()));
        System.out.println(("*").repeat(20));
    }
}
