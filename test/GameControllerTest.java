// package test;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;
// import controller.GameController;
// import model.Game;
// import view.ConsoleView;
// import java.io.ByteArrayInputStream;
// import java.io.InputStream;

// public class GameControllerTest {

//     @Test
//     public void testControllerInitialization() {
//         Game game = new Game();
//         ConsoleView view = new ConsoleView();
//         GameController controller = new GameController(game, view);
        
//         // This hits the constructor and setter lines
//         assertNotNull(controller);
//         controller.setDealerStrategy(new model.strategy.AggressiveStrategy());
//     }

//     @Test
//     public void testRunAndQuit() {
//         Game game = new Game();
//         view.ConsoleView viewObj = new view.ConsoleView();
        
//         // Sequence: 
//         // "10" for the bet
//         // "s" to Stay immediately
//         // "4" to Quit the main loop
//         String input = "10\ns\n4\n"; 
        
//         InputStream in = new ByteArrayInputStream(input.getBytes());
//         System.setIn(in);

//         GameController controller = new GameController(game, viewObj);
//         controller.run();
        
//         System.setIn(System.in);
//     }
// }