package net.projectsync.designpatterns.c.behavioral;

// 1. Context class
class Context {
    private int age;

    public Context(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}

// 2. AbstractExpression
interface Expression {
    boolean interpret(Context context);
}

// 3. TerminalExpression: Adult
class AdultExpression implements Expression {
    @Override
    public boolean interpret(Context context) {
        return context.getAge() >= 18;
    }
}

// 4. TerminalExpression: Minor
class MinorExpression implements Expression {
    @Override
    public boolean interpret(Context context) {
        return context.getAge() < 18;
    }
}

// 5. Client code
public class InterpreterPattern {
    public static void main(String[] args) {
        Context john = new Context(20);
        Context alice = new Context(15);

        Expression adult = new AdultExpression();
        Expression minor = new MinorExpression();

        System.out.println("John is adult? " + adult.interpret(john));   // true
        System.out.println("Alice is minor? " + minor.interpret(alice)); // true
    }
}
