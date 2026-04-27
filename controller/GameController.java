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
            view.displayMessage("Got " + card.getName() + "! Balance: $" + game.getPlayer().getBalance());
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

        while (!game.getPlayer().isBust()) {
            view.displayMessage("\nYour Hand: " + game.getPlayer().getHand());
            view.displayMessage("Your Score: " + game.getPlayer().calculateScore());
            view.displayMessage("Hit or Stay? (h/s)");

            String action = scanner.nextLine();
            if (action.equalsIgnoreCase("h")) {
                game.getPlayer().addCard(game.getDeck().draw());
            } else {
                break;
            }
        }

        if (game.getPlayer().isBust()) {
            game.getPlayer().adjustBalance(-bet);
            view.displayMessage("Bust! You hit " + game.getPlayer().calculateScore() + ". You lose $" + bet);
            view.displayMessage("Balance: $" + game.getPlayer().getBalance());
            return;
        }

        view.displayMessage("\nDealer's Turn...");
        while (dealerStrategy.shouldHit(game.getDealer().calculateScore())) {
            game.getDealer().addCard(game.getDeck().draw());
        }

        int pScore = game.getPlayer().calculateScore();
        int dScore = game.getDealer().calculateScore();
        view.displayMessage("Dealer Hand: " + game.getDealer().getHand());
        view.displayMessage("Dealer Score: " + dScore);

        if (dScore > 23 || pScore > dScore) {
            game.getPlayer().adjustBalance(bet);
            view.displayMessage("You Win! +$" + bet);
        } else if (dScore > pScore) {
            game.getPlayer().adjustBalance(-bet);
            view.displayMessage("Dealer Wins! -$" + bet);
        } else {
            view.displayMessage("Push! Your bet is returned.");
        }

        view.displayMessage("Balance: $" + game.getPlayer().getBalance());
    }
}