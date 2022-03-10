package exceptions;

public class UserAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1146197990954235250L;
	private static final String MESSAGE = "%s already exists!";

	public UserAlreadyExistsException(String id) {
		super(String.format(MESSAGE, id));
	}

}
