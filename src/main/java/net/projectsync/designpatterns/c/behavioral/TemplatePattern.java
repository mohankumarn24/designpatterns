package net.projectsync.designpatterns.c.behavioral;

import org.springframework.stereotype.Component;

/**
 * Defines skeleton of an algorithm, leaving steps to subclasses
 *  - JdbcTemplate:Get conn,create stmt,execute SQL,iterate ResultSet,handle ex,close resource
 *  - Spring templates fix algorithm steps and let user override specific parts 
 */

abstract class Game {
	protected abstract void startPlay(); 		// Subclass provides implementation
	protected abstract void endPlay(); 			// Subclass provides implementation

	public final void play() {					// Template method – final so subclasses cannot override
		startPlay(); 							// Step 1 – subclass-specific
		endPlay(); 								// Step 2 – subclass-specific
	}
}

class Cricket extends Game {
	@Override
	protected void startPlay() {
		System.out.println("Cricket Game Started");
	}

	@Override
	protected void endPlay() {
		System.out.println("Cricket Game Finished!");
	}
}

@Component
class Football extends Game {
	@Override
	protected void startPlay() {
		System.out.println("Football Game Started");
	}

	@Override
	protected void endPlay() {
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
