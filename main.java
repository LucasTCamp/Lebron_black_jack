import controller.GameController;
import model.Game;
import view.ConsoleView;

/**
 * The entry point for the Lebron Black Jack application.
 * This class handles the initial setup (bootstrapping) of the Model, View, 
 * and Controller components and starts the game's execution.
 * * @author Lucas & Masoud
 */
public class main {

    /**
     * Main method that serves as the starting point of the program.
     * It instantiates the {@link Game} model, the {@link ConsoleView}, 
     * and the {@link GameController}, then hands control over to the 
     * controller to begin the game loop.
     */
    public static void main(String[] args) {
        Game game = new Game();
        ConsoleView view = new ConsoleView();
        GameController controller = new GameController(game, view);
        controller.run();
    }
}