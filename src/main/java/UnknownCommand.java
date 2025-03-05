public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("Please enter one of the following valid commands:\n" +
                "1. todo <task description>\n" +
                "2. deadline <task description> /by <due date>\n" +
                "3. event <task description> /from <start time> /to <end time>\n" +
                "4. list  (Displays all tasks)\n" +
                "5. delete <task number> (Deletes a task)\n" +
                "6. mark <task number> (Marks a task as done)\n" +
                "7. unmark <task number> (Unmarks a task)\n" +
                "8. bye  (Exits the program)");
    }
}
