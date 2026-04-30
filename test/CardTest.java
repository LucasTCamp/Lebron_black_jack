// package test;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;
// import model.Card;

// /**
//  * Tests the Card class logic, specifically the Lebron renaming.
//  */
// public class CardTest {

//     @Test
//     public void testCardConstructorAndGetters() {
//         // Test a standard card
//         Card card = new Card("Hearts", "7", 7);
//         assertEquals(7, card.getValue());
//     }

//     @Test
//     public void testToStringRegularCard() {
//         // Test that a normal card displays correctly
//         Card card = new Card("Spades", "10", 10);
//         assertEquals("10 of Spades", card.toString());
//     }

//     @Test
//     public void testToStringLebronRenaming() {
//         // This is the critical test to turn the 'Lebron' branch green
//         // We test "King" in different cases to cover the .equalsIgnoreCase() logic
//         Card king1 = new Card("Diamonds", "King", 10);
//         Card king2 = new Card("Clubs", "king", 10);
        
//         assertEquals("Lebron of Diamonds", king1.toString());
//         assertEquals("Lebron of Clubs", king2.toString());
        
//         // Double check that it does NOT say "King"
//         assertFalse(king1.toString().contains("King"));
//     }
// }