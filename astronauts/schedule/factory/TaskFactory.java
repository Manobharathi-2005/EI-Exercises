package astronauts.schedule.factory;

import astronauts.schedule.model.Task;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskFactory {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public Task createTask(String description, String startTimeStr, String endTimeStr, String priority) {
        try {
            LocalTime startTime = LocalTime.parse(startTimeStr, TIME_FORMATTER);
            LocalTime endTime = LocalTime.parse(endTimeStr, TIME_FORMATTER);
            return new Task(description, startTime, endTime, priority);
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid time format. Please use HH:mm.");
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
//factory class to create Task objects