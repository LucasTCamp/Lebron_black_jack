package model;

/**
 * Represents a collectible NBA player card that provides unique gameplay advantages (buffs).
 * <p>
 * Each card has a purchase price, a flavor description, and a specific buff type 
 * that can be triggered during a round. Once a card's buff is consumed, it is 
 * marked as used.
 * </p>
 * * @author Lucas & Masoud
 */
public class PlayerCard {
    /** The name of the NBA player. */
    private final String name;
    
    /** The cost to purchase the card from the shop. */
    private final int price;
    
    /** A flavor text description of the card or player. */
    private final String description;
    
    /** * The category of buff provided by this card. 
     * Valid types: "SCORE_BOOST", "MULTIPLIER", "INSURANCE", "FREE_HIT". 
     */
    private final String buffType;
    
    /** The numerical value associated with the buff (e.g., boost amount or multiplier ratio). */
    private final double buffValue;
    
    /** Tracks whether the card's ability has been consumed in the current context. */
    private boolean used = false;

    /**
     * Constructs a new PlayerCard with the specified attributes.
     * * @param name        The name of the player.
     * @param price       The purchase cost.
     * @param description A brief description of the card.
     * @param buffType    The functional type of the buff.
     * @param buffValue   The magnitude of the buff effect.
     */
    public PlayerCard(String name, int price, String description, String buffType, double buffValue) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.buffType = buffType;
        this.buffValue = buffValue;
    }

    /** @return The name of the player on the card. */
    public String getName()        { return name; }

    /** @return The shop price of the card. */
    public int getPrice()          { return price; }

    /** @return The flavor text description. */
    public String getDescription() { return description; }

    /** @return The string identifier for the buff type. */
    public String getBuffType()    { return buffType; }

    /** @return The numerical value of the buff. */
    public double getBuffValue()   { return buffValue; }

    /** @return {@code true} if the buff has been used; {@code false} otherwise. */
    public boolean isUsed()        { return used; }

    /** Marks the card as used, preventing its buff from being triggered again. */
    public void markUsed()         { used = true; }

    /**
     * Translates the raw buff type and value into a human-readable explanation.
     * * @return A formatted string describing the card's special effect.
     */
    public String getBuffDescription() {
        return switch (buffType) {
            case "SCORE_BOOST"  -> "+" + (int) buffValue + " score boost";
            case "MULTIPLIER"   -> buffValue + "x payout multiplier";
            case "INSURANCE"    -> "Save from one bust";
            case "FREE_HIT"     -> "One free hit (no bust risk)";
            default             -> "Unknown buff";
        };
    }

    /**
     * Returns a formatted string containing the card's name, price, description, and buff.
     * * @return A complete summary of the player card.
     */
    @Override
    public String toString() {
        return name + " - $" + price + " | " + description + " [" + getBuffDescription() + "]";
    }
}