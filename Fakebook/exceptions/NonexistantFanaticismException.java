package exceptions;

public class NonexistantFanaticismException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7002985667124374283L;
	
	private static final String MESSAGE = "Oh please, who would be a fanatic of %s?";

	public NonexistantFanaticismException(String fanaticism) {
		super(String.format(MESSAGE, fanaticism));
	}
}
