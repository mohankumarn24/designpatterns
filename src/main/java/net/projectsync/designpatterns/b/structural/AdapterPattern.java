package net.projectsync.designpatterns.b.structural;

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

class LegacyPaymentSystem {
	public void makePayment(double amount) {
		System.out.println("Payment processed: " + amount);
	}
}

public class AdapterPattern {
	public static void main(String[] args) {
		LegacyPaymentSystem legacyPaymentSystem = new LegacyPaymentSystem();
		PaymentAdapter paymentAdapter = new PaymentAdapter(legacyPaymentSystem);
		paymentAdapter.makePayment(1000d); // works with new interface
	}
}
