import java.util.Scanner;
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
        String[] tasks = new String[100];
        boolean[] isDone = new boolean[100];
        int count = 0;
        while (exit) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    _____________________________________________________________");
                exit = false;
            }
            else if (command.equals("list")) {
                    System.out.println("    ____________________________________________________________");
                    for (int i = 0; i < count; i++) {
                        if (isDone[i]) {
                            System.out.println("     " + (i+1) + ".[X] " + tasks[i]);
                        }
                        else {
                            System.out.println("     " + (i+1) + ".[ ] " + tasks[i]);
                        }
                    }
                    System.out.println("    _____________________________________________________________");
            }
            else if (command.startsWith("mark")) {
                int which_mark = Integer.parseInt(command.substring(4).trim());
                isDone[which_mark-1] = true;
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       [X] "+tasks[which_mark-1]);
                System.out.println("    _____________________________________________________________");
            }
            else if (command.startsWith("unmark")) {
                int which_unmark = Integer.parseInt(command.substring(6).trim());
                isDone[which_unmark-1] = false;
                System.out.println("    ____________________________________________________________");
                System.out.println("     OK, I've marked this task as not done yet:");
                System.out.println("       [ ] "+tasks[which_unmark-1]);
                System.out.println("    _____________________________________________________________");
            }
            else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + command);
                System.out.println("    _____________________________________________________________");
                tasks[count] = command;
                isDone[count] = false;
                count++;
            }
        }
    }
}
