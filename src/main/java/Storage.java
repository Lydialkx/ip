import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
            return tasks;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");

                if (parts.length < 3) {
                    System.out.println("Skipping corrupted task entry: " + line);
                    continue;
                }

                boolean isDone = parts[1].equals("1");
                Task task;

                switch (parts[0]) {
                    case "T":
                        task = new TodoTask(parts[2]);
                        break;
                    case "D":
                        if (parts.length < 4) continue;
                        task = new DeadlineTask(parts[2], formatDateTime(parts[3]));
                        break;
                    case "E":
                        if (parts.length < 4) continue;
                        String[] eventTimes = parts[3].split("-");
                        task = new EventTask(parts[2], eventTimes[0].trim(), eventTimes[1].trim());
                        break;
                    default:
                        System.out.println("Skipping unknown task type: " + parts[0]);
                        continue;
                }

                if (isDone) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

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

    public void save(TaskList tasks) {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                fw.write(taskToFileFormat(task) + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private String taskToFileFormat(Task task) {
        String doneStatus = task.isDone ? "1" : "0";

        if (task instanceof TodoTask) {
            return "T | " + doneStatus + " | " + task.description;
        } else if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            return "D | " + doneStatus + " | " + deadlineTask.description + " | " + formatDateTime(deadlineTask.by);
        } else if (task instanceof EventTask) {
            EventTask eventTask = (EventTask) task;
            return "E | " + doneStatus + " | " + eventTask.description + " | " + formatDateTime(eventTask.from) + " - " + formatDateTime(eventTask.to);
        }
        return "";
    }
}
