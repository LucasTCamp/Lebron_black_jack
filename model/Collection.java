package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the inventory of {@link PlayerCard} objects acquired by the player.
 * This class handles the logic for checking ownership, retrieving available 
 * power-ups (buffs), and marking them as used during gameplay.
 * * @author Lucas & Masoud
 */
public class Collection {
    /** The list of player cards currently owned by the user. */
    private final List<PlayerCard> owned = new ArrayList<>();

    /**
     * Adds a new player card to the player's collection.
     * * @param card The {@link PlayerCard} to be added.
     */
    public void add(PlayerCard card) { 
        owned.add(card); 
    }

    /**
     * Checks if a specific card is already in the collection based on its name.
     * * @param card The card to check for.
     * @return {@code true} if a card with the same name exists; {@code false} otherwise.
     */
    public boolean owns(PlayerCard card) {
        for (PlayerCard c : owned) {
            if (c.getName().equalsIgnoreCase(card.getName())) return true;
        }
        return false;
    }

    /**
     * Retrieves the full list of cards owned by the player.
     * * @return A list of all {@link PlayerCard} objects in the collection.
     */
    public List<PlayerCard> getOwned() { 
        return owned; 
    }

    /**
     * Filters the collection for unused cards of a specific buff category.
     * * @param buffType The string identifier of the buff type (e.g., "INSURANCE").
     * @return A list of {@link PlayerCard} objects that match the type and are not yet used.
     */
    public List<PlayerCard> getAvailableBuffs(String buffType) {
        List<PlayerCard> result = new ArrayList<>();
        for (PlayerCard c : owned) {
            if (c.getBuffType().equals(buffType) && !c.isUsed()) result.add(c);
        }
        return result;
    }

    /**
     * Determines if the player has at least one unused card of the specified buff type.
     * * @param buffType The string identifier of the buff type.
     * @return {@code true} if an available buff exists; {@code false} otherwise.
     */
    public boolean hasUnusedBuff(String buffType) {
        return !getAvailableBuffs(buffType).isEmpty();
    }

    /**
     * Finds the first available card of a specific buff type, marks it as used, 
     * and returns it. This effectively consumes the buff for the round.
     * * @param buffType The string identifier of the buff type.
     * @return The {@link PlayerCard} used, or {@code null} if no available buff was found.
     */
    public PlayerCard useFirstBuff(String buffType) {
        for (PlayerCard c : owned) {
            if (c.getBuffType().equals(buffType) && !c.isUsed()) {
                c.markUsed();
                return c;
            }
        }
        return null;
    }

    /**
     * Resets the usage status of buffs between rounds.
     * <p>
     * <i>Note: Implementation is currently a placeholder for per-round 
     * logic vs permanent cards.</i>
     * </p>
     */
    public void resetBuffs() {
        for (PlayerCard c : owned) {
            // Only reset per-round buffs, not permanent ones
        }
    }

    /**
     * Prints the contents of the collection to the console.
     * Displays a message if the collection is empty.
     */
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