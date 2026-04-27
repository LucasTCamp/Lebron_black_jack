package controller;

import model.Game;
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

    public GameController(Game game, ConsoleView view) {
        this.game = game;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }
    
    public void run() {
        view.displayMessage("=== Welcome to Lebron Black Jack! ===");
        view.displayMessage("Goal: Get to 23. Don't bust!");
        
        boolean playing = true;
        while (playing) {
            playRound();
            view.displayMessage("\nPlay another round? (y/n)");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("y")) playing = false;
        }
        view.displayMessage("Thanks for playing!");
    }

    private void playRound() {
        game.startRound();
        
        // Player Turn
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
            view.displayMessage("Bust! You hit " + game.getPlayer().calculateScore() + ". Dealer wins.");
            return;
        }

        // Dealer Turn (Basic Logic)
        view.displayMessage("\nDealer's Turn...");
        while (game.getDealer().calculateScore() < 18) {
            game.getDealer().addCard(game.getDeck().draw());
        }

        // Final Results
        int pScore = game.getPlayer().calculateScore();
        int dScore = game.getDealer().calculateScore();

        view.displayMessage("Dealer Hand: " + game.getDealer().getHand());
        view.displayMessage("Dealer Score: " + dScore);

        if (dScore > 23 || pScore > dScore) {
            view.displayMessage("You Win!");
        } else if (dScore > pScore) {
            view.displayMessage("Dealer Wins!");
        } else {
            view.displayMessage("It's a Push (Tie)!");
        }
    }
}