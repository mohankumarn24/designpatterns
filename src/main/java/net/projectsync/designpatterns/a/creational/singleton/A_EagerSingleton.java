package net.projectsync.designpatterns.a.creational.singleton;

// Instance created at class loading time
public class A_EagerSingleton {

    // Instance is created by JVM at class loading time (eager initialization)
    private static final A_EagerSingleton instance = new A_EagerSingleton();

    // Private constructor prevents external instantiation
    private A_EagerSingleton() { }

    // Global access point - always returns the already-created instance
    public static A_EagerSingleton getInstance() {
        return instance;
    }
}


/*
| Class Name                | Type                   | How It Works                                                             | Pros                                          | Cons                                             |
| ------------------------- | ---------------------- | ------------------------------------------------------------------------ | --------------------------------------------- | ------------------------------------------------ |
|   A_EagerSingleton        | Eager Initialization   | Instance created at class loading time                                   | Simple, inherently thread-safe                | Creates instance even if unused                  |
|   B_LazySingleton         | Lazy (Non-thread-safe) | Creates instance only when 'getInstance()' is first called               | Saves resources until needed                  | Race condition → not thread-safe                 |
|   C_DCLSingleton          | Double-Checked Locking | Checks instance twice; synchronized only during first initialization     | Thread-safe, efficient (minimal locking)      | More complex; requires 'volatile'                |
|   D_ThreadSafeSingleton   | Synchronized Method    | Entire 'getInstance()' is synchronized                                   | Thread-safe, easy to implement                | Slow due to synchronization on every call        |
|   E_StaticBlockSingleton  | Static Block Singleton | Instance created inside a 'static' block when class is loaded 		    | Allows exception handling; thread-safe 	    | Still eager → instance created even if unused    |
|   F_BillPughSingleton     | Static Inner Class     | Instance created when inner class loads (lazy loading via JVM mechanism) | Best approach → lazy, thread-safe, no locking | None (generally considered ideal implementation) |
*/
