package exceptions;

public class UserHasNoFriendsException extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 1163213035030492241L;
	private static final String MESSAGE = "%s has no friends!";

	public UserHasNoFriendsException(String name) {
		super(String.format(MESSAGE, name));

	}
}
