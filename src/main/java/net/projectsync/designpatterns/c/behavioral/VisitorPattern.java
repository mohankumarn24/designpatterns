package net.projectsync.designpatterns.c.behavioral;

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
