import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Manages the list of tasks and provides detailed operation methods.
 */
class TaskList {
    /**
     * Constructs an empty TaskList.
     */
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with existing tasks.
     * @param tasks A list of existing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list and formats dates or time for deadline and event commands.
     * @param task The task to be added.
     * @param ui The UI instance to show all messages.
     */
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

    /**
     * Deletes one specific task from the list.
     * @param index The index of the task to be deleted.
     * @param ui The UI instance to show all messages.
     */
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

    /**
     * Marks a task as done or not done.
     * @param index The index of the task.
     * @param isDone The status to mark the task.
     * @param ui The UI instance to show all messages.
     */
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

    /**
     * Prints all tasks in the list.
     * @param ui The UI instance to show all messages.
     */
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

    /**
     * Finds tasks based on the searching string.
     * @param name The string to search for.
     * @param ui The UI instance to show messages.
     */
    public void findTasks(String name, Ui ui) {
        ui.showMessage("Here are the matching tasks in your list:");
        int count = 1;
        for (Task task : tasks) {
            if (task.description.contains(name)) {
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

    /**
     * Retrieves the list of tasks.
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Formats the date or time of one specific task for better display.
     * @param dateTime The date or time of the task to format.
     * @return The formatted task string.
     */
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
