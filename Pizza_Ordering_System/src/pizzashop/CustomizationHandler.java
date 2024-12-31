package pizzashop;

abstract class CustomizationHandler {
	// Reference to the next handler in the chain
    protected CustomizationHandler nextHandler;

    // Method to set the next handler in the chain
    public void setNextHandler(CustomizationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // Abstract method to handle customization requests
    public abstract void handleRequest(String request);
}

class ToppingHandler extends CustomizationHandler {
    @Override
    public void handleRequest(String request) {
    	// If the request matches "Topping," this handler processes it
        if (request.equalsIgnoreCase("Topping")) {
            System.out.println("Handling topping customization.");
        // Otherwise, the request is passed to the next handler in the chain
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class SauceHandler extends CustomizationHandler {
    @Override
    public void handleRequest(String request) {
    	// If the request matches "Sauce," this handler processes it
        if (request.equalsIgnoreCase("Sauce")) {
            System.out.println("Handling sauce customization.");
         // Otherwise, the request is passed to the next handler in the chain
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

class CrustHandler extends CustomizationHandler {
    @Override
    public void handleRequest(String request) {
    	// If the request matches "Crust," this handler processes it
        if (request.equalsIgnoreCase("Crust")) {
            System.out.println("Handling crust customization.");
         // Otherwise, the request is passed to the next handler in the chain
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}
