package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a participant in the game, which can be either a human player 
 * or a dealer. 
 * <p>
 * This class manages the player's hand, financial balance, and specific 
 * scoring mechanics, including handling Aces and the custom bust 
 * threshold of 23.
 * </p>
 * * @author Lucas & Masoud
 */
public class Player {
    /** The list of cards currently held by the player. */
    private final List<Card> hand = new ArrayList<>();
    
    /** The player's current monetary balance. */
    private int balance;
    
    /** A modifier applied to the total score, typically from power-up cards. */
    private int scoreBoost = 0;

    /**
     * Constructs a Player with a specified starting balance.
     * * @param startingBalance The initial amount of money the player has.
     */
    public Player(int startingBalance) { 
        this.balance = startingBalance; 
    }

    /**
     * Adds a card to the player's hand.
     * * @param card The {@link Card} to be added.
     */
    public void addCard(Card card) { 
        if (card != null) hand.add(card); 
    }

    /**
     * Retrieves the current hand of the player.
     * * @return A list of {@link Card} objects.
     */
    public List<Card> getHand() { 
        return hand; 
    }

    /**
     * Calculates the player's total score based on the cards in their hand 
     * and any active score boosts.
     * <p>
     * Follows standard Black Jack Ace logic adapted for a target of 23:
     * Aces are valued at 11 unless they cause the score to exceed 23, 
     * in which case they are valued at 1.
     * </p>
     * * @return The calculated point total.
     */
    public int calculateScore() {
        int score = scoreBoost;
        int aces = 0;
        for (Card c : hand) {
            score += c.getValue();
            if (c.getValue() == 11) aces++;
        }
        
        // Custom rule: Bust is 24+, Goal is 23
        while (score > 23 && aces > 0) {
            score -= 10;
            aces--;
        }
        return score;
    }

    /**
     * Determines if the player's current score exceeds the bust limit.
     * * @return {@code true} if the score is greater than 23; {@code false} otherwise.
     */
    public boolean isBust() { 
        return calculateScore() > 23; 
    }

    /**
     * Gets the player's current balance.
     * * @return The total money available to the player.
     */
    public int getBalance() { 
        return balance; 
    }

    /**
     * Modifies the player's balance.
     * * @param amount The amount to add (positive) or subtract (negative).
     */
    public void adjustBalance(int amount) { 
        this.balance += amount; 
    }

    /**
     * Increases the score modifier for the current round.
     * * @param amount The value to add to the boost.
     */
    public void applyScoreBoost(int amount) { 
        scoreBoost += amount; 
    }

    /**
     * Decreases the score modifier. Useful for undoing actions or 
     * specific buff logic.
     * * @param amount The value to subtract from the boost.
     */
    public void removeScoreBoost(int amount) { 
        scoreBoost -= amount; 
    }

    /**
     * Resets the score modifier back to zero.
     */
    public void resetBoost() { 
        scoreBoost = 0; 
    }

    /**
     * Action to bring a player back from a bust state.
     * Sorts the hand and removes the card with the highest value to 
     * attempt to bring the total score back under 23.
     */
    public void clearBust() {
        hand.sort((a, b) -> b.getValue() - a.getValue());
        hand.remove(0);
    }
}