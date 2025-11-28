package net.projectsync.designpatterns.a.creational.singleton;

// 11 12
class Demo1 {
	// Just a reference, does not trigger constructor yet
    private static Demo1 demo1;

    static {
    	// 1. Runs FIRST when class loads
    	System.out.println("11");
    }

    Demo1() {
    	// 2. Runs AFTER class loading is done, when 'new' is called
    	System.out.println("12");
    }
}

// 22 21        22
class Demo2 {
	// 1. This static variable is first in the file, so it initializes FIRST.
    // It calls 'new Demo2()', triggering the constructor immediately.
    private static Demo2 demo2 = new Demo2();

    static {
    	// 3. This runs AFTER the static variable 'demo2' above is initialized.
    	System.out.println("21");
    }

    // constructor runs once during class initialization ie., private static Demo2 demo = new Demo2();
    Demo2() {
    	// 2. Runs first for the static 'demo2' instance.
        // 4. Runs again for the object created in main().
    	System.out.println("22");
    }
}

class Outer {
	// This block runs ONLY when the class is loaded into memory
	static {
	    System.out.println("Outer loaded");
	}
	
	// Static Inner classes are NOT loaded when Outer is loaded.
	public static class Inner {
	    static {
	        System.out.println("Inner loaded");
	    }
	}
}

public class Z_OrderDemo {
	public static void main(String[] args) {
	    System.out.println("Main running...");	// Main running...
	    System.out.println();
	    
	    // --- Scenario 1: Standard Order ---
	    // Class Load (Static Block) -> Object Creation (Constructor)
	    Demo1 demo1 = new Demo1();				// 11 12
		System.out.println();
		
		// --- Scenario 2: Tricky Order (Top-to-Bottom Rule) ---
        // 1. Class Load starts.
        // 2. 'static demo2' line executes -> calls Constructor -> prints "22"
        // 3. 'static {}' block executes -> prints "21"
        // 4. Class Load finished.
        // 5. 'new Demo2()' in main executes -> calls Constructor -> prints "22"
		Demo2 demo2 = new Demo2();				// 22 21        22
		System.out.println();
		
		// --- Scenario 3: Lazy Loading of Inner Classes ---
	    Outer outer = new Outer(); 				// Only Outer loads			-> 	Outer loaded
	    Outer.Inner inner = null;  				// Just a declaration. No loading happens.
	    inner = new Outer.Inner(); 				// NOW Inner loads			->	Inner loaded
	}
}

/*
JVM Class Loading Lifecycle (3 phases):
When JVM deals with a class, it performs these steps:

1. Loading
 - The .class file is read into memory.
 - Bytecode is stored in JVM method area.
 - No static fields or static blocks run here.

2. Linking
 - Verification (bytecode safety check)
 - Preparation
	-- JVM allocates memory for static variables
	-- BUT only default values are assigned (e.g., 0, null, false)
 - Resolution (replace symbolic references)

STILL your singleton object is NOT created here. (private static final A_EagerSingleton instance = new A_EagerSingleton();)

3. Initialization ⭐ (Important!)
This is the step where JVM executes:

 - Static variable assignments
	→ your singleton gets created here
	    private static final A_EagerSingleton instance = new A_EagerSingleton();
 - Static blocks
		static {
		    System.out.println("Static block executed");
		} 
 - Any other static initialization logic
*/





//--------------- Java vs Spring Boot misconception ---------------
/*
In plain Java:
- Static blocks run only when the class is first actively used
In Spring Boot:
- Spring actively uses many classes (via reflection, scanning, etc.) during startup.
- Therefore, static blocks run during application startup, making it appear like class-loading time.
*/

//--------------- JVM Classloading step ---------------
/*
JVM Rule:
- Static blocks run at class initialization time, not simply at “class loading” time
- The JVM performs:
	1. Loading → bytecode read into memory
	2. Linking → verify, prepare
	3. Initialization → run static blocks & static initializers (your static { } block runs here)
- Static blocks only run in step 3 → initialization.
- But initialization happens only on first active use (as we discussed earlier):
	-- instantiating the class
	-- accessing static fields
	-- invoking static methods
	-- reflection (Class.forName), etc.
*/