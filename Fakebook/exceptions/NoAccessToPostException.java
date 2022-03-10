package exceptions;

public class NoAccessToPostException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5615665886012865405L;
	private static final String MESSAGE = "%s has no access to post %s by %s!";

	public NoAccessToPostException(String id1, int postId, String id2) {
		super (String.format(MESSAGE, id1, postId, id2));
	}
	

}
