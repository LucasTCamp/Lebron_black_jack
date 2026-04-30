package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the marketplace where players can purchase {@link PlayerCard} items.
 * <p>
 * The shop initializes a static catalog of NBA player cards, each with 
 * a specific cost and in-game buff. It provides methods to browse the 
 * inventory and find specific cards by name.
 * </p>
 * * @author Lucas & Masoud
 */
public class Shop {
    /** The list of player cards available for purchase. */
    private final List<PlayerCard> available = new ArrayList<>();

    /**
     * Constructs a new Shop and populates it with a predefined set of 
     * NBA player cards.
     * <p>
     * Cards range from entry-level boosts (e.g., Anthony Edwards) to 
     * elite high-cost multipliers (e.g., LeBron James).
     * </p>
     */
    public Shop() {
        available.add(new PlayerCard("Anthony Edwards", 1000,  "The next one. Pure buckets.",               "SCORE_BOOST", 1));
        available.add(new PlayerCard("Donovan Mitchell", 2000,  "The Spida. Venom in the fourth quarter.",  "FREE_HIT",    1));
        available.add(new PlayerCard("Nikola Jokic",    3500,  "3x MVP. Does everything.",                 "SCORE_BOOST", 2));
        available.add(new PlayerCard("Luka Doncic",     6000,  "Handles like butter.",                     "MULTIPLIER",  1.5));
        available.add(new PlayerCard("Wemby",           10000, "7'3\" and moves like a guard.",             "INSURANCE",   1));
        available.add(new PlayerCard("Kevin Durant",    18000, "Unstoppable scorer.",                       "MULTIPLIER",  2));
        available.add(new PlayerCard("Stephen Curry",   30000, "Greatest shooter of all time.",             "SCORE_BOOST", 3));
        available.add(new PlayerCard("LeBron James",    50000, "The King. Enough said.",                    "MULTIPLIER",  3));
    }

    /**
     * Retrieves the catalog of cards offered in the shop.
     * * @return A list of all available {@link PlayerCard} objects.
     */
    public List<PlayerCard> getAvailable() { 
        return available; 
    }

    /**
     * Searches for a card in the shop's inventory by its player name.
     * * @param name The name of the player card to search for (case-insensitive).
     * @return The matching {@link PlayerCard}, or {@code null} if no card with that name exists.
     */
    public PlayerCard findByName(String name) {
        for (PlayerCard card : available) {
            if (card.getName().equalsIgnoreCase(name)) return card;
        }
        return null;
    }
}