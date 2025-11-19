package net.projectsync.designpatterns.b.structural;

import java.util.Map;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

// Simplifies a complex system with a single interface

// NotificationService Interface
interface NotificationService {
	void sendNotification();
}

// Concrete Implementation
@Service
class EmailService implements NotificationService {
	@Override
	public void sendNotification() {
		System.out.println("Email sent");
	}
}

@Service
class SMSService implements NotificationService {
	@Override
	public void sendNotification() {
		System.out.println("SMS sent");
	}
}

// Facade Class
@Service
class NotificationFacade {
	private final Map<String, NotificationService> notificationServiceMap;

	public NotificationFacade(Map<String, NotificationService> notificationServiceMap) {
		this.notificationServiceMap = notificationServiceMap;
	}

	public void notifyUser() {
		notificationServiceMap.forEach((name, service) -> service.sendNotification());
	}
}

/*
@Service
class NotificationFacade {
	private final List<NotificationService> notificationServiceList;

	public NotificationFacade(List<NotificationService> notificationServiceList) {
		this.notificationServiceList = notificationServiceList;
	}

	public void notifyUser() {
		notificationServiceList.forEach(notificationService -> notificationService.sendNotification());
	}
}
*/

@SpringBootApplication
public class FacadePattern implements CommandLineRunner {
	private final NotificationFacade notificationFacade;

	public FacadePattern(NotificationFacade notificationFacade) {
		this.notificationFacade = notificationFacade;
	}

	public static void main(String[] args) {
		SpringApplication.run(FacadePattern.class, args);
	}

	@Override
	public void run(String... args) {
		notificationFacade.notifyUser();
	}
}
