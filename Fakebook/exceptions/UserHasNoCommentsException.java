package exceptions;

public class UserHasNoCommentsException extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3194323837276948930L;
	
	private static final String MESSAGE = "No comments!";

	public UserHasNoCommentsException() {
		super(String.format(MESSAGE));
	}

}
