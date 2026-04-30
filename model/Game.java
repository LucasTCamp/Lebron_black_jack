package model;

/**
 * Acts as the Facade and central controller for the game's model layer.
 * This class coordinates the interactions between the player, the dealer,
 * the deck, the shop, and the player's card collection.
 * * <p>It serves as the "brain" of the model, maintaining the state of the 
 * current match and providing access to the game's core components.</p>
 * * @author Lucas & Masoud
 */
public class Game {
    /** The human player controlled by the user. */
    private final Player player;
    
    /** The automated dealer player. */
    private final Player dealer;
    
    /** The deck of cards used for the current game session. */
    private final Deck deck;
    
    /** The marketplace where players can purchase special cards. */
    private final Shop shop;
    
    /** The inventory of special cards/buffs the player has acquired. */
    private final Collection collection;

    /**
     * Initializes a new game session.
     * Sets up the player with a starting balance of $1000, initializes the dealer,
     * generates a fresh deck, and prepares the shop and collection.
     */
    public Game() {
        this.player = new Player(1000);
        this.dealer = new Player(0);
        this.deck = new Deck();
        this.shop = new Shop();
        this.collection = new Collection();
    }

    /**
     * Prepares the state for a new round of play.
     * This method:
     * <ul>
     * <li>Shuffles the deck if it is empty.</li>
     * <li>Clears the hands of both the player and the dealer.</li>
     * <li>Resets any active score boosts on the player.</li>
     * <li>Deals two initial cards to both the player and the dealer.</li>
     * </ul>
     */
    public void startRound() {
        if (deck.isEmpty()) deck.shuffle();
        player.getHand().clear();
        dealer.getHand().clear();
        player.resetBoost();
        player.addCard(deck.draw());
        dealer.addCard(deck.draw());
        player.addCard(deck.draw());
        dealer.addCard(deck.draw());
    }

    /** @return The {@link Player} object representing the user. */
    public Player getPlayer()         { return player; }

    /** @return The {@link Player} object representing the dealer. */
    public Player getDealer()         { return dealer; }

    /** @return The {@link Deck} currently in use. */
    public Deck getDeck()             { return deck; }

    /** @return The {@link Shop} containing available cards for purchase. */
    public Shop getShop()             { return shop; }

    /** @return The {@link Collection} of cards owned by the player. */
    public Collection getCollection() { return collection; }
}