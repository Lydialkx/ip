import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Lyxo {
    private static final String filePath = "data/Lyxo.txt";

    public static void main(String[] args) {
        String logo = "  _                 \n"
                + " | |                \n"
                + " | |  _   _  _   _ ____ \n"
                + " | | | | | |\\ \\ / / __ \\\n"
                + " | |_| |_| | >> << |__| | \n"
                + "  \\___\\__, |/_/ \\_\\____/ \n"
                + "      ___| |       \n"
                + "     |_____|       \n";
        System.out.println("    Hello from\n" + logo);
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Lyxo");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        boolean exit = true;
        Task[] tasks = new Task[100];
        int count = loadTasks(filePath, tasks);

        while (exit) {
            String command = scanner.nextLine().trim();
            try {
                if (command.equals("bye")) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Bye. Hope to see you again soon!");
                    System.out.println("    _____________________________________________________________");
                    exit = false;
                } else if (command.equals("list")) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        System.out.println("     " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "][" + tasks[i].getMarkIcon() + "] " + tasks[i].description);
                    }
                    System.out.println("    _____________________________________________________________");
                } else if (command.startsWith("delete")) {
                    int taskIndex = Integer.parseInt(command.substring(6).trim()) - 1;
                    if (taskIndex < 0 || taskIndex >= count) {
                        throw new Exception("Please enter a valid number within the range of 1 to " + count + ".");
                    }
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Noted. I've removed this task:");
                    System.out.println("       [" + tasks[taskIndex].getStatusIcon() + "][" + tasks[taskIndex].getMarkIcon() + "] " + tasks[taskIndex].description);
                    System.out.println("    _____________________________________________________________");
                    for (int i = taskIndex; i < count - 1; i++) {
                        tasks[i] = tasks[i + 1];
                    }
                    tasks[count - 1] = null;
                    count--;
                    changeFile(filePath, tasks, count);
                } else if (command.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(command.substring(4).trim()) - 1;
                    if (taskIndex < 0 || taskIndex >= count) {
                        throw new Exception("Please enter a valid number within the range of 1 to " + count + ".");
                    }
                    tasks[taskIndex].markAsDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + "[" + tasks[taskIndex].getStatusIcon() + "][" + tasks[taskIndex].getMarkIcon() + "] " + tasks[taskIndex].description);
                    System.out.println("    _____________________________________________________________");
                    changeFile(filePath, tasks, count);
                } else if (command.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(command.substring(6).trim()) - 1;
                    if (taskIndex < 0 || taskIndex >= count) {
                        throw new Exception("Please enter a valid number within the range of 1 to " + count + ".");
                    }
                    tasks[taskIndex].unmarkAsDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     OK, I've marked this task as not done yet:");
                    System.out.println("       " + "[" + tasks[taskIndex].getStatusIcon() + "][" + tasks[taskIndex].getMarkIcon() + "] " + tasks[taskIndex].description);
                    System.out.println("    _____________________________________________________________");
                    changeFile(filePath, tasks, count);
                } else if (command.startsWith("todo")) {
                    String todoname = command.substring(4).trim();
                    if (todoname.isEmpty()) {
                        throw new Exception("Please enter a valid description of your todo task.");
                    }
                    tasks[count] = new Task(todoname);
                    tasks[count].markAstodo();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       [T]" + "[" + tasks[count].getMarkIcon() + "] " + todoname);
                    count++;
                    System.out.println("     Now you have " + count + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                    changeFile(filePath, tasks, count);
                } else if (command.startsWith("deadline")) {
                    String[] deadlinetask = command.substring(8).trim().split("/");
                    if (deadlinetask.length != 2) {
                        throw new Exception("Please enter your deadline in the format: deadline <task> /by <date>");
                    }
                    String deadlineby = deadlinetask[1].trim().substring(2).trim();
                    String deadlinename = deadlinetask[0].trim() + " (by: " + deadlineby + ")";
                    tasks[count] = new Task(deadlinename);
                    tasks[count].markAsdeadline();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       [D]" + "[" + tasks[count].getMarkIcon() + "] " + deadlinename);
                    count++;
                    System.out.println("     Now you have " + count + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                    changeFile(filePath, tasks, count);
                } else if (command.startsWith("event")) {
                    String[] eventtask = command.substring(5).trim().split("/");
                    if (eventtask.length != 3) {
                        throw new Exception("Please enter your event task in the format: event <task> /from <start> /to <end>");
                    }
                    String eventfrom = eventtask[1].trim().substring(4).trim();
                    String eventto = eventtask[2].trim().substring(2).trim();
                    String eventname = eventtask[0].trim() + " (from: " + eventfrom + " to: " + eventto + ")";
                    tasks[count] = new Task(eventname);
                    tasks[count].markAsevent();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       [E]" + "[" + tasks[count].getMarkIcon() + "] " + eventname);
                    count++;
                    System.out.println("     Now you have " + count + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                    changeFile(filePath, tasks, count);
                } else {
                    throw new Exception(
                            "Please enter one of the following valid commands:\n" +
                                    "1. todo <task description>\n" +
                                    "2. deadline <task description> /by <due date>\n" +
                                    "3. event <task description> /from <start time> /to <end time>\n" +
                                    "4. list  (Displays all tasks)\n" +
                                    "5. delete <task number> (Deletes a task)\n" +
                                    "6. mark <task number> (Marks a task as done)\n" +
                                    "7. unmark <task number> (Unmarks a task)\n" +
                                    "8. bye  (Exits the program)"
                    );
                }

            } catch (Exception e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     ERROR: " + e.getMessage());
                System.out.println("    _____________________________________________________________");
            }
        }
    }

    private static void changeFile(String filePath, Task[] tasks, int count) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < count; i++) {
                fw.write(tasks[i].getStatusIcon() + " | " + (tasks[i].isDone ? "1" : "0") + " | " + tasks[i].description + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("     ERROR: Unable to save tasks.");
        }
    }

    private static int loadTasks(String filePath, Task[] tasks) {
        int count = 0;
        File file = new File(filePath);
        if (!file.exists()) return 0;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split(" | ");
                if (parts.length < 3) {
                    System.out.println("     ERROR: Corrupted task data, skipping entry.");
                    continue;
                }
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                tasks[count] = new Task(description);
                if (parts[0].equals("T")) tasks[count].markAstodo();
                if (parts[0].equals("D")) tasks[count].markAsdeadline();
                if (parts[0].equals("E")) tasks[count].markAsevent();
                if (isDone) tasks[count].markAsDone();
                count++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("     ERROR: File not found, starting fresh.");
        }
        return count;
    }
}
