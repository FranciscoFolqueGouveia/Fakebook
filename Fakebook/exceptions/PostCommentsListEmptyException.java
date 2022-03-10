package exceptions;

public class PostCommentsListEmptyException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2372302256810283039L;
	private static final String MESSAGE = "No comments!";

	public PostCommentsListEmptyException() {

		super((MESSAGE));

	}
}
