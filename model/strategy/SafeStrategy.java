package model.strategy;

public class SafeStrategy implements DealerStrategy {
    @Override
    public boolean shouldHit(int currentScore) { return currentScore < 18; }
}