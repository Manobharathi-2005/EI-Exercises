# 🚀 Astronaut Daily Schedule Organizer

A console-based application designed to help astronauts manage their daily tasks. This project was built as a coding exercise to demonstrate proficiency in Java, Object-Oriented Programming, and the application of core software design patterns. The focus is on clean architecture, robust backend logic, and adherence to professional coding standards.
-----
## ⭐ Features

This application allows users to manage a daily schedule with the following capabilities:

  - [x] **Add Tasks**: Schedule a new task with a description, start time, end time, and priority level.
  - [x] **Remove Tasks**: Delete an existing task from the schedule.
  - [x] **View Schedule**: Display all scheduled tasks, sorted chronologically by start time.
  - [x] **Conflict Detection**: The system automatically prevents scheduling tasks that overlap with existing ones.
  - [x] **Edit Tasks**: Modify the details of an existing task.
  - [x] **Task Status**: Mark tasks as "Completed" and view their status.
  - [x] **Data Persistence**: Save the current schedule to a file and load it back in a future session.

-----

## 🔧 Design Patterns Used

This project was built using specific design patterns to ensure a clean, scalable, and maintainable codebase.

### 1\. Singleton Pattern

  * **Implementation**: The `ScheduleManager` class is implemented as a Singleton.
  * **Purpose**: To ensure that there is only one instance of the schedule manager throughout the application. This provides a single, global point of access to the schedule data, preventing inconsistencies and ensuring all operations work on the same set of tasks.

### 2\. Factory Pattern

  * **Implementation**: The `TaskFactory` class is used to create `Task` objects.
  * **Purpose**: To encapsulate the logic for object creation. The factory handles the parsing and validation of user input (like time strings) and the instantiation of `Task` objects. This decouples the client code from the complex creation process and makes the system easier to extend.

### 3\. Observer Pattern

  * **Implementation**: The `ScheduleManager` acts as the `Subject`, and the `ConflictNotifier` is an `Observer`.
  * **Purpose**: To create a flexible notification system. When the `ScheduleManager` detects a task conflict, it notifies all registered observers. This decouples the notification logic from the core scheduling logic. We could add new observers (like an email or push notification service) in the future without changing the `ScheduleManager`.

-----

## 💻 Technology Stack

  * **Language**: Java (JDK 11+)
  * **Logging**: `java.util.logging`

-----

## ⚙️ Setup and How to Run

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/Manobharathi-2005/EI-Exercises.git
    ```

2.  **Navigate to the project directory:**

    ```bash
    cd astronaut-schedule-organizer
    ```

## 📋 Usage Examples

The application accepts commands directly in the console.

| Command | Example |
| :--- | :--- |
| **Add a Task** | `add "Team Meeting" 09:00 10:00 Medium` |
| **View Schedule** | `view` |
| **Mark as Complete** | `complete "Team Meeting"` |
| **Edit a Task** | `edit "Team Meeting" "Daily Sync" 09:00 09:30 High` |
| **Remove a Task** | `remove "Daily Sync"` |
| **Save Schedule** | `save schedule.csv` |
| **Load Schedule**| `load schedule.csv` |
| **Exit** | `exit` |
