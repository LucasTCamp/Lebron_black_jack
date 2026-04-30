// package test;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;
// import model.Game;

// public class GameTest {
//     @Test
//     public void testGameInitialization() {
//         Game game = new Game();
//         assertNotNull(game.getPlayer());
//         assertEquals(1000, game.getPlayer().getBalance());
//     }
//         @Test
//         public void testGameInit() {
//             Game game = new Game();
//             assertNotNull(game.getPlayer());
//             assertNotNull(game.getDealer());
//             assertEquals(1000, game.getPlayer().getBalance());
//         }

//         @Test
//         public void testStartRound() {
//             Game game = new Game();
//             game.startRound();
//             // Check that cards were actually dealt (clears red lines in startRound)
//             assertEquals(2, game.getPlayer().getHand().size());
//             assertEquals(2, game.getDealer().getHand().size());
//         }
//             @Test
//             public void testGameGetters() {
//                 Game g = new Game();
//                 assertNotNull(g.getDeck());
//                 assertNotNull(g.getShop());
//                 assertNotNull(g.getCollection());
//             }
// }