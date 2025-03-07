/**
 * Represents an abstract command to be implemented in the chatbot.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, user interface, and storage.
     * @param tasks   The task list where tasks are stored.
     * @param ui      The user interface for showing messages.
     * @param storage The storage system for saving and loading tasks.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Determines whether to exit the chatbot.
     * @return false if the chatbot continues; can be overridden in specific commands.
     */
    public boolean isExit() {
        return false;
    }
}