package net.projectsync.designpatterns.b.structural;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

// Printer Interface
interface PrinterB {
	void print();
}

// Real Printer Implementation
@Service
@Primary
class InkjetPrinterB implements PrinterB {
	@Override
	public void print() {
		System.out.println("Inkjet printer prints document");
	}
}

@Service
class LaserPrinterB implements PrinterB {
	@Override
	public void print() {
		System.out.println("Laser printer prints document");
	}
}

// Proxy Printer
// ✅ Fix: Don’t make ProxyPrinter a bean of type Printer
// Instead, inject the real Printer into it manually
// ProxyPrinter → needs Printer → could inject ProxyPrinter → depends on itself ❌ (The Circular Dependency Problem).
@Service
class ProxyPrinter {
	private final PrinterB realPrinter;

	public ProxyPrinter(PrinterB realPrinter) {
		this.realPrinter = realPrinter;
	}

	public void print() {
		System.out.println("---------------");
		realPrinter.print();
		System.out.println("---------------");
	}
}

@Service
class DocumentB {
	private final ProxyPrinter proxyPrinter;

	public DocumentB(ProxyPrinter proxyPrinter) {
		this.proxyPrinter = proxyPrinter;
	}

	public void print() {
		proxyPrinter.print();
	}
}

@SpringBootApplication
public class ProxyPattern implements CommandLineRunner {
	private final DocumentB document;

	public ProxyPattern(DocumentB document) {
		this.document = document;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProxyPattern.class, args);
	}

	@Override
	public void run(String... args) {
		document.print();
	}
}
