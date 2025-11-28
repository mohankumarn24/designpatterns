package net.projectsync.designpatterns.a.creational.singleton;

// Ensures a class has only one instance

public class SingletonDemo {

	public static void main(String[] args) {

		System.out.println("=== Eager Singleton ===");
		A_EagerSingleton eager1 = A_EagerSingleton.getInstance();
		A_EagerSingleton eager2 = A_EagerSingleton.getInstance();
		System.out.println(eager1);
		System.out.println(eager2);
		System.out.println("Same instance? " + (eager1 == eager2));
		System.out.println();

		System.out.println("=== Lazy Singleton (Non-thread-safe) ===");
		B_LazySingleton lazy1 = B_LazySingleton.getInstance();
		B_LazySingleton lazy2 = B_LazySingleton.getInstance();
		System.out.println(lazy1);
		System.out.println(lazy2);
		System.out.println("Same instance? " + (lazy1 == lazy2));
		System.out.println();

		System.out.println("=== Double-Checked Locking Singleton ===");
		C_DCLSingleton dcl1 = C_DCLSingleton.getInstance();
		C_DCLSingleton dcl2 = C_DCLSingleton.getInstance();
		System.out.println(dcl1);
		System.out.println(dcl2);
		System.out.println("Same instance? " + (dcl1 == dcl2));
		System.out.println();

		System.out.println("=== Thread-Safe Singleton (synchronized) ===");
		D_ThreadSafeSingleton ts1 = D_ThreadSafeSingleton.getInstance();
		D_ThreadSafeSingleton ts2 = D_ThreadSafeSingleton.getInstance();
		System.out.println(ts1);
		System.out.println(ts2);
		System.out.println("Same instance? " + (ts1 == ts2));
		System.out.println();

		System.out.println("=== Static Block Singleton ===");
		E_StaticBlockSingleton sb1 = E_StaticBlockSingleton.getInstance();
		E_StaticBlockSingleton sb2 = E_StaticBlockSingleton.getInstance();
		System.out.println(sb1);
		System.out.println(sb2);
		System.out.println("Same instance? " + (sb1 == sb2));
		System.out.println();

		System.out.println("=== Bill Pugh Singleton ===");
		F_BillPughSingleton bp1 = F_BillPughSingleton.getInstance();
		F_BillPughSingleton bp2 = F_BillPughSingleton.getInstance();
		System.out.println(bp1);
		System.out.println(bp2);
		System.out.println("Same instance? " + (bp1 == bp2));
	}
}
