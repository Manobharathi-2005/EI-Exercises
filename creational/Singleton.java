package creational;
class DatabaseConnection {

    private static final DatabaseConnection instance = new DatabaseConnection();


    private DatabaseConnection() {

        System.out.println("Creating a new database connection instance...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            // ignore
        }
    }

    public static DatabaseConnection getInstance() {
        return instance;
    }

    public void executeQuery(String query) {
        System.out.println("Executing query: '" + query + "'");
    }
}


public class Singleton {
    public static void main(String[] args) {
        System.out.println("--- Singleton Pattern Demo ---");

        DatabaseConnection conn1 = DatabaseConnection.getInstance();
        conn1.executeQuery("SELECT * FROM users");

        DatabaseConnection conn2 = DatabaseConnection.getInstance();
        conn2.executeQuery("SELECT * FROM products");


        System.out.println("Are both connections the same instance? " + (conn1 == conn2));
    }
}