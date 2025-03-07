import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The main class for the Lyxo chatbot.
 * Manages interactions between the user, task list, and storage.
 */
class Lyxo {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Lyxo instance.
     * @param filePath The path to the file where tasks are stored.
     */
    public Lyxo(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the chatbot, reading user input and implementing commands.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point for the Lyxo chatbot.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Lyxo("data/Lyxo.txt").run();
    }
}
