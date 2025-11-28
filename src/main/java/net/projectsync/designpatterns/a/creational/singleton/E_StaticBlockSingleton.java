package net.projectsync.designpatterns.a.creational.singleton;

// Instance created inside a 'static' block when class is loaded 
// Designed for try-catch
public class E_StaticBlockSingleton {

    // Instance will be initialized inside the static block
    private static E_StaticBlockSingleton instance;

    // Static block runs once when the class is loaded into memory ie., JVM class Initialization
    // The static block runs as soon as the class is loaded into memory by the JVM, regardless of whether getInstance() is ever called
    // Useful when you need exception handling during initialization. If your Singleton constructor connects to a file system or database, you need to handle potential IOExceptions here
    static {
        try {
            instance = new E_StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in creating singleton instance", e);
        }
    }

    // Private constructor prevents creation of new instances
    private E_StaticBlockSingleton() { }

    // Returns the already-created instance
    public static E_StaticBlockSingleton getInstance() {
        return instance;
    }
}

// [App Start] -> [Singleton Created] -> [Runs 10 mins] -> [getInstance called] -> [Return]