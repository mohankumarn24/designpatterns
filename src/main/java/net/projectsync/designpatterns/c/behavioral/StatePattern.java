package net.projectsync.designpatterns.c.behavioral;

// Step 1: State Interface
interface OrderState {
    void processOrder(OrderContext context);
}

// Step 2: Concrete States
class PendingState implements OrderState {
    @Override
    public void processOrder(OrderContext context) {
        System.out.println("Order is pending. Moving to processed.");
        context.setState(new ProcessedState());
    }
}

class ProcessedState implements OrderState {
    @Override
    public void processOrder(OrderContext context) {
        System.out.println("Order is processed. Moving to shipped.");
        context.setState(new ShippedState());
    }
}

class ShippedState implements OrderState {
    @Override
    public void processOrder(OrderContext context) {
        System.out.println("Order is shipped. Order completed!");
        // No next state
    }
}

// Step 3: Context
class OrderContext {
    private OrderState state;

    public OrderContext() {
        this.state = new PendingState(); // Initial state
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void processOrder() {
        state.processOrder(this);
    }
}

// Step 4: Test the pattern
public class StatePattern {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();

        order.processOrder(); // Pending -> Processed
        order.processOrder(); // Processed -> Shipped
        order.processOrder(); // Shipped -> Completed
    }
}
