package net.projectsync.designpatterns.a.creational.singleton;

// Bill Pugh Singleton uses a static inner helper class to create a lazy-loaded, thread-safe singleton without synchronization, by relying on JVM classloading guarantees
// Instance created when inner class loads (lazy loading via JVM mechanism)
public class F_BillPughSingleton {

    // Private constructor prevents instantiation from outside
    private F_BillPughSingleton() { }

    // Static inner class - loaded only when getInstance() is called
    // JVM guarantees thread-safety during class loading
    private static class SingletonHolder {

        // Singleton instance created lazily when this inner class is loaded
        private static final F_BillPughSingleton INSTANCE = new F_BillPughSingleton();
    }

    // Global access point
    // Returns the instance held by SingletonHolder
    public static F_BillPughSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

// [App Start] -> [Runs 10 mins] -> [getInstance called] -> [Singleton Created] -> [Return]

/*
--------------- Two threads scenario ---------------
Key Principle: Class Loading is Thread-Safe
 - In Java, class loading & class initialization are guaranteed to be thread-safe by the JVM.

What happens when two threads call getInstance() simultaneously?
1️. Both threads call getInstance() at the SAME time: 
		F_BillPughSingleton.getInstance();
	- Inside the method:
		return SingletonHolder.INSTANCE;
	 - At this point, SingletonHolder class is NOT loaded yet.
	
2. JVM detects that SingletonHolder class must be initialized
	- Only ONE thread is allowed to initialize a class.
	- JVM guarantees: Class initialization is atomic and synchronized internally.
	- So:
		  -- Either T1 or T2 will start loading SingletonHolder
		  -- The other thread must wait until the class is fully initialized
 
3. Suppose T1 starts loading the inner static class
	T1:
		- Loads SingletonHolder class
		- Initializes static field:
			INSTANCE = new F_BillPughSingleton();   // constructor runs ONCE
		 - After the instance is created, initialization completes.
		
4. T2 is blocked until T1 finishes initialization
	- The moment SingletonHolder is initialized:
		-- T2 resumes
		-- But now the class is already initialized, so T2 does NOT create a new object
	- It simply returns the already-created instance.
*/

/*
 * --------------- Concept ---------------
 * How the Bill Pugh Singleton works:
 *
 * - The outer class (F_BillPughSingleton) is loaded as soon as the application references it. At this point, no instance is created.
 *
 * - The static inner class (SingletonHolder) is NOT loaded with the outer class. 
 * - It is loaded lazily—only when it is first accessed. 
 * - The instance is never created if the application never calls getInstance().
 *
 * - According to the JVM specification, nested classes are not initialized when their outer class is initialized. Each class is initialized separately.
 * - The JVM guarantees that class loading is thread-safe. You get synchronization for free without using the synchronized keyword
 *
 * - A class is initialized only upon its first active use, such as:
 *      • accessing a static field of that class
 *      • calling a static method of that class
 *      • creating an instance of that class
 *      • performing reflective operations that require initialization
 *
 * - Simply referencing the inner class inside the outer class does NOT load it.
 *
 * Result:
 * - The singleton instance is created only when SingletonHolder.INSTANCE is accessed.
 * - JVM class-loading guarantees make this lazy and thread-safe without synchronization.
 */





