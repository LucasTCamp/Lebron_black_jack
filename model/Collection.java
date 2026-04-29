package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Tracks which player cards the player has collected.
 * @author Lucas & Masoud
 */
public class Collection {
    private final List<PlayerCard> owned = new ArrayList<>();

    public void add(PlayerCard card) { owned.add(card); }

    public boolean owns(PlayerCard card) {
        for (PlayerCard c : owned) {
            if (c.getName().equalsIgnoreCase(card.getName())) return true;
        }
        return false;
    }

    public List<PlayerCard> getOwned() { return owned; }

    // Get all unused cards of a specific buff type
    public List<PlayerCard> getAvailableBuffs(String buffType) {
        List<PlayerCard> result = new ArrayList<>();
        for (PlayerCard c : owned) {
            if (c.getBuffType().equals(buffType) && !c.isUsed()) result.add(c);
        }
        return result;
    }

    public boolean hasUnusedBuff(String buffType) {
        return !getAvailableBuffs(buffType).isEmpty();
    }

    // Get first unused card of a buff type and mark it used
    public PlayerCard useFirstBuff(String buffType) {
        for (PlayerCard c : owned) {
            if (c.getBuffType().equals(buffType) && !c.isUsed()) {
                c.markUsed();
                return c;
            }
        }
        return null;
    }

    // Reset all buffs between rounds
    public void resetBuffs() {
        for (PlayerCard c : owned) {
            // Only reset per-round buffs, not permanent ones
        }
    }

    public void display() {
        if (owned.isEmpty()) {
            System.out.println("No cards collected yet.");
            return;
        }
        System.out.println("=== Your Collection ===");
        for (PlayerCard card : owned) {
            System.out.println("  * " + card);
        }
    }
}