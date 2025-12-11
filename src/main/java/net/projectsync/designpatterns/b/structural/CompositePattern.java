package net.projectsync.designpatterns.b.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * Treats a group of objects and a single object in the same way (tree-like structure)
 *  - Spring Security Filter Chain. Internally it is also a composite 
 *  - FilterChainProxy holds a list of filters and treats them as one
 */

interface Employee {
	void showEmployeeDetails();
}

class Developer implements Employee {
	private String name;
	private String position;

	public Developer(String name, String position) {
		this.name = name;
		this.position = position;
	}

	@Override
	public void showEmployeeDetails() {
		System.out.println(name + " works as " + position);
	}
}

class Manager implements Employee {
	private String name;
	private String position;
	private List<Employee> subordinates = new ArrayList<>();

	public Manager(String name, String position) {
		this.name = name;
		this.position = position;
	}

	public void add(Employee e) {
		subordinates.add(e);
	}

	public void remove(Employee e) {
		subordinates.remove(e);
	}

	@Override
	public void showEmployeeDetails() {
		System.out.println(name + " works as " + position);
		for (Employee e : subordinates) {
			e.showEmployeeDetails();
		}
	}
}

// Usage
public class CompositePattern {
	public static void main(String[] args) {
		Developer dev1 = new Developer("John", "Frontend Developer");
		Developer dev2 = new Developer("Jane", "Backend Developer");

		Manager manager = new Manager("Alice", "Project Manager");
		manager.add(dev1);
		manager.add(dev2);

		manager.showEmployeeDetails();
	}
}
