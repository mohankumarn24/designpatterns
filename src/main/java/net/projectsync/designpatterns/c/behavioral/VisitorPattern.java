package net.projectsync.designpatterns.c.behavioral;

/*
Behavioral Design Patterns:
 - Purpose: Deal with communication between objects. They focus on how objects interact and share responsibility.
 - Key Idea: “How objects behave and interact with each other”

|------------|--------------------------|--------------------------------------|-------------------------------------|   
| Category   | Focus                    | Question Answered                    | Spring Example                      |
|------------|--------------------------|--------------------------------------|-------------------------------------|
| Behavioral | How objects communicate  | “How do objects talk to each other?” | Event handling, TransactionTemplate |
|------------|--------------------------|--------------------------------------|-------------------------------------|
*/

/**
 * Adds new operations to objects without modifying their classes, by separating algorithm from data structure
 *  - Spring bean traversal using 'BeanDefinitionVisitor'.@Value("${app.name}")
 *  - Visitor walks bean definitions and applies placeholder resolution  
 */

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
		Lion lion = new Lion();
		HealthCheck healthCheck = new HealthCheck();
		lion.accept(healthCheck);	// perform health check on animal
	}
}

// VIMM COSST
