/**
 * Represents a command to delete one specific task.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a DeleteCommand.
     * @param taskIndex The index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.deleteTask(taskIndex, ui);
        storage.save(tasks);
    }
}

