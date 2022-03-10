package exceptions;

public class ResponsiveException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5549777833599123322L;
	private static final String MESSAGE = "Social distancing has reached fakebook. "
			+ "Post something and then comment your own post to become the king of responsiveness.";

	public ResponsiveException() {
		super(MESSAGE);

	}

}
