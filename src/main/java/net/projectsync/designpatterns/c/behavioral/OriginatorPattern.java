package net.projectsync.designpatterns.c.behavioral;

import java.util.Stack;

class FormMemento {
	private final String name;
	private final String email;

	public FormMemento(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
}

class FormHistory {
	private final Stack<FormMemento> history = new Stack<>();

	public void save(FormMemento memento) {
		history.push(memento);
	}

	public FormMemento undo() {
		return history.isEmpty() ? null : history.pop();
	}
}

class Form {
	private String name;
	private String email;

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public FormMemento save() {
		return new FormMemento(name, email);
	}

	public void restore(FormMemento memento) {
		if (memento != null) {
			this.name = memento.getName();
			this.email = memento.getEmail();
		}
	}

	@Override
	public String toString() {
		return "Name: " + name + ", Email: " + email;
	}
}

public class OriginatorPattern {
	public static void main(String[] args) {
		Form form = new Form();
		FormHistory history = new FormHistory();

		// Initial state
		form.setName("Mohan");
		form.setEmail("mohan@example.com");
		history.save(form.save()); // save snapshot

		// Update 1
		form.setName("Mohan Kumar");
		form.setEmail("mohan.kumar@example.com");
		history.save(form.save()); // save snapshot

		System.out.println("Current Form: " + form);

		// Undo last change
		form.restore(history.undo());
		System.out.println("After First Undo: " + form);

		// Another undo
		form.restore(history.undo());
		System.out.println("After Second Undo: " + form);
	}
}
