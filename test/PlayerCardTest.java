// package test;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;
// import model.PlayerCard;

// public class PlayerCardTest {

//     @Test
//     public void testAllBuffDescriptions() {
//         String[] types = {"SCORE_BOOST", "MULTIPLIER", "INSURANCE", "FREE_HIT", "UNKNOWN"};
//         for (String type : types) {
//             PlayerCard c = new PlayerCard("Test", 100, "Desc", type, 2.0);
//             String desc = c.getBuffDescription();
//             assertNotNull(desc);
//             // Calling toString covers the toString red lines
//             assertNotNull(c.toString());
//         }
//     }
    
//     @Test
//     public void testGetters() {
//         PlayerCard c = new PlayerCard("LeBron", 1500, "GOAT", "SCORE_BOOST", 5.0);
//         assertEquals(1500, c.getPrice());
//         assertEquals("GOAT", c.getDescription());
//         assertEquals(5.0, c.getBuffValue(), 0.01);
//     }
// }