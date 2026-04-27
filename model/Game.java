package model;

/**
 * The Facade/Brain of the model. Coordinates the players and the deck.
 * @author Lucas & Masoud
 */
public class Game {
    private final Player player;
    private final Player dealer;
    private final Deck deck;

    public Game() {
        this.player = new Player(1000); // Starting cash
        this.dealer = new Player(0);    
        this.deck = new Deck();
    }

    public void startRound() {
        player.getHand().clear();
        dealer.getHand().clear();
        player.addCard(deck.draw());
        dealer.addCard(deck.draw());
        player.addCard(deck.draw());
        dealer.addCard(deck.draw());
    }

    public Player getPlayer() { return player; }
    public Player getDealer() { return dealer; }
    
    // ADD THIS PART TO FIX THE ERROR
    public Deck getDeck() { return deck; }
}