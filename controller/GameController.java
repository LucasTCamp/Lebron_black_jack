package controller;

import model.Game;
import model.PlayerCard;
import model.strategy.DealerStrategy;
import model.strategy.SafeStrategy;
import view.ConsoleView;
import java.util.Scanner;

/**
 * Handles the game loop and user input.
 * @author Lucas & Masoud
 */
public class GameController {
    private Game game;
    private ConsoleView view;
    private Scanner scanner;
    private DealerStrategy dealerStrategy;

    public GameController(Game game, ConsoleView view) {
        this.game = game;
        this.view = view;
        this.scanner = new Scanner(System.in);
        this.dealerStrategy = new SafeStrategy();
    }

    public void setDealerStrategy(DealerStrategy strategy) {
        this.dealerStrategy = strategy;
    }

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

            // Show available buff options
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
                // If they would bust, undo the bust
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

        // Results — check for multiplier
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