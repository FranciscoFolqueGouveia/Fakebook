package exceptions;

public class AlreadyFriendsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3787342044631858807L;

	private static final String MESSAGE = "%s must really admire %s!";

	public AlreadyFriendsException(String name1, String name2) {
	
		super(String.format(MESSAGE, name1, name2));
	}
}
