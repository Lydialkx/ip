/**
 * Represents a command to add an event task.
 */
public class EventCommand extends Command {
    private String description, from, to;

    /**
     * Constructs an EventCommand.
     * @param description The event description.
     * @param from The event start time.
     * @param to The event end time.
     */
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