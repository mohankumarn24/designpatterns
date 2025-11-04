package net.projectsync.designpatterns.c.behavioral;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

interface Command {
	void execute();
}

@Component
class Light {
	public void turnOn() {
		System.out.println("Light is ON");
	}

	public void turnOff() {
		System.out.println("Light is OFF");
	}
}

@Component
class LightOnCommand implements Command {
	private final Light light;

	public LightOnCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.turnOn();
	}
}

@Component
class RemoteControl {
	private Command command;

	public void setCommand(Command command) {
		this.command = command;
	}

	public void pressButton() {
		command.execute();
	}
}

@SpringBootApplication
public class CommandPattern implements CommandLineRunner {
	private final RemoteControl remoteControl;
	private final LightOnCommand lightOnCommand;

	public CommandPattern(RemoteControl remoteControl, LightOnCommand lightOnCommand) {
		this.remoteControl = remoteControl;
		this.lightOnCommand = lightOnCommand;
	}

	public static void main(String[] args) {
		SpringApplication.run(CommandPattern.class, args);
	}

	@Override
	public void run(String... args) {
		remoteControl.setCommand(lightOnCommand); 	// Set command
		remoteControl.pressButton(); 				// Execute command
	}
}
