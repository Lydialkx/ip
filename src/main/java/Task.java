/**
 * Represents one task with a description and completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with a given description.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Gets the icon representing the task's completion status.
     *
     * @return "X" if the task is done, otherwise a space character.
     */
    public String getMarkIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the status icon representing the type of task.
     * @return A string representing the task type.
     */
    public abstract String getStatusIcon();
}