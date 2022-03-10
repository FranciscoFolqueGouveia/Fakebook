package Comment;

import post.Post;
import user.User;

public class CommentClass implements Comment {
/**
 * POSITIVE - stance positive
 */
	private static final String POSITIVE = "positive";
	
	/**
	 * NEGATIVE - stance negative
	 */
	private static final String NEGATIVE = "negative";
	
/**
 * author - the User that created the Comment
 */
	private User author;
	
	/**
	 * positivity - the stance of the Comment
	 * <code>true</code> if is positive
	 * <code>false</code> if is negative
	 */
	private boolean positivity;
	
	/**
	 * message - the text of the Comment
	 */
	private String message;
	
	/**
	 * post - the Post where the Comment was created
	 */
	private Post post;

	
	/**
	 * 
	 * Constructor of CommentClass
	 * 
	 * @param author - the User who is author of the Comment
	 * @param stance - stance of the Comment
	 * @param message - the text of the Comment
	 * @param post - the Post where the Comment was created
	 */
	public CommentClass(User author, boolean stance, String message, Post post) {
		this.author = author;
		this.positivity = stance;
		this.message = message;
		this.post = post;
	}

	@Override
	public User getAuthor() {

		return author;
	}

	@Override
	public String getAuthorName() {
		return author.getId();
	}

	@Override
	public String getMessage() {

		return this.message;
	}

	@Override
	public String getStance() {
		if (positivity) {
			return POSITIVE;
		} else
			return NEGATIVE;

	}

	@Override
	public boolean isPositive() {

		return this.positivity;
	}

	@Override
	public boolean hasTopic(String hashtag) {
		return post.hasTopic(hashtag);
	}

	@Override
	public Post getPost() {
		return this.post;

	}

}
