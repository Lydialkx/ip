public class DeadlineCommand extends Command {
    private String description, by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new DeadlineTask(description, by), ui);
        storage.save(tasks);
    }
}