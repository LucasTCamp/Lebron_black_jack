package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains all buyable NBA player cards.
 * @author Lucas & Masoud
 */
public class Shop {
    private final List<PlayerCard> available = new ArrayList<>();

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

    public List<PlayerCard> getAvailable() { return available; }

    public PlayerCard findByName(String name) {
        for (PlayerCard card : available) {
            if (card.getName().equalsIgnoreCase(name)) return card;
        }
        return null;
    }
}