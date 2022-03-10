package exceptions;

public class InvalidFanaticismListException extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3609760725986621086L;
	
	private static final String MESSAGE = "Invalid fanaticism list!";

	public InvalidFanaticismListException() {
		super(MESSAGE);
	
	}
	


}


