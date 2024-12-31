package pizzashop;
import java.util.List;

public class Pizza {
	// Instance variables to store pizza details
    private String crust;
    private String sauce;
    private List<String> toppings;
    private String cheese;
    private String name; // Custom name for the pizza
    private int quantity;
    private double price;

    // Private constructor to create a Pizza object using the Builder pattern
    private Pizza(Builder builder) {
        this.crust 	  = builder.crust;
        this.sauce 	  = builder.sauce;
        this.toppings = builder.toppings;
        this.cheese   = builder.cheese;
        this.name 	  = builder.name;
        this.quantity = builder.quantity;
        this.price 	  = builder.price;
    }

    // Builder class to customize the pizza (Builder pattern)
    public static class Builder {
        private String crust;
        private String sauce;
        private List<String> toppings;
        private String cheese;
        private String name = "Custom Pizza";
        private int quantity = 1;
        private double price = 10.0; // Base price

        // Method to set the crust type, and adjust price
        public Builder crust(String crust) {
            this.crust = crust;
            if (crust.equalsIgnoreCase("Stuffed")) {
                this.price += 3.0;
            } else {
                this.price += 1.0;
            }
            return this;
        }

     // Method to set the sauce type
        public Builder sauce(String sauce) {
            this.sauce = sauce;
            return this;
        }
     // Method to set the list of toppings, and adjust price
        public Builder toppings(List<String> toppings) {
            this.toppings = toppings;
            this.price += toppings.size() * 1.5;
            return this;
        }
     // Method to set the cheese type, and adjust price
        public Builder cheese(String cheese) {
            this.cheese = cheese;
            this.price += 2.0;
            return this;
        }
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        // Method to set the quantity of pizzas
        public Builder quantity(int quantity) {
            this.quantity = quantity;
            this.price *= quantity;
            return this;
        }
        // Build method to create and return the final Pizza object
        public Pizza build() {
            return new Pizza(this);
        }
    }    
    // Method to get the price of the pizza
    public double getPrice() {
        return price;
    }

    // Method to represent the pizza for printing pizza details
    @Override
    public String toString() {
        return String.format("Pizza [%s, %s crust, %s sauce, %s cheese, Toppings: %s, Quantity: %d, Total: $%.2f]",
                name, crust, sauce, cheese, toppings, quantity, price);
    }
}
