package exceptions;

public class UserDoNotExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4653514377523379329L;
	/**
	 * 
	 */
	private static final String MESSAGE = "%s does not exist!";

	public UserDoNotExistsException(String id) {
		super(String.format(MESSAGE, id));
	}

}
