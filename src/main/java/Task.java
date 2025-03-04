public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean todo;
    protected boolean deadline;
    protected boolean event;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.todo = false;
        this.event = false;
        this.deadline = false;
    }

    public String getMarkIcon() {
        return (isDone ? "X" : " ");
    }

    public String getStatusIcon() {
        if (todo){
            return "T";
        }
        else if (deadline){
            return "D";
        }
        else if (event){
            return "E";
        }
        else{
            return " ";
        }
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public void markAstodo() {
        this.todo = true;
    }

    public void markAsdeadline() {
        this.deadline = true;
    }

    public void markAsevent() {
        this.event = true;
    }
}
