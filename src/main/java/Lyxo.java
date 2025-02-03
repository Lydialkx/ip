import java.util.Scanner;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // "X" if done, " " if not done
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }
}

public class Lyxo {
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
        int count = 0;

        while (exit) {
            String command = scanner.nextLine().trim();
            if (command.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    _____________________________________________________________");
                exit = false;
            }
            else if (command.equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println("     " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                }
                System.out.println("    _____________________________________________________________");
            }
            else if (command.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(command.substring(4).trim()) - 1;
                    tasks[taskIndex].markAsDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       [X] " + tasks[taskIndex].description);
                    System.out.println("    _____________________________________________________________");

            }
            else if (command.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(command.substring(6).trim()) - 1;
                    tasks[taskIndex].unmarkAsDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     OK, I've marked this task as not done yet:");
                    System.out.println("       [ ] " + tasks[taskIndex].description);
                    System.out.println("    _____________________________________________________________");
            }
            else {
                    tasks[count] = new Task(command);
                    count++;
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     added: " + command);
                    System.out.println("    _____________________________________________________________");
            }
        }
    }
}