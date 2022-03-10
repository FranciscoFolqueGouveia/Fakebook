package exceptions;

public class WrongHashtagListException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3331315685820881180L;
	
	private static final String MESSAGE = "Invalid hashtags list!";

	public WrongHashtagListException() {
		super(MESSAGE);

	}

}
