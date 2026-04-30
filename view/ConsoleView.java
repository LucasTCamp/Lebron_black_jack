package view;

/**
 * The view component of the application responsible for text-based output.
 * <p>
 * This class abstracts the console output mechanism, allowing the controller 
 * to send information to the user without needing direct access to 
 * {@code System.out}.
 * </p>
 * * @author Lucas & Masoud
 */
public class ConsoleView {
    
    /**
     * Displays a message to the user via the standard output stream.
     * * @param msg The string message to be printed to the console.
     */
    public void displayMessage(String msg) { 
        System.out.println(msg); 
    }
}