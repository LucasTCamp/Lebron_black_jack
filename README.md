# Lebron Black Jack

A terminal-based "High Stakes" Black Jack simulation written in Java. You start with $1,000 and must navigate the deck to reach the custom target score of **23**. Win rounds to earn cash, visit the shop to buy NBA legend cards, and build a collection of powerful buffs to avoid going bust.

## How to Run on the Terminal

**Steps**
1. **Download and Extract:** Download the ZIP from GitHub and extract the folder.
2. **Open in IDE:** Open the extracted folder in VS Code.
3. **Launch:** Open `main.java` and click the **Run** button at the top right of the editor.

## In-Game Commands

* **1 (PLAY):** Start a new round of Black Jack.
* **2 (SHOP):** Purchase NBA Player Cards using your balance.
* **3 (COLLECTION):** View your owned cards and their unique buffs.
* **4 (QUIT):** Exit the game and see your final stats.

## Design Patterns

### Strategy Pattern — AI Dealer Behavior
We used the **Strategy Pattern** to handle the dealer's logic. By using the `DealerStrategy` interface, we decoupled the game rules from the dealer's decision-making. 
* **Implementation:** The `GameController` can swap between `SafeStrategy` (dealer stays at 18) and `AggressiveStrategy` (dealer hits until 21) at runtime.
* **Benefit:** This makes the AI modular, allowing for different difficulty levels without modifying the core game loop.

### Decorator Pattern — Player Card Buffs
The **Decorator Pattern** logic is used to manage the special abilities granted by NBA Player Cards. Rather than hard-coding dozens of "if-statements" into the player class, we layer these card effects onto the standard game actions.
* **Implementation:** When a card like "Donovan Mitchell" (Free Hit) or "Wemby" (Insurance) is used, it "decorates" the standard turn logic with extra protection or scoring modifiers.
* **Benefit:** This structure allows us to easily add new players and unique abilities to the shop without bloating the existing codebase.

## MVC Structure

### model/
* `Card.java`: Logic for suit, rank, and "Lebron" (King) renaming.
* `Deck.java`: Handles the 52-card deck, shuffling, and drawing.
* `Player.java`: Manages hand logic, balances, and the "23" scoring rule.
* `PlayerCard.java`: Represents collectible items with specific buff types.
* `Shop.java`: The marketplace catalog for available NBA cards.
* `Collection.java`: Inventory system for tracking and using owned buffs.
* `Game.java`: The Facade class coordinating all model-layer interactions.

### controller/
* `GameController.java`: The central engine handling input, betting, and round flow.

### view/
* `ConsoleView.java`: Dedicated class for all system output and user messages.

## Known Issues/Limitations

* **Persistence:** No save/load system; collection and balance are reset on program exit.

## AI Tool Usage

All game logic, class design, and MVC architecture were written by us. AI assistance was used for help writing standardized Javadocs and for troubleshooting specific logic errors when implementing the Strategy pattern. AI was used for this readme to for help with the markdown formatting. It was finally used to help us refine the math for the "23-point" scoring logic and Ace-handling edge cases.
