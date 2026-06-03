package net.projectsync.designpatterns.a.creational.singleton;

// Creates instance only when 'getInstance()' is first called
public class B_LazySingleton {

    // Instance is not created until first call of getInstance()
	// private static final B_LazySingleton instance; 				// compile error
    private static B_LazySingleton instance;

    // Private constructor to prevent outside instantiation
    private B_LazySingleton() { }

    public static B_LazySingleton getInstance() {

        // Lazy initialization: instance created only when needed
        // Not thread-safe: multiple threads may enter here simultaneously
        if (instance == null) { 
            instance = new B_LazySingleton();
        }

        return instance;
    }
}


/*
public class MyClass {
    public static void main(String[] args) {

        // Blank final local variable.
        // A final variable must be assigned exactly once before it is read.
        final String str1;

        // CTE #1: Reading before initialization
        //         Java requires a blank final local variable to be definitely assigned before any read.
        if (str1 == null) {
            str1 = "hello, world";
        }

        // CTE #2: Reassigning a final variable
        //         A final variable cannot be assigned again after initialization.
        final String str2 = "hello";
        str2 = "world";
    }
}

Expected Compile Time Errors:
variable str1 might not have been initialized
cannot assign a value to final variable str2
*/





/*
Thread A enters getInstance()
 - Sees instance == null
 - Goes inside the if block
 - Is about to run: instance = new B_LazySingleton();

Thread B enters getInstance()
 - This can happen before Thread A assigns the instance.
 - Thread B also sees instance == null
 - Also goes inside the if block
 - Also is about to run: instance = new B_LazySingleton();

Both threads create different objects
 - Thread A creates object A
 - Thread B creates object B
 - Both are assigned to instance at different times

Final result:
 - Two singleton objects exist in memory, violating the singleton pattern
 - Whichever thread writes last will "win" and overwrite the other
 
 
Visualization:
Initial: instance = null

Thread A: if (instance == null) -> true
Thread B: if (instance == null) -> true

Thread A: instance = new B_LazySingleton() (object A)
Thread B: instance = new B_LazySingleton() (object B)

Final result: instance -> object B 
(but object A was still created!)
*/