package exceptions;

public class InvalidHonestyForPostException extends RuntimeException {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6110019516779603185L;
	private static final String MESSAGE = "Invalid honesty for Post!";

	public InvalidHonestyForPostException() {
		super(MESSAGE);
	}

}