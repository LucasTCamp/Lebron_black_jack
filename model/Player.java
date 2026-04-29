package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles hand logic, scoring, balance, and the "23" scoring rule.
 * @author Lucas & Masoud
 */
public class Player {
    private final List<Card> hand = new ArrayList<>();
    private int balance;
    private int scoreBoost = 0;

    public Player(int startingBalance) { this.balance = startingBalance; }

    public void addCard(Card card) { if (card != null) hand.add(card); }

    public List<Card> getHand() { return hand; }

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

    public boolean isBust() { return calculateScore() > 23; }

    public int getBalance() { return balance; }

    public void adjustBalance(int amount) { this.balance += amount; }

    public void applyScoreBoost(int amount) { scoreBoost += amount; }

    public void removeScoreBoost(int amount) { scoreBoost -= amount; }

    public void resetBoost() { scoreBoost = 0; }

    public void clearBust() {
        // Remove highest value card to get back under 23
        hand.sort((a, b) -> b.getValue() - a.getValue());
        hand.remove(0);
    }
}