package net.projectsync.designpatterns.c.behavioral;

// 1. Context class
class Person {
	private int age;

	public Person(int age) { this.age = age; }
	
	// Setters, Getters
	public void setAge(int age) { this.age = age; }
	public int getAge() { return age; }
}

// 2. AbstractExpression
interface Expression {
	boolean interpret(Person person);
}

// 3. TerminalExpression: Adult
class AdultExpression implements Expression {
	@Override
	public boolean interpret(Person person) {
		return person.getAge() >= 18;
	}
}

// 4. TerminalExpression: Minor
class MinorExpression implements Expression {
	@Override
	public boolean interpret(Person person) {
		return person.getAge() < 18;
	}
}

// 5. Client code
public class InterpreterPattern {
	public static void main(String[] args) {
		Person john = new Person(20);
		Person alice = new Person(15);

		Expression adultExpression = new AdultExpression();
		Expression minorExpression = new MinorExpression();

		System.out.println("John is adult? "  + adultExpression.interpret(john)); 		// true
		System.out.println("Alice is minor? " + minorExpression.interpret(alice)); 		// true
	}
}
