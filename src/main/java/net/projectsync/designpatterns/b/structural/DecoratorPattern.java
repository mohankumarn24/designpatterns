package net.projectsync.designpatterns.b.structural;

/**
 * Adds new behavior/responsibilities dynamically
 *  - BeanPostProcessor, AOP proxies decorate original beans
 *  - Wraps beans with additional behavior (logging, caching, transactions)
 */

interface Coffee {
	String getDescription();
	double getPrice();
}

class SimpleCoffee implements Coffee {
	@Override
	public String getDescription() {
		return "Espresso";
	}

	@Override
	public double getPrice() {
		return 12.5;
	}
}

abstract class CoffeeDecorator implements Coffee {
	protected Coffee coffee;

	public CoffeeDecorator(Coffee coffee) {
		this.coffee = coffee;
	}
}

class MilkDecorator extends CoffeeDecorator {
	public MilkDecorator(Coffee coffee) {
		super(coffee);
	}

	@Override
	public String getDescription() {
		return coffee.getDescription() + " with Milk";
	}

	@Override
	public double getPrice() {
		return coffee.getPrice() + 2;
	}
}

class ChocolateDecorator extends CoffeeDecorator {
	public ChocolateDecorator(Coffee coffee) {
		super(coffee);
	}

	@Override
	public String getDescription() {
		return coffee.getDescription() + " and Chocolate";
	}

	@Override
	public double getPrice() {
		return coffee.getPrice() + 10;
	}
}

public class DecoratorPattern {
	public static void main(String[] args) {
		Coffee espresso = new SimpleCoffee();
		System.out.println(espresso.getDescription() + " $" + espresso.getPrice());

		Coffee milkEspresso = new MilkDecorator(espresso);
		System.out.println(milkEspresso.getDescription() + " $" + milkEspresso.getPrice());

		Coffee chocolateEspresso = new ChocolateDecorator(milkEspresso);
		System.out.println(chocolateEspresso.getDescription() + " $" + chocolateEspresso.getPrice());
	}
}
