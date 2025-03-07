/**
 * Specifies a deadline task with detailed information.
 */
public class DeadlineTask extends Task {
    public String by;

    /**
     * Constructs a DeadlineTask with a description and deadline.
     * @param description The task description.
     * @param by The deadline date/time.
     */
    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the status icon for a deadline task.
     * @return "D" indicating a deadline task.
     */
    @Override
    public String getStatusIcon() {
        return "D";
    }
}
