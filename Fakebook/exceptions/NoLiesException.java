package exceptions;

public class NoLiesException extends RuntimeException {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3368150928696255511L;
	
	private static final String MESSAGE = "Social distancing has reached fakebook. Post a lie and become the king of liars.";

	public NoLiesException() {
		super(MESSAGE);
	

}}
