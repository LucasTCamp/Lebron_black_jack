// package test;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;
// import model.Shop;
// import model.PlayerCard;

// public class ShopTest {
//     @Test
//     public void testFindByName() {
//         Shop shop = new Shop();
//         // Test finding a real player
//         PlayerCard card = shop.findByName("LeBron James");
//         assertNotNull(card);
//         assertEquals("LeBron James", card.getName());

//         // Test case sensitivity (findByName uses equalsIgnoreCase)
//         PlayerCard cardLower = shop.findByName("lebron james");
//         assertNotNull(cardLower);

//         // Test null case (The "return null" red line)
//         assertNull(shop.findByName("NonExistentPlayer"));
//     }

//     @Test
//     public void testGetAvailable() {
//         Shop shop = new Shop();
//         assertFalse(shop.getAvailable().isEmpty());
//     }
// }