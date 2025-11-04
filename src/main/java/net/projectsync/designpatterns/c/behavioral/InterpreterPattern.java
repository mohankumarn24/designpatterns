package net.projectsync.designpatterns.c.behavioral;

class Person {
	private int age;

	public Person(int age) { this.age = age; }
	
	// Setters, Getters
	public void setAge(int age) { this.age = age; }
	public int getAge() { return age; }
}

interface Expression {
	boolean interpret(Person person);
}

class AdultExpression implements Expression {
	@Override
	public boolean interpret(Person person) {
		return person.getAge() >= 18;
	}
}

class MinorExpression implements Expression {
	@Override
	public boolean interpret(Person person) {
		return person.getAge() < 18;
	}
}

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

/*
package net.projectsync.designpatterns.c.behavioral;

class Person {
	private int age;

	public Person(int age) { this.age = age; }
	public void setAge(int age) { this.age = age; }
	public int getAge() { return age; }
}

@FunctionalInterface
interface Expression {
	boolean interpret(Person person);
}

public class InterpreterPattern {
	public static void main(String[] args) {

		Expression adultExpression = p -> p.getAge() >= 18;
		Expression minorExpression = p -> p.getAge() < 18;

		Person john = new Person(20);
		Person alice = new Person(15);

		System.out.println("John is adult? "  + adultExpression.interpret(john));
		System.out.println("Alice is minor? " + minorExpression.interpret(alice));
	}
}
*/