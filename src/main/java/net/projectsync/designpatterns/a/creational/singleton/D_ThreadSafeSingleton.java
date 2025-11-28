package net.projectsync.designpatterns.a.creational.singleton;

// Entire 'getInstance()' is synchronized   
public class D_ThreadSafeSingleton {

    // Instance is created lazily (only when first requested)
    private static D_ThreadSafeSingleton instance;

    // Private constructor prevents object creation from outside
    private D_ThreadSafeSingleton() { }

    // Synchronized method ensures only one thread can access it at a time. This naturally prevents multiple instance creation without needing volatile
    // Makes lazy initialization thread-safe but reduces performance
    public static synchronized D_ThreadSafeSingleton getInstance() {

        // Create instance only if it doesn't exist
        if (instance == null) {
            instance = new D_ThreadSafeSingleton();
        }

        return instance;
    }
}

/*
Onlyone thread can enter Synchronized method 'getInstance()' at a time. 
This naturally prevents multiple instance creation without needing volatile

When thread A exits the synchronized method:
 - All writes to shared variables inside the synchronized block become visible to any thread B that later enters the synchronized block.
 - This avoids the issue of threads seeing a partially constructed object.
*/