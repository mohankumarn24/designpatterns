package net.projectsync.designpatterns.a.creational;

import lombok.Data;

interface Prototype {
	Prototype clone();
}

@Data
class Book implements Prototype {
	private String data;

	public Book(String data) {
		this.data = data;
	}

	@Override
	public Prototype clone() {
		return new Book(this.data);
	}
}

public class PrototypePattern {
	public static void main(String[] args) {
		Book original = new Book("Original Book");
		Book copy = (Book) original.clone();
		copy.setData("Copied Object");

		System.out.println("Original: " + original.getData());
		System.out.println("Copy: " + copy.getData());
	}
}
