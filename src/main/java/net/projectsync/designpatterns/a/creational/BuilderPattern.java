package net.projectsync.designpatterns.a.creational;

// Builds complex objects step-by-step

class Computer {

    // Required fields
    private final String cpu;
    private final int ram;
    private final int storage;

    // Optional fields
    private final boolean graphicsCard;
    private final boolean bluetooth;
    private final boolean wifi;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.bluetooth = builder.bluetooth;
        this.wifi = builder.wifi;
    }

    public static class Builder {

        // Required fields
        private final String cpu;
        private final int ram;
        private int storage;
        
        // Optional fields
        private boolean graphicsCard;
        private boolean bluetooth;
        private boolean wifi;

        // Constructor for required fields
        public Builder(String cpu, int ram, int storage) {
            this.cpu = cpu;
            this.ram = ram;
            this.storage = storage;
        }

        public Builder graphicsCard(boolean graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public Builder bluetooth(boolean bluetooth) {
            this.bluetooth = bluetooth;
            return this;
        }

        public Builder wifi(boolean wifi) {
            this.wifi = wifi;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }

    @Override
    public String toString() {
        return "Computer {" +
                "cpu='" + cpu + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", graphicsCard=" + graphicsCard +
                ", bluetooth=" + bluetooth +
                ", wifi=" + wifi +
                '}';
    }
}


public class BuilderPattern {
    public static void main(String[] args) {
        Computer gamingPc = new Computer.Builder("Intel i9", 64, 512)
        		.graphicsCard(true)
                .wifi(true)
                .bluetooth(true)
                .build();

        Computer officePc = new Computer.Builder("Intel i5", 32, 256)
                .wifi(true)
                .build();

        System.out.println(gamingPc);
        System.out.println(officePc);
    }
}

/*
Computer {cpu='Intel i9', ram=32, storage=512, graphicsCard=true, bluetooth=true, wifi=true}
Computer {cpu='Intel i5', ram=8, storage=256, graphicsCard=false, bluetooth=false, wifi=true}
*/


/*
public class User {
	
	// final fields → immutable object
	private final String name;
	private final int age;
	private final String email;
	private final String country;
	private final boolean verified;
	
	// Private constructor accepts Builder
	private User(Builder builder) {
	    this.name = builder.name;
	    this.age = builder.age;
	    this.email = builder.email;
	    this.country = builder.country;
	    this.verified = builder.verified;
	}
	
	// Getters
	public String getName() { return name; }
	public int getAge() { return age; }
	public String getEmail() { return email; }
	public String getCountry() { return country; }
	public boolean isVerified() { return verified; }

	// Static Builder class
	public static class Builder {
	    private String name;
	    private int age;
	    private String email;
	    private String country;
	    private boolean verified;
	
	    public Builder name(String name) {
	        this.name = name;
	        return this;
	    }
	
	    public Builder age(int age) {
	        this.age = age;
	        return this;
	    }
	
	    public Builder email(String email) {
	        this.email = email;
	        return this;
	    }
	
	    public Builder country(String country) {
	        this.country = country;
	        return this;
	    }
	
	    public Builder verified(boolean verified) {
	        this.verified = verified;
	        return this;
	    }
	
	    // Final build() method
	    public User build() {
	        return new User(this);
	    }
	}
}

public class BuilderPattern {
    public static void main(String[] args) {

        // Create a User object using the Builder pattern
        User user = new User.Builder()
                .name("Mohan")
                .age(28)
                .email("mohan@example.com")
                .country("India")
                .verified(true)
                .build();

        // Print values
        System.out.println("Name: " + user.getName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Country: " + user.getCountry());
        System.out.println("Verified: " + user.isVerified());
    }
}
*/