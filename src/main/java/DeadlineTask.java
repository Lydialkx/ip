public class DeadlineTask extends Task {
    public String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getStatusIcon() {
        return "D";
    }
}
