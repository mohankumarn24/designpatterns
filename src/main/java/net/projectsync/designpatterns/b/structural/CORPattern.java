package net.projectsync.designpatterns.b.structural;

// Handler
abstract class Handler {
	protected Handler next;

	public void setNext(Handler next) {
		this.next = next;
	}

	public abstract void handleRequest(String request);
}

// Concrete Handlers
class InfoHandler extends Handler {
	@Override
	public void handleRequest(String request) {
		if (request.equalsIgnoreCase("INFO")) {
			System.out.println("InfoHandler handled the request.");
		} else if (next != null) {
			next.handleRequest(request);
		}
	}
}

class ErrorHandler extends Handler {
	@Override
	public void handleRequest(String request) {
		if (request.equalsIgnoreCase("ERROR")) {
			System.out.println("ErrorHandler handled the request.");
		} else if (next != null) {
			next.handleRequest(request);
		}
	}
}

// Client
public class CORPattern {
	public static void main(String[] args) {
		Handler infoHandler = new InfoHandler();
		Handler errorHandler = new ErrorHandler();
		infoHandler.setNext(errorHandler); 			// chain
		infoHandler.handleRequest("ERROR"); 		// goes to ErrorHandler
	}
}
