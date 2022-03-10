package exceptions;

public class WrongNumberToListPostsException extends RuntimeException {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8690004715626737532L;
	private static final String MESSAGE = "Invalid number of posts to present!";

	public WrongNumberToListPostsException() {
		super(MESSAGE);

	}

}
