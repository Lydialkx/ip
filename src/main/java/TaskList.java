import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

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
            ui.showMessage("  [" + task.getStatusIcon() + "][" + task.getMarkIcon() + "] " + task.description + " (by: " + formatDateTime(deadlineTask.by) + ")");
        }
        if (task instanceof EventTask) {
            EventTask eventTask = (EventTask) task;
            ui.showMessage("  [" + task.getStatusIcon() + "][" + task.getMarkIcon() + "] " + task.description + " (from: " + formatDateTime(eventTask.from) + " to: " + formatDateTime(eventTask.to) + ")");
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
                ui.showMessage((i + 1) + ".[" + tasks.get(i).getStatusIcon() + "][" + tasks.get(i).getMarkIcon() + "] " + tasks.get(i).description + " (by: " + formatDateTime(deadlineTask.by) + ")");
            }
            if (tasks.get(i) instanceof EventTask) {
                EventTask eventTask = (EventTask) tasks.get(i);
                ui.showMessage((i + 1) + ".[" + tasks.get(i).getStatusIcon() + "][" + tasks.get(i).getMarkIcon() + "] " + tasks.get(i).description + " (from: " + formatDateTime(eventTask.from) + " to: " + formatDateTime(eventTask.to) + ")");
            }

        }
    }

    public void findTasks(String keyword, Ui ui) {
        ui.showMessage("Here are the matching tasks in your list:");
        int count = 1;
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                if (task instanceof TodoTask) {
                    ui.showMessage(count + ".[" + task.getStatusIcon() + "][" + task.getMarkIcon() + "] " + task.description);
                }
                if (task instanceof DeadlineTask) {
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    ui.showMessage(count + ".[" + task.getStatusIcon() + "][" + task.getMarkIcon() + "] " + task.description + " (by: " + formatDateTime(deadlineTask.by) + ")");
                }
                if (task instanceof EventTask) {
                    EventTask eventTask = (EventTask) task;
                    ui.showMessage(count + ".[" + task.getStatusIcon() + "][" + task.getMarkIcon() + "] " + task.description + " (from: " + formatDateTime(eventTask.from) + " to: " + formatDateTime(eventTask.to) + ")");
                }
                count++;
            }
        }
        if (count == 1) {
            ui.showMessage("No matching tasks found.");
        }
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private String formatDateTime(String dateTime) {
        try {
            if (dateTime.matches("\\d{4}-\\d{2}-\\d{2}")) {
                LocalDate parsedDate = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return parsedDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            } else if (dateTime.matches("\\d{4}-\\d{2}-\\d{2} \\d{4}")) {
                LocalDateTime parsedDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                return parsedDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
            }
        } catch (DateTimeParseException e) {
            return dateTime;
        }
        return dateTime;
    }
}
