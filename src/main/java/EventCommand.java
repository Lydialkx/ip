public class EventCommand extends Command {
    private String description, from, to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new EventTask(description, from, to), ui);
        storage.save(tasks);
    }
}