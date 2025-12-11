package net.projectsync.designpatterns.c.behavioral;

/**
 * Changes behavior when state changes
 *  - Java Thread States, Spring Bean Lifecycle States(Starting, Running, Stopping, Stopped)
 *  - Object behavior changes depending on lifecycle or thread state
 */

// Step 1: State Interface
interface OrderState {
	void processOrder(Order order);
}

// Step 2: Concrete States
class PendingState implements OrderState {
	@Override
	public void processOrder(Order order) {
		System.out.println("Order is pending. Moving to processed.");
		order.setState(new ProcessedState());
	}
}

class ProcessedState implements OrderState {
	@Override
	public void processOrder(Order order) {
		System.out.println("Order is processed. Moving to shipped.");
		order.setState(new ShippedState());
	}
}

class ShippedState implements OrderState {
	@Override
	public void processOrder(Order order) {
		System.out.println("Order is shipped. Order completed!");
		// No next state
	}
}

// Step 3: Context
class Order {
	private OrderState orderState;

	public Order() {
		this.orderState = new PendingState(); 			// Initial state
	}

	public void setState(OrderState orderState) {
		this.orderState = orderState;
	}

	public void processOrder() {
		orderState.processOrder(this);
	}
}

// Step 4: Test the pattern
public class StatePattern {
	public static void main(String[] args) {
		Order order = new Order();

		order.processOrder(); 							// Pending -> Processed
		order.processOrder(); 							// Processed -> Shipped
		order.processOrder(); 							// Shipped -> Completed
	}
}