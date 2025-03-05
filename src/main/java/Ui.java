import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

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

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println("     ERROR: " + message);
    }

    public void showMessage(String message) {
        System.out.println("    " + message);
    }

    public void showLoadingError() {
        System.out.println("     ERROR: Unable to load tasks from file.");
    }
}
