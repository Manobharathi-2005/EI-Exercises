package astronauts.schedule.manager;

import astronauts.schedule.factory.TaskFactory;
import astronauts.schedule. model.Task;
import astronauts.schedule.model.TaskStatus;
import astronauts.schedule.observer.Observer;
import astronauts.schedule.observer.Subject;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
//singleton class to manage schedule tasks
public class ScheduleManager implements Subject {
    private static final Logger LOGGER = Logger.getLogger(ScheduleManager.class.getName());
    private static final ScheduleManager instance = new ScheduleManager();

    private final List<Task> tasks;
    private final List<Observer> observers;


    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        return instance;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public boolean addTask(Task newTask) {
        for (Task existingTask : tasks) {
            if (existingTask.isOverlapping(newTask)) {
                String conflictMessage = String.format("Error: Task '%s' conflicts with existing task '%s'.",
                        newTask.getDescription(), existingTask.getDescription());
                notifyObservers(conflictMessage);
                LOGGER.warning("Conflict detected when adding task: " + newTask.getDescription());
                return false;
            }
        }
        tasks.add(newTask);
        notifyObservers("Task '" + newTask.getDescription() + "' added successfully. No conflicts.");
        LOGGER.info("Task added: " + newTask.getDescription());
        return true;
    }

    public boolean removeTask(String description) {
        boolean removed = tasks.removeIf(task -> task.getDescription().equalsIgnoreCase(description));
        if (removed) {
            System.out.println("Task '" + description + "' removed successfully.");
            LOGGER.info("Task removed: " + description);
        } else {
            System.out.println("Error: Task '" + description + "' not found.");
            LOGGER.warning("Attempted to remove non-existent task: " + description);
        }
        return removed;
    }

    public List<Task> getTasks() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getStartTime))
                .collect(Collectors.toList());
    }
    // ... inside ScheduleManager class

    public void markTaskAsCompleted(String description) {
        tasks.stream()
                .filter(task -> task.getDescription().equalsIgnoreCase(description))
                .findFirst()
                .ifPresent(task -> {
                    task.setStatus(TaskStatus.COMPLETED);
                    System.out.println("Task '" + description + "' marked as completed.");
                    LOGGER.info("Task status changed to COMPLETED for: " + description);
                });
    }

    public boolean editTask(String oldDescription, Task updatedTask) {
        // Find the original task to be removed
        Task originalTask = tasks.stream()
                .filter(t -> t.getDescription().equalsIgnoreCase(oldDescription))
                .findFirst()
                .orElse(null);

        if (originalTask == null) {
            System.out.println("Error: Task to edit '" + oldDescription + "' not found.");
            return false;
        }

        // Check for conflicts against all other tasks
        for (Task existingTask : tasks) {
            if (existingTask != originalTask && existingTask.isOverlapping(updatedTask)) {
                String conflictMessage = String.format("Error: Edited task '%s' conflicts with existing task '%s'.",
                        updatedTask.getDescription(), existingTask.getDescription());
                notifyObservers(conflictMessage);
                return false;
            }
        }

        tasks.remove(originalTask);
        tasks.add(updatedTask);
        System.out.println("Task '" + oldDescription + "' has been successfully updated.");
        return true;
    }


    public void saveScheduleToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Task task : tasks) {
                // Format: description,startTime,endTime,priority,status
                writer.println(String.join(",",
                        "\"" + task.getDescription() + "\"",
                        task.getStartTime().toString(),
                        task.getEndTime().toString(),
                        task.getPriority(),
                        task.getStatus().name()
                ));
            }
            System.out.println("Schedule successfully saved to " + filename);
            LOGGER.info("Schedule saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error: Could not save schedule to file.");
            LOGGER.log(Level.SEVERE, "File save error", e);
        }
    }

    public void loadScheduleFromFile(String filename, TaskFactory factory) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            tasks.clear(); // Clear current schedule before loading
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (parts.length == 5) {
                    String description = parts[0].replace("\"", "");
                    Task task = factory.createTask(description, parts[1], parts[2], parts[3]);
                    if (task != null) {
                        task.setStatus(TaskStatus.valueOf(parts[4]));
                        tasks.add(task);
                    }
                }
            }
            System.out.println("Schedule successfully loaded from " + filename);
            LOGGER.info("Schedule loaded from file: " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found '" + filename + "'.");
        } catch (IOException e) {
            System.out.println("Error: Could not read schedule from file.");
            LOGGER.log(Level.SEVERE, "File load error", e);
        }
    }




}