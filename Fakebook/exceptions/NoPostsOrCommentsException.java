package exceptions;

public class NoPostsOrCommentsException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4106899526964796785L;
	private static final String MESSAGE = "Social distancing has reached fakebook. Please post something.";

	public NoPostsOrCommentsException() {
		super(MESSAGE);

	}

}
