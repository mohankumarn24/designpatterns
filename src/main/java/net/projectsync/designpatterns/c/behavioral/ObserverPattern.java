package net.projectsync.designpatterns.c.behavioral;

import java.util.ArrayList;
import java.util.List;

// --- Observer Interface ---
interface Observer {
    void update(String news);
}

// --- Subject ---
class NewsAgency {
    private String news;
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer o : observers) {
            o.update(news);
        }
    }
}

// --- Concrete Observer ---
class NewsChannel implements Observer {
    private String news;

    @Override
    public void update(String news) {
        this.news = news;
        System.out.println("NewsChannel received: " + news);
    }
}

// --- Test ---
public class ObserverPattern {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        NewsChannel channel1 = new NewsChannel();
        NewsChannel channel2 = new NewsChannel();

        agency.addObserver(channel1);
        agency.addObserver(channel2);

        agency.setNews("Observer Pattern Simplified!");
    }
}
