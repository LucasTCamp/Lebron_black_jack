import controller.GameController;
import model.Game;
import view.ConsoleView;

public class main {
    public static void main(String[] args) {
        Game game = new Game();
        ConsoleView view = new ConsoleView();
        GameController controller = new GameController(game, view);
        controller.run();
    }
}