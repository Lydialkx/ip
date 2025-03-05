public class EventTask extends Task {
    public String from, to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getStatusIcon() {
        return "E";
    }
}