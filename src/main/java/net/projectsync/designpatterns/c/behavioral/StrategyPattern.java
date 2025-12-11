package net.projectsync.designpatterns.c.behavioral;

/**
 * Encapsulates interchangeable algorithms, or
 * Encapsulates algorithms so they can vary independently
 * - 'PasswordEncoder' strategies, 'ViewResolver', 'DataSource' routing
 * - Algorithm chosen at runtime depending on configuration
 */

interface PaymentStrategy {
	void makePayment(int amount);
}

class CreditCardPayment implements PaymentStrategy {
	private String cardNumber;

	public CreditCardPayment(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public void makePayment(int amount) {
		System.out.println("Paid " + amount + " using Credit Card: " + cardNumber);
	}
}

class ChequePayment implements PaymentStrategy {
	private String chequeNumber;

	public ChequePayment(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	@Override
	public void makePayment(int amount) {
		System.out.println("Paid " + amount + " using Cheque: " + chequeNumber);
	}
}

class ShoppingCart {
	private PaymentStrategy paymentStrategy;

	public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
		this.paymentStrategy = paymentStrategy;
	}

	public void checkout(int amount) {
		if (paymentStrategy == null) {
			throw new IllegalStateException("PaymentStrategy not set!");
		}
		paymentStrategy.makePayment(amount);
	}
}

public class StrategyPattern {
	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();

		// Pay using Credit Card
		cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
		cart.checkout(200);

		// Switch to Cheque dynamically
		cart.setPaymentStrategy(new ChequePayment("12345"));
		cart.checkout(500);
	}
}
