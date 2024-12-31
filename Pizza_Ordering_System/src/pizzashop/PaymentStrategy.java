package pizzashop;

interface PaymentStrategy {
	// Abstract method to handle payment logic
    void pay(double amount);
}

//Payment logic for processing payments using a credit card
class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
    	// Displays a message indicating the amount paid via Credit Card
        System.out.println("Paid $" + amount + " using Credit Card.");
    }
}

//Payment logic for processing payments using a digital wallet
class DigitalWalletPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
    	// Displays a message indicating the amount paid via Digital Wallet
        System.out.println("Paid $" + amount + " using Digital Wallet.");
    }
}
