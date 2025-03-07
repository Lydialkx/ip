/**
 * Represents a command to find tasks containing one specific string.
 */
public class FindCommand extends Command{
    private String name;

    /**
     * Constructs a FindCommand.
     * @param name The string to search for.
     */
    public FindCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTasks(name, ui);
    }
}
