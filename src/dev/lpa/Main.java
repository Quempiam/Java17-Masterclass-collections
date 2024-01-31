package dev.lpa;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Card> deck = Card.getStandardDeck();
        Card.printDeck(deck);

        Collections.reverse(deck);
        Card.printDeck(deck, "reversed", 4);

        Collections.shuffle(deck);
        Card.printDeck(deck, "shuffled", 4);

        Comparator<Card> rankSorter = Comparator.comparing(Card::rank).thenComparing(Card::suit);
        Collections.sort(deck, rankSorter);
        Card.printDeck(deck, "sorted by rank and suit", 4);

        Comparator<Card> suitSorter = Comparator.comparing(Card::suit).thenComparing(Card::rank);
        Collections.sort(deck, suitSorter);
        Card.printDeck(deck, "sorted by suit and rank", 5);

        var kingOfSpades = Card.getFaceCard(Card.Suit.SPADE, 'K');
//        int kingIndex = Collections.binarySearch(deck, kingOfSpades, suitSorter);
        int kingIndex = deck.indexOf(kingOfSpades);
        System.out.println("index of king = " + kingIndex);
        System.out.println(deck.get(kingIndex));

        Collections.replaceAll(deck, Card.getFaceCard(Card.Suit.CLUB, 'J'), kingOfSpades);
        System.out.println("Amount of kings of spades: " +Collections.frequency(deck, kingOfSpades));
    }
}
