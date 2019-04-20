package shiftman.server;

public class CustomException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private String _errorMessage;
	public CustomException(String message) {
		super(message);
	}
}
