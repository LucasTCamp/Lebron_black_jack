package model.strategy;

public class AggressiveStrategy implements DealerStrategy {
    @Override
    public boolean shouldHit(int currentScore) { return currentScore < 21; }
}