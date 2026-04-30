package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a standard deck of 52 playing cards.
 * This class handles the initialization of cards across four suits,
 * shuffling logic, and drawing cards for gameplay.
 * * @author Lucas & Masoud
 */
public class Deck {
    /** The internal list of cards currently in the deck. */
    private final List<Card> cards = new ArrayList<>();

    /**
     * Constructs a new Deck containing 52 cards.
     * Each suit (Hearts, Diamonds, Clubs, Spades) is populated with 
     * ranks from 2 through Ace. Once the deck is built, it is 
     * automatically shuffled.
     */
    public Deck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                cards.add(new Card(suit, ranks[i], values[i]));
            }
        }
        shuffle();
    }

    /**
     * Randomizes the order of the cards remaining in the deck.
     */
    public void shuffle() { 
        Collections.shuffle(cards); 
    }

    /**
     * Removes and returns the top card from the deck.
     * * @return The {@link Card} drawn, or {@code null} if the deck is empty.
     */
    public Card draw() {
        return cards.isEmpty() ? null : cards.remove(0);
    }

    /**
     * Checks if there are any cards left in the deck.
     * * @return {@code true} if the deck is empty; {@code false} otherwise.
     */
    public boolean isEmpty() { 
        return cards.isEmpty(); 
    }
}