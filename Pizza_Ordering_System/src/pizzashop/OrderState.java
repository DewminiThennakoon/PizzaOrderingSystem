package pizzashop;

//Define the OrderState interface
interface OrderState {
    void next(OrderContext context);
    void prev(OrderContext context);
    void printStatus();
}

//Placed state
class PlacedState implements OrderState {
	//Move to the next state
    public void next(OrderContext context) {
        context.setState(new PreparingState());
    }

    public void prev(OrderContext context) {
        System.out.println("The order is already placed. Cannot go back.");
    }

    // Print the current status of the order
    public void printStatus() {
        System.out.println("Order placed. Awaiting preparation.");
    }
}

//Preparing state
class PreparingState implements OrderState {
    public void next(OrderContext context) {
        context.setState(new OutForDeliveryState());
    }

    public void prev(OrderContext context) {
        context.setState(new PlacedState());
    }

    public void printStatus() {
        System.out.println("Pizza is being prepared.");
    }
}

//OutForDelivery state
class OutForDeliveryState implements OrderState {
    public void next(OrderContext context) {
        context.setState(new DeliveredState());
    }

    public void prev(OrderContext context) {
        context.setState(new PreparingState());
    }

    public void printStatus() {
        System.out.println("Pizza is out for delivery.");
    }
}

//Delivered state
class DeliveredState implements OrderState {
    public void next(OrderContext context) {
        System.out.println("The pizza has already been delivered.");
    }

    public void prev(OrderContext context) {
        context.setState(new OutForDeliveryState());
    }

    public void printStatus() {
        System.out.println("Pizza delivered. Enjoy your meal!");
    }
}

// Manages the state transitions for an order
class OrderContext {
    private OrderState state;

    // Set the current state of the order
    public OrderContext() {
        state = new PlacedState();
    }

    // Set the current state of the order
    public void setState(OrderState state) {
        this.state = state;
    }

    // Move to the next state in the order process
    public void nextState() {
        state.next(this);
    }

    // Print the previuos status of the order
    public void prevState() {
        state.prev(this);
    }

    // Print the current status of the order
    public void printStatus() {
        state.printStatus();
    }
}
