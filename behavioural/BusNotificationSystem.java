package behavioural;

import java.util.ArrayList;
import java.util.List;


interface Observer {
    void update(String message);
}


interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}


class BusService implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private final String routeNumber;

    public BusService(String routeNumber) {
        this.routeNumber = routeNumber;
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
    public void updateStatus(String newStatus) {
        String fullMessage = "Bus #" + routeNumber + ": " + newStatus;
        System.out.println("\n[BUS SERVICE] " + fullMessage);
        notifyObservers(fullMessage);
    }
}


class Passenger implements Observer {
    private final String name;

    public Passenger(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("-> " + name + " received a notification: '" + message + "'");
    }
}


public class BusNotificationSystem {
    public static void main(String[] args) {

        BusService bus77B = new BusService("77B");
        Observer passengerAlice = new Passenger("Alice");
        Observer passengerBob = new Passenger("Bob");


        bus77B.addObserver(passengerAlice);
        bus77B.addObserver(passengerBob);


        bus77B.updateStatus("Arrived at Central Station.");


        bus77B.removeObserver(passengerAlice);


        bus77B.updateStatus("Delayed by 10 minutes due to traffic.");
    }
}