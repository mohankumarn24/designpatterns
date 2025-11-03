package net.projectsync.designpatterns.c.behavioral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Autowired;

// -------------------- Template --------------------
// Abstract class defining the skeleton of the algorithm
abstract class Game {
    // Template method – final so subclasses cannot override
    public final void play() {
        initialize();   // Step 1 – subclass-specific
        startPlay();    // Step 2 – subclass-specific
        endPlay();      // Step 3 – subclass-specific
    }

    abstract void initialize(); // Subclass provides implementation
    abstract void startPlay();  // Subclass provides implementation
    abstract void endPlay();    // Subclass provides implementation
}

// -------------------- Concrete Implementations --------------------

// Cricket game implementation
@Component
@Primary // Marks this bean as primary if multiple Game beans are autowired
class Cricket extends Game {
    @Override
    void initialize() {
        System.out.println("Cricket Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }
}

// Football game implementation
@Component
class Football extends Game {
    @Override
    void initialize() {
        System.out.println("Football Game Initialized! Start playing.");
    }

    @Override
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }

    @Override
    void endPlay() {
        System.out.println("Football Game Finished!");
    }
}

// -------------------- Spring Boot Application --------------------
@SpringBootApplication
public class TemplatePattern implements CommandLineRunner {

    // Spring injects the Game beans automatically
    @Autowired
    private Cricket cricketGame;

    @Autowired
    private Football footballGame;

    public static void main(String[] args) {
        SpringApplication.run(TemplatePattern.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Using the template method to play games
        System.out.println("Playing Cricket:");
        cricketGame.play();

        System.out.println("\nPlaying Football:");
        footballGame.play();
    }
}
