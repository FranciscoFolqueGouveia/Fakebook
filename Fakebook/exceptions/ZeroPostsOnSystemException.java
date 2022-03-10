package exceptions;

public class ZeroPostsOnSystemException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3801169193046683862L;
	
	private static final String MESSAGE = "Social distancing has reached fakebook. Post something to become the king of posters.";

	public ZeroPostsOnSystemException() {
		super(MESSAGE);

	}

}
