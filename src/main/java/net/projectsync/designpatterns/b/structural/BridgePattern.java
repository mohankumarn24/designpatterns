package net.projectsync.designpatterns.b.structural;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Separates abstraction from implementation
 *  - @Cacheable("users"). Actual cache implementation can be Redis, Ehcache 
 *  - Single abstraction (`CacheManager`) works with many concrete cache providers
 */

interface Printer {
	void print(String content);
}

@Component
class LaserPrinter implements Printer {
	@Override
	public void print(String content) {
		System.out.println("Laser printing: " + content);
	}
}

@Component
@Primary
class InkjetPrinter implements Printer {
	@Override
	public void print(String content) {
		System.out.println("Inkjet printing: " + content);
	}
}

@Component
class Document {

	private final Printer printer;

	public Document(Printer printer) {
		this.printer = printer;
	}
	
	/*
	public Document(@Qualifier("laserPrinter") Printer printer) {		// comment out '@Primary' to use this
		this.printer = printer;
	}
	*/
	
	public void print(String content) {
		printer.print(content);
	}
}

@SpringBootApplication
public class BridgePattern implements CommandLineRunner {
	private final Document document;
	
	public BridgePattern(Document document) {
		this.document = document;
	}

	public static void main(String[] args) {
		SpringApplication.run(BridgePattern.class, args);
	}

	@Override
	public void run(String... args) {
		document.print("hello, moto");
	}
}
