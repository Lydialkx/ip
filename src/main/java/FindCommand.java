public class FindCommand extends Command{
    private String name;

    public FindCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTasks(name, ui);
    }
}
