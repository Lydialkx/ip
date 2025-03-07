/**
 * Specifies an event task with detailed information.
 */
public class EventTask extends Task {
    public String from, to;

    /**
     * Constructs an EventTask with a description, start time, and end time.
     * @param description The event description.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the status icon for an event task.
     * @return "E" indicating an event task.
     */
    @Override
    public String getStatusIcon() {
        return "E";
    }
}