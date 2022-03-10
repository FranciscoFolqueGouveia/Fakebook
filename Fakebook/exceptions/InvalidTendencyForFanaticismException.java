package exceptions;

public class InvalidTendencyForFanaticismException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5414909649954249838L;
	private static final String MESSAGE = "Invalid tendency for Fanaticism!";

	public InvalidTendencyForFanaticismException() {
		super(MESSAGE);
	}

}

