import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Parser {
    public static Command parse(String command) {
        try {
            if (command.equals("bye")) {
                return new ExitCommand();
            } else if (command.equals("list")) {
                return new ListCommand();
            } else if (command.startsWith("mark")) {
                String number = command.substring(4).trim();
                if (!number.matches("\\d+")) {
                    throw new IllegalArgumentException("Please enter a valid task number.");
                }
                return new MarkCommand(Integer.parseInt(number) - 1);
            } else if (command.startsWith("unmark")) {
                String number = command.substring(6).trim();
                if (!number.matches("\\d+")) {
                    throw new IllegalArgumentException("Please enter a valid task number.");
                }
                return new UnmarkCommand(Integer.parseInt(number) - 1);
            } else if (command.startsWith("delete")) {
                String number = command.substring(6).trim();
                if (!number.matches("\\d+")) {
                    throw new IllegalArgumentException("Please enter a valid task number.");
                }
                return new DeleteCommand(Integer.parseInt(number) - 1);
            } else if (command.startsWith("todo")) {
                String todoname = command.substring(4).trim();
                if (todoname.isEmpty()) {
                    throw new IllegalArgumentException("Please enter a valid description of your todo task.");
                }
                return new TodoCommand(todoname);
            } else if (command.startsWith("deadline")) {
                String[] deadlinetask = command.substring(8).trim().split("/");
                if (deadlinetask.length != 2 || !deadlinetask[1].trim().startsWith("by ")) {
                    throw new IllegalArgumentException("Please enter your deadline in the format: deadline <task> /by <date>");
                }
                String deadlineby = deadlinetask[1].trim().substring(3).trim();
                String deadlinename = deadlinetask[0].trim();
                return new DeadlineCommand(deadlinename, deadlineby);
            } else if (command.startsWith("event")) {
                String[] eventtask = command.substring(5).trim().split("/");
                if (eventtask.length != 3 || !eventtask[1].trim().startsWith("from ") || !eventtask[2].trim().startsWith("to ")) {
                    throw new IllegalArgumentException("Please enter your event task in the format: event <task> /from <start> /to <end>");
                }
                String eventfrom = eventtask[1].trim().substring(5).trim();
                String eventto = eventtask[2].trim().substring(3).trim();
                String eventname = eventtask[0].trim();
                return new EventCommand(eventname, eventfrom, eventto);
            } else {
                return new UnknownCommand();
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format. Please enter a valid task number.");
        }
    }
}
