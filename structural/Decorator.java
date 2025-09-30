package structural;

interface Coffee {
    String getDescription();
    double getCost();
}

class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
    @Override
    public double getCost() {
        return 50.0;
    }
}


abstract class CoffeeDecorator implements Coffee {
    protected final Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// Concrete Decorators that add functionality.
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with Milk";
    }

    @Override
    public double getCost() {
        return super.getCost() + 15.0;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with Sugar";
    }

    @Override
    public double getCost() {
        return super.getCost() + 5.0;
    }
}


public class Decorator {
    public static void main(String[] args) {
        // Order a simple coffee.
        Coffee myCoffee = new SimpleCoffee();
        System.out.println("Order: " + myCoffee.getDescription());
        System.out.println("Cost: Rs." + myCoffee.getCost());

        System.out.println("---");

        // Now, decorate the coffee with milk and sugar.
        myCoffee = new MilkDecorator(myCoffee);
        myCoffee = new SugarDecorator(myCoffee);

        System.out.println("Order: " + myCoffee.getDescription());
        System.out.println("Cost: Rs." + myCoffee.getCost());
    }
}