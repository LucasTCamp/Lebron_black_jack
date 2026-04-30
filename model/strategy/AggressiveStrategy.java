package model.strategy;

/**
 * An aggressive implementation of the {@link DealerStrategy}.
 * Under this strategy, the dealer will continue to take cards (hit) 
 * as long as their score is below 21. 
 * <p>
 * This maximizes the chance of hitting the target score of 23 but 
 * significantly increases the risk of busting.
 * </p>
 * * @author Lucas & Masoud
 */
public class AggressiveStrategy implements DealerStrategy {

    /**
     * Determines if the dealer should hit based on an aggressive threshold.
     * * @param currentScore The dealer's current total score.
     * @return {@code true} if the score is less than 21; {@code false} otherwise.
     */
    @Override
    public boolean shouldHit(int currentScore) { 
        return currentScore < 21; 
    }
}