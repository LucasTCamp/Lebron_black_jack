// package test;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeEach;
// import model.Collection;
// import model.PlayerCard;
// import java.util.List;

// public class CollectionTest {

//     private Collection collection;
//     private PlayerCard bron;
//     private PlayerCard curry;

//     @BeforeEach
//     public void setUp() {
//         collection = new Collection();
//         // Updated to match: String, int, String, String, double
//         bron = new PlayerCard("LeBron James", 1500, "The King", "SCORE BOOST", 5.0);
//         curry = new PlayerCard("Stephen Curry", 900, "Chef", "MULTIPLIER", 2.0);
//     }

//     @Test
//     public void testAddAndGetOwned() {
//         collection.add(bron);
//         assertEquals(1, collection.getOwned().size());
//         assertEquals(bron, collection.getOwned().get(0));
//     }

//     @Test
//     public void testOwnsLogic() {
//         collection.add(bron);
//         assertTrue(collection.owns(bron));
        
//         // Testing case insensitivity
//         PlayerCard lowerCaseBron = new PlayerCard("lebron james", 0, "", "NONE", 0.0);
//         assertTrue(collection.owns(lowerCaseBron));
        
//         assertFalse(collection.owns(curry));
//     }

//     @Test
//     public void testBuffLogic() {
//         collection.add(bron);
        
//         // Tests getAvailableBuffs logic
//         List<PlayerCard> buffs = collection.getAvailableBuffs("SCORE BOOST");
//         assertEquals(1, buffs.size());
        
//         // Tests hasUnusedBuff (True branch)
//         assertTrue(collection.hasUnusedBuff("SCORE BOOST"));
        
//         // Tests hasUnusedBuff (False branch - wrong type)
//         assertFalse(collection.hasUnusedBuff("INSURANCE"));
//     }

//     @Test
//     public void testUseFirstBuff() {
//         collection.add(bron);

//         // First use: hits the return card branch
//         PlayerCard used = collection.useFirstBuff("SCORE BOOST");
//         assertNotNull(used);
//         assertTrue(used.isUsed());

//         // Second use: hits the return null branch (because it's now 'used')
//         assertNull(collection.useFirstBuff("SCORE BOOST"));
        
//         // Wrong type: hits the final return null branch
//         assertNull(collection.useFirstBuff("FREE HIT"));
//     }

//     @Test
//     public void testDisplay() {
//         // Hits empty list branch
//         collection.display();

//         // Hits loop branch
//         collection.add(bron);
//         collection.display();
//     }
    
//     @Test
//     public void testResetBuffs() {
//         // Just calling to cover the method lines
//         collection.add(bron);
//         collection.resetBuffs();
//     }
// }