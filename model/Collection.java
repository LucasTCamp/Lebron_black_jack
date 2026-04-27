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