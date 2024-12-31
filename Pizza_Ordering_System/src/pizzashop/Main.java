package pizzashop;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Welcome Message
        System.out.println("-----Welcome to the Pizza Ordering System!-----");

        // Step 1: Pizza Customization using Builder Pattern
        System.out.println("\nStep 1: Customize your pizza");
        System.out.println("Choose Crust (Thin/Regular/Stuffed): ");
        String crust = scanner.nextLine();

        // Prompting the user to choose the crust type
        System.out.println("Choose Sauce (Tomato/Barbecue/Pesto): ");
        String sauce = scanner.nextLine();

        // Prompting the user to choose the cheede type
        System.out.println("Choose Cheese (Mozzarella/Cheddar/Parmesan): ");
        String cheese = scanner.nextLine();

        // Prompting the user to choose the toppings
        System.out.println("Choose Toppings (comma-separated: Olives, Peppers, Mushrooms): ");
        String[] toppingsInput = scanner.nextLine().split(",");
        List<String> toppings = Arrays.asList(toppingsInput);

        System.out.println("Enter Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); 
        
        // Building the pizza object using the Builder pattern
        Pizza pizza = new Pizza.Builder()
                .crust(crust)
                .sauce(sauce)
                .cheese(cheese)
                .toppings(toppings)
                .quantity(quantity)
                .build();
        
        // Displaying the customized pizza details
        System.out.println("\nYour customized pizza: ");
        System.out.println(pizza);
        
        //Choose order type
        int orderType = 0;
        boolean validInput = false;

        // Loop to ensure valid input for order type (1: Pickup, 2: Delivery
        while (!validInput) {
            try {
                System.out.println("\nStep 2: Choose Order Type (1: Pickup, 2: Delivery): ");
                orderType = scanner.nextInt();
                scanner.nextLine(); 
                if (orderType == 1 || orderType == 2) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter 1 for Pickup or 2 for Delivery.");
                }
            } catch (java.util.InputMismatchException e) {
            	// Handling invalid input (non-numeric)
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                scanner.nextLine(); // Clear invalid input
            }
        }

        if (orderType == 2) {
            // Delivery
            System.out.println("Enter your delivery address: ");
            String address = scanner.nextLine();

            // Mock Delivery Estimate
            System.out.println("Calculating delivery time...");
            System.out.println("Estimated delivery time: 30 minutes to " + address);
        } else {
        	
            // Pickup
            System.out.println("Your order will be ready for pickup in 20 minutes.");
        }
    
        
    //  Order Processing using Chain of Responsibility
        System.out.println("\nStep 2: Processing your order...");
        CustomizationHandler crustHandler = new CrustHandler();
        CustomizationHandler sauceHandler = new SauceHandler();
        CustomizationHandler toppingHandler = new ToppingHandler();

        crustHandler.setNextHandler(sauceHandler);
        sauceHandler.setNextHandler(toppingHandler);

        crustHandler.handleRequest("Crust");
        crustHandler.handleRequest("Sauce");
        crustHandler.handleRequest("Topping");           
   
        // Step 3: Payment using Strategy Pattern
        System.out.println("\nStep 3: Choose Payment Method (1: Credit Card, 2: Digital Wallet): ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); 

        //Select the payment method
        PaymentStrategy paymentMethod;
        if (paymentChoice == 1) {
        	// Assigns credit card payment strategy
            paymentMethod = new CreditCardPayment();
        } else {
        	// Assigns digital wallet payment strategy
            paymentMethod = new DigitalWalletPayment();
        }
        paymentMethod.pay(pizza.getPrice());

        // Step 4: Loyalty Program 
        System.out.println("\nStep 4: Earn Loyalty Points");
        
        // Calculates loyalty points based on the pizza's price
        int loyaltyPoints = (int) pizza.getPrice() / 10;
        System.out.println("You earned " + loyaltyPoints + " loyalty points!");

        // Step 5: Real-Time Order Tracking using Observer and State Patterns
        System.out.println("\nStep 5: Order Tracking");
        
        // Observer Pattern - Notifications for Customers
        OrderStatus orderStatus = new OrderStatus();
        Customer customer = new Customer("Dewmini");
        orderStatus.addObserver(customer);

        // State Pattern - Manage Order Progress
        OrderContext orderContext = new OrderContext();

        System.out.println("Order Status:");
        //placing state
        orderContext.printStatus();
        orderStatus.setStatus("Placed");

        orderContext.nextState();
        //preparing state
        orderContext.printStatus();
        orderStatus.setStatus("In Preparation");

        orderContext.nextState();
        //delivery state
        orderContext.printStatus();
        orderStatus.setStatus("Out for Delivery");

        orderContext.nextState();
        //delivered state
        orderContext.printStatus();
        orderStatus.setStatus("Delivered");

        // Step 6: Seasonal Specials and Promotions
        System.out.println("\nStep 6: Seasonal Specials and Promotions");
        System.out.println("Today's Special: 20% off on Stuffed Crust Pizzas!");
        
        //The system checks if the selected crust type is "Stuffed"
        if (crust.equalsIgnoreCase("Stuffed")) {
            double discount = pizza.getPrice() * 0.2;
            System.out.printf("You saved $%.2f on your order!\n", discount);
        }

        // Step 7: Feedback and Ratings using Command Pattern
        System.out.println("\nStep 7: Feedback and Ratings");
        System.out.println("Please enter your feedback: ");
        
        // Captures the user's feedback as a string using the Scanner object
        String feedbackInput = scanner.nextLine();

        // Create a FeedbackCommand object with the user's feedback
        Command feedbackCommand = new FeedbackCommand(feedbackInput);
        // Execute the feedback command to process and display the submitted feedback
        feedbackCommand.execute();

        System.out.println("Thank you for your order and feedback!");
    }
}
