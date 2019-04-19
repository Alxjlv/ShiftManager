package shiftman.server;

public class InvalidInputException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private String _errorMessage;
	public InvalidInputException(String message) {
		super(message);
	}
}
