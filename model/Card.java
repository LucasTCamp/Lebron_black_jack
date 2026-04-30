package model;

/**
 * Represents a standard playing card used in the game. 
 * Special logic is applied to the "King" rank, which is referred to 
 * as "Lebron" within this game's context.
 * * @author Lucas & Masoud
 */
public class Card {
    /** The suit of the card (e.g., Hearts, Diamonds, Spades, Clubs). */
    private final String suit;
    
    /** The rank of the card (e.g., Ace, 2-10, Jack, Queen, King). */
    private final String rank;
    
    /** The numerical value of the card used for score calculation. */
    private final int value;

    /**
     * Constructs a Card with a specified suit, rank, and point value.
     * * @param suit  The suit of the card.
     * @param rank  The rank of the card.
     * @param value The integer value of the card.
     */
    public Card(String suit, String rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    /**
     * Gets the numerical value of the card.
     * * @return The card's point value.
     */
    public int getValue() { 
        return value; 
    }

    /**
     * Returns a string representation of the card.
     * If the rank is "King", it is displayed as "Lebron".
     * * @return A formatted string (e.g., "Lebron of Hearts" or "7 of Spades").
     */
    @Override
    public String toString() {
        String displayRank = rank.equalsIgnoreCase("King") ? "Lebron" : rank;
        return displayRank + " of " + suit;
    }
}