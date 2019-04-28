package shiftman.server;
/**
 * This custom exception class serves to return messages from methods which would normally fail to execute due to user error.
 * These messages are thrown to the ShiftManServer where the message is retrieved and fed back to the client.
 * This Exception class extends Exception so that when this error is caught, NullPointerExceptions and other unchecked exceptions
 * aren't accidently caught as well. This means that we have finer control over which checked exceptions are thrown and caught.
 * @author Alex Verkerk
 */
public class UserErrorException extends Exception {
	private static final long serialVersionUID = 1L;//Default ID, not used
	
	public UserErrorException(String message) {
		super(message);//Using the inbuilt methods of Exception to store the message
	}
}
