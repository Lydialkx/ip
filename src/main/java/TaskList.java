import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    // New Constructor to Accept Preloaded Tasks
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        ui.showMessage("Got it. I've added this task:");
        if (task instanceof TodoTask) {
            ui.showMessage("  [" + task.getStatusIcon() + "][" + task.getMarkIcon() + "] " + task.description);
        }
        if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            ui.showMessage("  [" + task.getStatusIcon() + "][" + task.getMarkIcon() + "] " + task.description + " (by: " + deadlineTask.by + ")");
        }
        if (task instanceof EventTask) {
            EventTask eventTask = (EventTask) task;
            ui.showMessage("  [" + task.getStatusIcon() + "][" + task.getMarkIcon() + "] " + task.description + " (from: " + eventTask.from + " to: " + eventTask.to + ")");
        }
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void deleteTask(int index, Ui ui) {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            ui.showMessage("Noted. I've removed this task:");
            ui.showMessage("  [" + removedTask.getStatusIcon() + "][" + removedTask.getMarkIcon() + "] " + removedTask.description);
            ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            ui.showError("Invalid task number.");
        }
    }

    public void markTask(int index, boolean isDone, Ui ui) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            if (isDone) {
                task.markAsDone();
                ui.showMessage("Nice! I've marked this task as done:");
            } else {
                task.unmarkAsDone();
                ui.showMessage("OK, I've marked this task as not done yet:");
            }
            ui.showMessage("  [" + task.getStatusIcon() + "][" + task.getMarkIcon() + "] " + task.description);
        } else {
            ui.showError("Invalid task number.");
        }
    }


    public void printTasks(Ui ui) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof TodoTask) {
                ui.showMessage((i + 1) + ".[" + tasks.get(i).getStatusIcon() + "][" + tasks.get(i).getMarkIcon() + "] " + tasks.get(i).description);
            }
            if (tasks.get(i) instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) tasks.get(i);
                ui.showMessage((i + 1) + ".[" + tasks.get(i).getStatusIcon() + "][" + tasks.get(i).getMarkIcon() + "] " + tasks.get(i).description + " (by: " + deadlineTask.by + ")");
            }
            if (tasks.get(i) instanceof EventTask) {
                EventTask eventTask = (EventTask) tasks.get(i);
                ui.showMessage((i + 1) + ".[" + tasks.get(i).getStatusIcon() + "][" + tasks.get(i).getMarkIcon() + "] " + tasks.get(i).description + " (from: " + eventTask.from + " to: " + eventTask.to + ")");
            }

        }
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
