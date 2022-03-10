package exceptions;


public class NotAllowedForUserException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8867343493380360981L;
	
	
	
	private static final String MESSAGE = "Inadequate stance!";

	public NotAllowedForUserException() {
		super(MESSAGE);
	}

}
