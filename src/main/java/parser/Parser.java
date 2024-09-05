// Parser.java
package parser;

import tasks.Task;

import ui.Ui;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Parser {

    public static final Map<String, Command> commands = new HashMap<>();

    // Implement a new command "many"
    // input command looks something like =>
    /*
    many deadline <> /by <>

     */
    // Get an array of Tasks split by "\n" and then call a method named addMultipleTasks(Tasks... tasks)
    // And then process accordingly
    static {
        // Initialize command mappings
        commands.put("list", new ListCommand());
        commands.put("mark", new MarkCommand());
        commands.put("unmark", new UnmarkCommand());
        commands.put("todo", new ToDoCommand());
        commands.put("deadline", new DeadlineCommand());
        commands.put("event", new EventCommand());
        commands.put("delete", new DeleteCommand());
        commands.put("find", new FindCommand());
        commands.put("on", new OnCommand());
        //commands.put("many", new AddManyCommand());
    }

    // To initialise all the commands inside the map
    public static void initialiseMap() {}

    public static void ratchetCatBot(List<Task> items) {
        Ui.sayWelcome();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Reads a line of text

        while (!input.equals("bye")) {
            String commandKey = input.split(" ")[0].trim(); // Get the command keyword
            Command command = commands.get(commandKey);

            try {
                if (command != null) {
                    input = command.execute(input, items, scanner);
                } else {
                    System.out.print("Inappropriate Command. Try again with a valid command: ");
                    input = scanner.nextLine();
                }
            } catch (DateTimeParseException e) { //TheOrangeRatchetCatException |
                System.out.println(e.getMessage());
                input = scanner.nextLine();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Correct input format for adding event: event <Task> /from <input> /to <input>");
                input = scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid number for the command.");
                input = scanner.nextLine();
            }
        }

        Ui.goodByeCat();
        scanner.close(); // Close the scanner to avoid resource leaks
    }
}

