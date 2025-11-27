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