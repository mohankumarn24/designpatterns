package net.projectsync.designpatterns.a.creational.singleton;

// Double-Checked Locking Singleton
// Checks instance twice; synchronized only during first initialization
public class C_DCLSingleton {

    // volatile ensures visibility of instance across threads
    // and prevents instruction reordering issues
    private static volatile C_DCLSingleton instance;

    // Private constructor prevents external instantiation
    private C_DCLSingleton() { }

    public static C_DCLSingleton getInstance() {

        // First check (no locking) – improves performance
        if (instance == null) {

            // Only one thread can enter this block at a time
            synchronized (C_DCLSingleton.class) {

                // Second check – ensures instance is still null
                // before creating it (prevents multiple instances)
                if (instance == null) {
                    instance = new C_DCLSingleton();
                }
            }
        }

        // Return the singleton instance
        return instance;
    }
}


/*
// --------------- Explanation 1 ---------------
Step 1: First Check (no synchronization)
	if (instance == null) {
 - Most of the time, the instance is already created
 - This check avoids locking → fast
 - Only when instance is null, threads go inside
 - Assume two threads pass this check and enter first 'if' block
 
Step 2: Lock
	synchronized (C_DCLSingleton.class) {
 - Only one thread can enter this block at a time
 - Prevents simultaneous instance creation 

Step 3: Second Check
	if (instance == null) {
 - This is extremely important. Why?
     -- Because multiple threads might pass the first check simultaneously, but only the first one that acquires the lock should create the instance.
	 -- All others must see that the instance is already created.

Step 4: Create the instance
	instance = new C_DCLSingleton();
 - Now only one instance is created.


Why volatile is required?
 - Object creation in Java is not a single step.
 	-- It involves three separate actions:
		1. Allocate memory
		2. Initialize the object
		3. Assign the reference to instance
	
 - Without volatile, JVM may reorder steps 2 and 3:
	instance = memory;     // reference assigned EARLY
	initialize(memory);    // object initialized LATER

 - This causes:
 	-- Other threads may see a non-null but partially constructed object. This is a serious correctness bug.
 
 - volatile prevents reordering
    volatile:
 	-- Ensures visibility → once one thread writes, others see it
 	-- Prevents the JVM from reordering writes
 	-- Guarantees the object is fully initialized before assignment is visible
*/


// --------------- Explanation 2 ---------------
/*
Double-Checked Locking (DCL) is a pattern used to create a lazy-loaded,
thread-safe singleton without paying the synchronization cost
on every call to getInstance().

---------------------------------------------------------------
1. The singleton class
---------------------------------------------------------------
We declare:

    private static volatile C_DCLSingleton instance;

Why volatile?
- Ensures visibility: once a thread initializes the instance, all other
  threads will immediately see the updated value.
- Prevents instruction reordering during object creation.

---------------------------------------------------------------
2. Why reordering is dangerous
---------------------------------------------------------------
Without volatile, the JVM is allowed to reorder instructions like this:

    1. Allocate memory
    2. Assign memory to instance (instance becomes non-null)
    3. Initialize the object

This means another thread could see a non-null "instance"
before the object is fully constructed — a broken singleton.

volatile prevents this by forbidding reordering and ensuring
the object is fully initialized before any thread can see it.

---------------------------------------------------------------
3. The getInstance() logic
---------------------------------------------------------------

    public static C_DCLSingleton getInstance() {

        // First check (unsynchronized)
        // Very fast path: if instance is already created,
        // return it without locking.
        if (instance == null) {

            // Synchronize only when instance is null
            synchronized (C_DCLSingleton.class) {

                // Second check (inside synchronized block)
                // Ensures only one thread actually creates the instance.
                if (instance == null) {
                    instance = new C_DCLSingleton();
                }
            }
        }

        return instance;
    }

---------------------------------------------------------------
4. Why the two checks?
---------------------------------------------------------------
First check:
- Avoid expensive synchronization after the instance is created.
- Most calls will go through this and return immediately.

Second check:
- Multiple threads may pass the first check simultaneously.
- The second check ensures only one thread creates the instance.

---------------------------------------------------------------
5. Benefits of Double-Checked Locking
---------------------------------------------------------------
- Lazy initialization (object created only when needed)
- Thread-safe
- Very fast after initialization (minimal overhead)
- Avoids synchronization on every call

---------------------------------------------------------------
6. Final thoughts
---------------------------------------------------------------
Double-Checked Locking is safe only because the instance variable is volatile.
Before Java 5, this pattern was broken, but now it works correctly.

Even so, for production code, the Initialization-on-Demand Holder Pattern
is simpler and preferred:

    private static class Holder {
        static final MySingleton INSTANCE = new MySingleton();
    }

    public static MySingleton getInstance() {
        return Holder.INSTANCE;
    }
*/