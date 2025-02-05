import java.util.Scanner;

class Task {
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
        return (isDone ? "X" : " "); // "X" if done, " " if not done
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
                    System.out.println("     " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "][" + tasks[i].getMarkIcon() + "] " + tasks[i].description);
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

            else if (command.startsWith("todo")){
                    String todoname = command.substring(4).trim();
                    tasks[count] = new Task(todoname);
                    tasks[count].markAstodo();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       [T]" + "[" + tasks[count].getMarkIcon() + "] " + todoname);
                    count ++;
                    System.out.println("     Now you have " + count +" tasks in the list.");
            }

            else if (command.startsWith("deadline")){
                String[] deadlinetask = command.substring(8).trim().split("/");
                String deadlineby = deadlinetask[1].substring(2).trim();
                String deadlinename = deadlinetask[0].trim() + " (by: " + deadlineby + ")";
                tasks[count] = new Task(deadlinename);
                tasks[count].markAsdeadline();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task:");
                System.out.println("       [D]" + "[" + tasks[count].getMarkIcon() + "] " + deadlinename);
                count ++;
                System.out.println("     Now you have " + count +" tasks in the list.");
            }

            else if (command.startsWith("event")){
                String[] eventtask = command.substring(5).trim().split("/");
                String eventfrom = eventtask[1].substring(4).trim();
                String eventto = eventtask[2].substring(2).trim();
                String eventname = eventtask[0] + " (from: " + eventfrom + " to: " + eventto + ")";
                tasks[count] = new Task(eventname);
                tasks[count].markAsevent();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task:");
                System.out.println("       [E]" + "[" + tasks[count].getMarkIcon() + "] " + eventname );
                count ++;
                System.out.println("     Now you have " + count +" tasks in the list.");
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