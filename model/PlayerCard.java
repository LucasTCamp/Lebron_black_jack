package model;

/**
 * Represents a collectable NBA player card with a buff.
 * @author Lucas & Masoud
 */
public class PlayerCard {
    private final String name;
    private final int price;
    private final String description;
    private final String buffType;  // "SCORE_BOOST", "MULTIPLIER", "INSURANCE", "FREE_HIT"
    private final double buffValue;
    private boolean used = false;

    public PlayerCard(String name, int price, String description, String buffType, double buffValue) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.buffType = buffType;
        this.buffValue = buffValue;
    }

    public String getName()        { return name; }
    public int getPrice()          { return price; }
    public String getDescription() { return description; }
    public String getBuffType()    { return buffType; }
    public double getBuffValue()   { return buffValue; }
    public boolean isUsed()        { return used; }
    public void markUsed()         { used = true; }

    public String getBuffDescription() {
        return switch (buffType) {
            case "SCORE_BOOST"  -> "+" + (int) buffValue + " score boost";
            case "MULTIPLIER"   -> buffValue + "x payout multiplier";
            case "INSURANCE"    -> "Save from one bust";
            case "FREE_HIT"     -> "One free hit (no bust risk)";
            default             -> "Unknown buff";
        };
    }

    @Override
    public String toString() {
        return name + " - $" + price + " | " + description + " [" + getBuffDescription() + "]";
    }
}