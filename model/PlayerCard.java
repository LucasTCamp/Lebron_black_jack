package model;

/**
 * Represents a collectable NBA player card.
 * @author Lucas & Masoud
 */
public class PlayerCard {
    private final String name;
    private final int price;
    private final String description;

    public PlayerCard(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return name + " - $" + price + " | " + description;
    }
}