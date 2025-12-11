package net.projectsync.designpatterns.b.structural;

/*
Structural Design Patterns:
 - Purpose: Deal with object composition. They focus on how classes and objects are composed to form larger structures
 - Key Idea: “How to combine objects and classes to form larger structures”
   
|------------|--------------------------|--------------------------------------|-------------------------------------|   
| Category   | Focus                    | Question Answered                    | Spring Example                      |
|------------|--------------------------|--------------------------------------|-------------------------------------|
| Structural | How objects are composed | “How do I connect objects?”          | JDBC Template, Spring Proxies       |
|------------|--------------------------|--------------------------------------|-------------------------------------|
*/

/**
 * Makes one interface compatible with another, or
 * Converts one interface into another expected by client
 *  - AuthenticationProvider (Database, LDAP, In-Memory)
 *  - Converts credentials into Spring Security’s internal auth model 
 */

class LegacyPaymentSystem {
	public void makePayment(double amount) {
		System.out.println("Payment processed: " + amount);
	}
}

interface PaymentProcessor {
	void makePayment(double amount);
}

class PaymentAdapter implements PaymentProcessor {
	private LegacyPaymentSystem legacyPaymentSystem;

	public PaymentAdapter(LegacyPaymentSystem legacyPaymentSystem) {
		this.legacyPaymentSystem = legacyPaymentSystem;
	}

	@Override
	public void makePayment(double amount) {
		// translate the new interface call to old system
		legacyPaymentSystem.makePayment(amount);
	}
}

public class AdapterPattern {
	public static void main(String[] args) {
		LegacyPaymentSystem legacyPaymentSystem = new LegacyPaymentSystem();
		PaymentAdapter paymentAdapter = new PaymentAdapter(legacyPaymentSystem);
		paymentAdapter.makePayment(1000d); // works with new interface
	}
}
