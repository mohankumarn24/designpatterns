package net.projectsync.designpatterns.c.behavioral;

import org.springframework.stereotype.Component;

abstract class Game {
	protected abstract void startPlay(); 		// Subclass provides implementation
	protected abstract void endPlay(); 			// Subclass provides implementation

	// Template method – final so subclasses cannot override
	public final void play() {
		startPlay(); 							// Step 1 – subclass-specific
		endPlay(); 								// Step 2 – subclass-specific
	}
}

class Cricket extends Game {
	@Override
	public void startPlay() {
		System.out.println("Cricket Game Started");
	}

	@Override
	public void endPlay() {
		System.out.println("Cricket Game Finished!");
	}
}

@Component
class Football extends Game {
	@Override
	public void startPlay() {
		System.out.println("Football Game Started");
	}

	@Override
	public void endPlay() {
		System.out.println("Football Game Finished!");
	}
}

public class TemplatePattern {
	public static void main(String[] args) {
		Cricket cricket = new Cricket();
		Football football = new Football();
		
		cricket.play();
		football.play();
	}
}
