package exceptions;

public class UserPostListEmptyException extends RuntimeException {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2003018286384152127L;
	
	private static final String MESSAGE = "%s has no posts!";

	public UserPostListEmptyException(String id) {
		
		super(String.format(MESSAGE, id));
	
}}
