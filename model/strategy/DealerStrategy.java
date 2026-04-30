package model.strategy;

/**
 * Defines the contract for dealer AI behavior in the game.
 * Implementations of this interface determine the logic for whether 
 * the dealer should draw another card (hit) or stop (stay) based 
 * on their current score.
 * * @author Lucas & Masoud
 */
public interface DealerStrategy {
    
    /**
     * Decides if the dealer should take another card.
     * * @param currentScore The current total score of the dealer's hand.
     * @return {@code true} if the dealer should hit, {@code false} if they should stay.
     */
    boolean shouldHit(int currentScore);
}