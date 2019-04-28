package shiftman.server;

public class UserErrorException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserErrorException(String message) {
		super(message);
	}
}
