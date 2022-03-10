package exceptions;

public class InvalidCommentStanceException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1548829716255641287L;
	
	private static final String MESSAGE = "Invalid comment stance!";

	public InvalidCommentStanceException() {
		super(MESSAGE);
	
	}
	
	

}
