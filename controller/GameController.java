package controller;

import java.util.Scanner;
import model.Game;
import model.PlayerCard;
import model.strategy.DealerStrategy;
import model.strategy.SafeStrategy;
import view.ConsoleView;

/**
 * The GameController class manages the primary game loop, user interactions, 
 * and high-level game logic for Lebron Black Jack.
 * * It acts as the mediator between the Game model and the ConsoleView,
 * handling state transitions such as betting, player turns, dealer turns, 
 * and shop transactions.
 * * @author Lucas & Masoud
 */
public class GameController {
    /** The core game engine containing player data, deck, and collection. */
    private Game game;
    
    /** The view component responsible for displaying output to the user. */
    private ConsoleView view;
    
    /** Scanner for capturing user input from the terminal. */
    private Scanner scanner;
    
    /** Strategy object determining how the dealer hits or stays. */
    private DealerStrategy dealerStrategy;

    /**
     * Constructs a new GameController with the specified model and view.
     * Initializes the dealer with a {@link model.strategy.SafeStrategy}.
     * * @param game The game model instance.
     * @param view The console view instance.
     */
    public GameController(Game game, ConsoleView view) {
        this.game = game;
        this.view = view;
        this.scanner = new Scanner(System.in);
        this.dealerStrategy = new SafeStrategy();
    }

    /**
     * Updates the strategy used by the dealer during their turn.
     * * @param strategy The new {@link DealerStrategy} to be implemented.
     */
    public void setDealerStrategy(DealerStrategy strategy) {
        this.dealerStrategy = strategy;
    }

    /**
     * Starts and maintains the main game loop. 
     * Displays the welcome message and continues the game as long as the 
     * player has a positive balance and chooses to keep playing.
     */
    public void run() {
        view.displayMessage("=== Welcome to Lebron Black Jack! ===");
        view.displayMessage("Goal: Get to 23. Don't bust!");
        view.displayMessage("Starting balance: $" + game.getPlayer().getBalance());

        boolean playing = true;
        while (playing && game.getPlayer().getBalance() > 0) {
            playRound();

            if (game.getPlayer().getBalance() <= 0) {
                view.displayMessage("You're out of money. Game over!");
                break;
            }

            view.displayMessage("\nWhat do you want to do?");
            view.displayMessage("  1. Play another round");
            view.displayMessage("  2. Visit the shop");
            view.displayMessage("  3. View your collection");
            view.displayMessage("  4. Quit");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> {}
                case "2" -> openShop();
                case "3" -> game.getCollection().display();
                case "4" -> playing = false;
                default  -> view.displayMessage("Invalid choice, playing next round.");
            }
        }

        view.displayMessage("Thanks for playing! Final balance: $" + game.getPlayer().getBalance());
        view.displayMessage("Cards collected: " + game.getCollection().getOwned().size());
        game.getCollection().display();
    }

    /**
     * Opens the shop interface, allowing the player to view and purchase 
     * PlayerCards/Buffs using their balance.
     */
    private void openShop() {
        view.displayMessage("\n=== SHOP === (Balance: $" + game.getPlayer().getBalance() + ")");
        view.displayMessage("Available cards:");

        for (PlayerCard card : game.getShop().getAvailable()) {
            if (game.getCollection().owns(card)) {
                view.displayMessage("  [OWNED] " + card);
            } else {
                view.displayMessage("  " + card);
            }
        }

        view.displayMessage("\nType a player name to buy, or 'back' to return:");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("back")) return;

        PlayerCard card = game.getShop().findByName(input);

        if (card == null) {
            view.displayMessage("Player not found.");
        } else if (game.getCollection().owns(card)) {
            view.displayMessage("You already own " + card.getName() + "!");
        } else if (game.getPlayer().getBalance() < card.getPrice()) {
            view.displayMessage("Not enough money. You need $" + card.getPrice());
        } else {
            game.getPlayer().adjustBalance(-card.getPrice());
            game.getCollection().add(card);
            view.displayMessage("Got " + card.getName() + "! [" + card.getBuffDescription() + "]");
            view.displayMessage("Balance: $" + game.getPlayer().getBalance());
        }
    }

    /**
     * Executes a single round of Black Jack.
     * This includes:
     * <ul>
     * <li>Taking a bet</li>
     * <li>Processing pre-round buffs (e.g., Insurance)</li>
     * <li>The player's turn (Hit, Stay, Boost, or Free Hit)</li>
     * <li>The dealer's turn based on the current strategy</li>
     * <li>Determining the winner and applying payouts/multipliers</li>
     * </ul>
     */
    private void playRound() {
        view.displayMessage("\nYour Balance: $" + game.getPlayer().getBalance());

        view.displayMessage("Place your bet:");
        int bet;
        try {
            bet = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            view.displayMessage("Invalid bet. Try again.");
            return;
        }

        if (bet <= 0 || bet > game.getPlayer().getBalance()) {
            view.displayMessage("Invalid bet. Must be between $1 and $" + game.getPlayer().getBalance());
            return;
        }

        game.startRound();

        // Check for insurance before player turn
        boolean insuranceActive = false;
        if (game.getCollection().hasUnusedBuff("INSURANCE")) {
            view.displayMessage("You have Wemby's Insurance! Use it this round? (y/n)");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                game.getCollection().useFirstBuff("INSURANCE");
                insuranceActive = true;
                view.displayMessage("Insurance active — one bust saved!");
            }
        }

        // Player turn
        while (!game.getPlayer().isBust()) {
            view.displayMessage("\nYour Hand: " + game.getPlayer().getHand());
            view.displayMessage("Your Score: " + game.getPlayer().calculateScore());

            String options = "Hit or Stay? (h/s)";
            if (game.getCollection().hasUnusedBuff("SCORE_BOOST"))
                options += " or Boost? (b)";
            if (game.getCollection().hasUnusedBuff("FREE_HIT"))
                options += " or Free Hit? (f)";
            view.displayMessage(options);

            String action = scanner.nextLine().trim();

            if (action.equalsIgnoreCase("h")) {
                game.getPlayer().addCard(game.getDeck().draw());

            } else if (action.equalsIgnoreCase("b") && game.getCollection().hasUnusedBuff("SCORE_BOOST")) {
                PlayerCard used = game.getCollection().useFirstBuff("SCORE_BOOST");
                game.getPlayer().applyScoreBoost((int) used.getBuffValue());
                view.displayMessage(used.getName() + " activated! +" + (int) used.getBuffValue() + " to your score.");

            } else if (action.equalsIgnoreCase("f") && game.getCollection().hasUnusedBuff("FREE_HIT")) {
                game.getCollection().useFirstBuff("FREE_HIT");
                int scoreBefore = game.getPlayer().calculateScore();
                game.getPlayer().addCard(game.getDeck().draw());
                view.displayMessage("Donovan Mitchell activated! Free hit — no bust risk.");
                
                if (game.getPlayer().isBust()) {
                    game.getPlayer().removeScoreBoost(game.getPlayer().calculateScore() - scoreBefore);
                    view.displayMessage("Would have busted — Mitchell saved you! Score stays at " + scoreBefore);
                }
            } else {
                break;
            }
        }

        // Insurance saves a bust
        if (game.getPlayer().isBust() && insuranceActive) {
            view.displayMessage("BUSTED — but Wemby's Insurance saved you! Continuing...");
            game.getPlayer().clearBust();
        }

        if (game.getPlayer().isBust()) {
            game.getPlayer().adjustBalance(-bet);
            view.displayMessage("Bust! You hit " + game.getPlayer().calculateScore() + ". You lose $" + bet);
            view.displayMessage("Balance: $" + game.getPlayer().getBalance());
            return;
        }

        // Dealer turn
        view.displayMessage("\nDealer's Turn...");
        while (dealerStrategy.shouldHit(game.getDealer().calculateScore())) {
            game.getDealer().addCard(game.getDeck().draw());
        }

        // Results
        int pScore = game.getPlayer().calculateScore();
        int dScore = game.getDealer().calculateScore();
        view.displayMessage("Dealer Hand: " + game.getDealer().getHand());
        view.displayMessage("Dealer Score: " + dScore);

        double multiplier = 1.0;
        if (game.getCollection().hasUnusedBuff("MULTIPLIER")) {
            view.displayMessage("You have a payout multiplier! Use it? (y/n)");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                PlayerCard used = game.getCollection().useFirstBuff("MULTIPLIER");
                multiplier = used.getBuffValue();
                view.displayMessage(used.getName() + " activated! " + multiplier + "x payout!");
            }
        }

        if (dScore > 23 || pScore > dScore) {
            int winnings = (int)(bet * multiplier);
            game.getPlayer().adjustBalance(winnings);
            view.displayMessage("You Win! +$" + winnings);
        } else if (dScore > pScore) {
            game.getPlayer().adjustBalance(-bet);
            view.displayMessage("Dealer Wins! -$" + bet);
        } else {
            view.displayMessage("Push! Your bet is returned.");
        }

        view.displayMessage("Balance: $" + game.getPlayer().getBalance());
    }
}