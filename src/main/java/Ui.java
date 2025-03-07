import java.util.Scanner;

/**
 * Handles user interaction by reading user input and showing messages.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object with a scanner for reading user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message along with the program logo.
     */
    public void showWelcome() {
        String logo = "  _                 \n"
                + " | |                \n"
                + " | |  _   _  _   _ ____ \n"
                + " | | | | | |\\ \\ / / __ \\\n"
                + " | |_| |_| | >> << |__| | \n"
                + "  \\___\\__, |/_/ \\_\\____/ \n"
                + "      ___| |       \n"
                + "     |_____|       \n";
        System.out.println("    Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Lyxo");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Reads and returns the next user input command.
     * @return A string of user input.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a horizontal line for standardizing the outputs.
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Displays an error message.
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("     ERROR: " + message);
    }

    /**
     * Displays a given message.
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println("    " + message);
    }

    /**
     * Displays an error message when loading tasks from a file fails.
     */
    public void showLoadingError() {
        System.out.println("     ERROR: Unable to load tasks from file.");
    }
}
