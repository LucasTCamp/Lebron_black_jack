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
        available.add(new PlayerCard("Anthony Edwards", 200,  "The next one. Pure buckets."));
        available.add(new PlayerCard("Nikola Jokic",    400,  "3x MVP. Does everything."));
        available.add(new PlayerCard("Luka Doncic",     500,  "Handles like butter."));
        available.add(new PlayerCard("Wemby",           600,  "7'3\" and moves like a guard."));
        available.add(new PlayerCard("Donovan Mitchell", 300,  "The Spida. Venom in the fourth quarter."));
        available.add(new PlayerCard("Kevin Durant",    800,  "Unstoppable scorer."));
        available.add(new PlayerCard("Stephen Curry",   900,  "Greatest shooter of all time."));
        available.add(new PlayerCard("LeBron James",    1500, "The King. Enough said."));
    }

    public List<PlayerCard> getAvailable() { return available; }

    public PlayerCard findByName(String name) {
        for (PlayerCard card : available) {
            if (card.getName().equalsIgnoreCase(name)) return card;
        }
        return null;
    }
}