package astronauts.schedule.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final String description;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String priority;

    public Task(String description, LocalTime startTime, LocalTime endTime, String priority) {
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new IllegalArgumentException("End time must be after start time.");
        }
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }


    public boolean isOverlapping(Task other) {
        return this.startTime.isBefore(other.endTime) && other.startTime.isBefore(this.endTime);
    }


    // ... inside Task class
    private TaskStatus status;





    // Updated toString() method
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String statusIndicator = (status == TaskStatus.COMPLETED) ? "✓ " : "  ";
        return String.format("%s%s - %s: %s [%s]",
                statusIndicator,
                startTime.format(formatter),
                endTime.format(formatter),
                description,
                priority);
    }




    public LocalTime getEndTime() {
        return endTime;
    }

    public String getPriority() {
        return priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}