// package test;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;
// import model.Card;
// import model.Player;

// public class PlayerTest {
//     @Test
//     public void testLebronScore() {
//         Player p = new Player(100);
//         p.addCard(new Card("Spades", "King", 10)); // Lebron
//         p.addCard(new Card("Hearts", "Ace", 11));
//         p.addCard(new Card("Clubs", "2", 2));
//         assertEquals(23, p.calculateScore());
//     }
//     @Test
//     public void testAceLogic() {
//         Player p = new Player(100);
//         // 11 + 11 + 5 = 27 (Over the 23 limit)
//         p.addCard(new Card("Spades", "Ace", 11));
//         p.addCard(new Card("Hearts", "Ace", 11));
//         p.addCard(new Card("Clubs", "5", 5)); 
        
//         // 27 - 10 (one ace becomes 1) = 17
//         assertEquals(17, p.calculateScore(), "Score should adjust one Ace to 1 to stay under 23");
//     }

//     @Test
//     public void testClearBust() {
//         Player p = new Player(100);
//         p.addCard(new Card("Spades", "10", 10));
//         p.addCard(new Card("Hearts", "10", 10));
//         p.addCard(new Card("Clubs", "5", 5)); // Total 25 (Bust)
        
//         assertTrue(p.isBust());
        
//         p.clearBust(); // Removes the highest card (10)
//         assertEquals(15, p.calculateScore());
//         assertFalse(p.isBust());
//     }

//     @Test
//     public void testScoreBoosts() {
//         Player p = new Player(100);
//         p.applyScoreBoost(5);
//         assertEquals(5, p.calculateScore());
        
//         p.removeScoreBoost(2);
//         assertEquals(3, p.calculateScore());
        
//         p.resetBoost();
//         assertEquals(0, p.calculateScore());
//     }
// }