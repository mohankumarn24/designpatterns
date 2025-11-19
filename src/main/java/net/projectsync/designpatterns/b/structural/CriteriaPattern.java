package net.projectsync.designpatterns.b.structural;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

// Filters objects using different criteria, and allows combining these criteria flexibly

// Person class
@Data
@AllArgsConstructor
class Person {
	private String name;
	private String gender;
	private String maritalStatus;

	@Override
	public String toString() {
		return "Person [Name=" + name + ", Gender=" + gender + ", Marital Status=" + maritalStatus + "]";
	}
}

// Criteria interface
interface Criteria {
	List<Person> meetCriteria(List<Person> persons);
}

// Concrete Criteria: Male
class CriteriaMale implements Criteria {
	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		return persons.stream()
				.filter(person -> person.getGender().equalsIgnoreCase("MALE"))
				.collect(Collectors.toList());
	}
}

// Concrete Criteria: Single
class CriteriaSingle implements Criteria {
	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		return persons.stream()
				.filter(person -> person.getMaritalStatus().equalsIgnoreCase("SINGLE"))
				.collect(Collectors.toList());
	}
}

// AndCriteria: combines two criteria
class AndCriteria implements Criteria {
	private Criteria criteria;
	private Criteria otherCriteria;

	public AndCriteria(Criteria criteria, Criteria otherCriteria) {
		this.criteria = criteria;
		this.otherCriteria = otherCriteria;
	}

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);
		return otherCriteria.meetCriteria(firstCriteriaPersons);
	}
}

public class CriteriaPattern {
	public static void main(String[] args) {
		List<Person> persons = new ArrayList<>();

		persons.add(new Person("John", "Male", "Single"));
		persons.add(new Person("Jane", "Female", "Married"));
		persons.add(new Person("Mike", "Male", "Married"));
		persons.add(new Person("Mary", "Female", "Single"));

		Criteria male = new CriteriaMale();
		Criteria single = new CriteriaSingle();
		Criteria singleMale = new AndCriteria(single, male);

		System.out.println("Males:");
		for (Person person : male.meetCriteria(persons)) {
			System.out.println(person);
		}

		System.out.println("\nSingle Males:");
		for (Person person : singleMale.meetCriteria(persons)) {
			System.out.println(person);
		}
	}
}
