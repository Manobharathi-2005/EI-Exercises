package astronauts.schedule;


import astronauts.schedule.factory.TaskFactory;
import astronauts.schedule.manager.ScheduleManager;
import astronauts.schedule.model.Task;
import astronauts.schedule.observer.ConflictNotifier;
import  util.CommandParser;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %5$s %n");


        ScheduleManager scheduleManager = ScheduleManager.getInstance();
        TaskFactory taskFactory = new TaskFactory();
        ConflictNotifier notifier = new ConflictNotifier();
        scheduleManager.addObserver(notifier);

        Scanner scanner = new Scanner(System.in);

        System.out.println("🚀 Astronaut Daily Schedule Organizer 🚀");
        System.out.println("Commands: add, remove,edit, view, exit");
        System.out.println("Example: add \"Team Meeting\" 09:00 10:00 Medium");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) continue;

            List<String> parts = CommandParser.parse(input);
            String command = parts.get(0).toLowerCase();

            try {
                switch (command) {
                    case "add":
                        if (parts.size() != 5) {
                            System.out.println("Usage: add \"<description>\" <startTime> <endTime> <priority>");
                        } else {
                            Task task = taskFactory.createTask(parts.get(1), parts.get(2), parts.get(3), parts.get(4));
                            if (task != null) {
                                scheduleManager.addTask(task);
                            }
                        }
                        break;
                    case "remove":
                        if (parts.size() != 2) {
                            System.out.println("Usage: remove \"<description>\"");
                        } else {
                            scheduleManager.removeTask(parts.get(1));
                        }
                        break;
                    case "view":
                        List<Task> tasks = scheduleManager.getTasks();
                        if (tasks.isEmpty()) {
                            System.out.println("No tasks scheduled for the day.");
                        } else {
                            System.out.println("--- Daily Schedule ---");
                            tasks.forEach(System.out::println);
                            System.out.println("--------------------");
                        }
                        break;
                        case "exit":
                       System.out.println("Exiting scheduler. Goodbye!");
                        scanner.close();
                        return;

                        case "complete":
                            if (parts.size() != 2) {
                                System.out.println("Usage: complete \"<description>\"");
                            } else {
                                scheduleManager.markTaskAsCompleted(parts.get(1));
                            }
                            break;

                        case "edit":
                            if (parts.size() != 6) {
                                System.out.println("Usage: edit \"<old_desc>\" \"<new_desc>\" <startTime> <endTime> <priority>");
                            } else {
                                Task updatedTask = taskFactory.createTask(parts.get(2), parts.get(3), parts.get(4), parts.get(5));
                                if (updatedTask != null) {
                                    scheduleManager.editTask(parts.get(1), updatedTask);
                                }
                            }
                            break;

                        case "save":
                            if (parts.size() != 2) {
                                System.out.println("Usage: save <filename.csv>");
                            } else {
                                scheduleManager.saveScheduleToFile(parts.get(1));
                            }
                            break;

                        case "load":
                            if (parts.size() != 2) {
                                System.out.println("Usage: load <filename.csv>");
                            } else {
                                scheduleManager.loadScheduleFromFile(parts.get(1), taskFactory);
                            }
                            break;
                            default: System.out.println("Unknown command. Please use add, remove, view, or exit.");

                        break;
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "An unexpected error occurred for input: " + input, e);
                System.out.println("An unexpected error occurred. Please check the logs for details.");
            }
        }
    }
}