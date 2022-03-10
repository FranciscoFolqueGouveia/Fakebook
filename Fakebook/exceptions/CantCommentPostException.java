package exceptions;

public class CantCommentPostException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8973307775973259593L;
	
	private static final String MESSAGE = "%s cannot comment on this post!";

	public CantCommentPostException(String id) {
		super(String.format(MESSAGE, id));
	}

}
