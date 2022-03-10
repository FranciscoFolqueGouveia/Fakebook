package exceptions;

public class UsersCannotBeTheSameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8691326506784205221L;
	

	private static final String MESSAGE = "%s cannot be the same as %s!";

	public UsersCannotBeTheSameException(String name1, String name2) {
		super(String.format(MESSAGE, name1, name2));
	}
}