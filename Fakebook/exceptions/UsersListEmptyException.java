package exceptions;

public class UsersListEmptyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8104689887644604625L;

	private static final String MESSAGE = "There are no users!";

	public UsersListEmptyException() {
		super(MESSAGE);

	}
}
