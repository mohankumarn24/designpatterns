package net.projectsync.designpatterns.c.behavioral;

import java.util.Arrays;
import java.util.List;

// Visitor = Actions
interface Visitor {
	void visit(Lion lion);
	void visit(Elephant elephant);
	void visit(Monkey monkey);
}

// Concrete Visitor
class HealthCheck implements Visitor {
	public void visit(Lion lion) {
		System.out.println("Checking Lion's health");
	}
	
	public void visit(Elephant elephant) {
		System.out.println("Checking Elephant's health");
	}

	public void visit(Monkey monkey) {
		System.out.println("Checking Monkey's health");
	}
}

// Element = Animals
interface Animal {
	void accept(Visitor visitor);
}

// Concrete Elements
class Lion implements Animal {
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}

class Elephant implements Animal {
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}

class Monkey implements Animal {
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
}

public class VisitorPattern {
	public static void main(String[] args) {
		List<Animal> animals = Arrays.asList(new Lion(), new Elephant(), new Monkey());
		HealthCheck healthCheck = new HealthCheck();
		animals.forEach(animal -> animal.accept(healthCheck)); // Perform health check on each animal
	}
}
