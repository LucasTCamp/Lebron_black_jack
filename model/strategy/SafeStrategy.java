package model.strategy;

/**
 * A conservative implementation of the {@link DealerStrategy}.
 * Under this strategy, the dealer hits only when their score is below 18.
 * <p>
 * This approach prioritizes safety by reducing the likelihood of 
 * busting, though it may result in lower scores compared to more 
 * aggressive strategies.
 * </p>
 * * @author Lucas & Masoud
 */
public class SafeStrategy implements DealerStrategy {

    /**
     * Determines if the dealer should hit based on a conservative threshold.
     * * @param currentScore The dealer's current total score.
     * @return {@code true} if the score is less than 18; {@code false} otherwise.
     */
    @Override
    public boolean shouldHit(int currentScore) { 
        return currentScore < 18; 
    }
}