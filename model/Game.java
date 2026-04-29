package model;

/**
 * The Facade/Brain of the model. Coordinates the players and the deck.
 * @author Lucas & Masoud
 */
public class Game {
    private final Player player;
    private final Player dealer;
    private final Deck deck;
    private final Shop shop;
    private final Collection collection;

    public Game() {
        this.player = new Player(1000);
        this.dealer = new Player(0);
        this.deck = new Deck();
        this.shop = new Shop();
        this.collection = new Collection();
    }

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

    public Player getPlayer()         { return player; }
    public Player getDealer()         { return dealer; }
    public Deck getDeck()             { return deck; }
    public Shop getShop()             { return shop; }
    public Collection getCollection() { return collection; }
}