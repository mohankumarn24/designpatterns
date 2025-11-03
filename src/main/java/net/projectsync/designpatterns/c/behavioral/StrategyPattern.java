package net.projectsync.standalone;

// ------------------- Strategy Interface -------------------
interface PaymentStrategy {
    void pay(int amount);
}

// ------------------- Concrete Strategy 1: Credit Card -------------------
class CreditCardPayment implements PaymentStrategy {

    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card: " + cardNumber);
    }
}

// ------------------- Concrete Strategy 2: PayPal -------------------
class PayPalPayment implements PaymentStrategy {

    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal: " + email);
    }
}

// ------------------- Context / Shopping Cart -------------------
class ShoppingCart {

    private PaymentStrategy paymentStrategy;

    // Set the strategy at runtime
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("PaymentStrategy not set!");
        }
        paymentStrategy.pay(amount);
    }
}

// ------------------- Main Class -------------------
public class StrategyPattern {

    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();

        // Pay using Credit Card
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
        cart.checkout(500);

        // Switch to PayPal dynamically
        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(300);
    }
}
