package exceptions;

public class UserDontHaveThatPost extends RuntimeException {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2760499017479827340L;
	
	private static final String MESSAGE = "%s has no post %s!";

	public UserDontHaveThatPost(String id, int postId) {
		super(String.format(MESSAGE, id, postId));
	}

}
