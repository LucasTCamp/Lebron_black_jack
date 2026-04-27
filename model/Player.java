package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles hand logic and the "23" scoring rule.
 * @author Lucas & Masoud
 */
public class Player {
    private final List<Card> hand = new ArrayList<>();
    private int balance;

    public Player(int startingBalance) { this.balance = startingBalance; }

    public void addCard(Card card) { if (card != null) hand.add(card); }
    
    public List<Card> getHand() { return hand; }

    public int calculateScore() {
        int score = 0;
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
}