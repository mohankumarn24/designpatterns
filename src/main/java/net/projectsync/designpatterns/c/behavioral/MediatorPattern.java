package net.projectsync.designpatterns.c.behavioral;

import java.util.ArrayList;
import java.util.List;

// Mediator Interface
interface ChatMediator {
	void addUser(User user);
	void sendMessageToAll(String message, User user);
}

// Concrete Mediator
class ChatRoom implements ChatMediator {
	private List<User> users = new ArrayList<>();

	@Override
	public void addUser(User user) {
		users.add(user);
	}

	@Override
	public void sendMessageToAll(String message, User sender) {
		for (User user : users) {
			// Message should not be received by the sender
			if (user != sender) {
				user.receive(message);
			}
		}
	}
}

// Colleague
abstract class User {
	protected ChatMediator mediator;
	protected String name;

	public User(ChatMediator mediator, String name) {
		this.mediator = mediator;
		this.name = name;
	}

	public abstract void send(String message);
	public abstract void receive(String message);
}

// Concrete Colleague
class ChatUser extends User {
	public ChatUser(ChatMediator mediator, String name) {
		super(mediator, name);
	}

	@Override
	public void send(String message) {
		System.out.println(this.name + " sends: " + message);
		mediator.sendMessageToAll(message, this);
	}

	@Override
	public void receive(String message) {
		System.out.println(this.name + " receives: " + message);
	}
}

public class MediatorPattern {
	public static void main(String[] args) {
		ChatRoom chatRoom = new ChatRoom();

		ChatUser chatUser1 = new ChatUser(chatRoom, "Alice");
		ChatUser chatUser2 = new ChatUser(chatRoom, "Bob");
		ChatUser chatUser3 = new ChatUser(chatRoom, "Charlie");

		chatRoom.addUser(chatUser1);
		chatRoom.addUser(chatUser2);
		chatRoom.addUser(chatUser3);

		chatUser1.send("Hi everyone!");
	}
}
