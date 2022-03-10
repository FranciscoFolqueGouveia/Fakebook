package user;

import Comment.Comment;
import Comment.CommentClass;
import exceptions.CantCommentPostException;
import exceptions.InvalidCommentStanceException;
import post.Post;


public class NaiveClass extends AbstractUserClass {
	/**
	 * type associated to this subClass of AbstractUserClass
	 */
	private static final String TYPE = "naive";

	public NaiveClass(String id) {
		super(id, TYPE);
	}

	public void post(Post post) {

		addPost(post);
		giveMeAndFriendsAccessToPost(post);
		
		if (!post.isHonest()) {
			addLie();
		}
	}

	public void comment(Post post, boolean positivity, String message) throws CantCommentPostException, InvalidCommentStanceException {

		if (positivity) {
			Comment comment = new CommentClass(this, positivity, message, post);
			post.addComment(comment);
			addComment(comment);

			if (!post.isHonest()) {
				addLie();
			}

		} else {
			throw new InvalidCommentStanceException();
		}

	}

	
}
