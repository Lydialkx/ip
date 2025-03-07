/**
 * Specifies a task with no dates or time.
 */
public class TodoTask extends Task {
    /**
     * Constructs a TodoTask with a description.
     * @param description The task description.
     */
    public TodoTask(String description) {
        super(description);
    }

    /**
     * Returns the status icon for the task.
     * @return "T" indicating the task.
     */
    @Override
    public String getStatusIcon() {
        return "T";
    }
}