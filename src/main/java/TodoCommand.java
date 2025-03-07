/**
 * Represents a command to add a task with no dates or time.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Constructs a TodoCommand.
     * @param description The task description.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new TodoTask(description), ui);
        storage.save(tasks);
    }
}
