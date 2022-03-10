package user;

import Comment.Comment;
import Comment.CommentClass;
import exceptions.*;
import post.Post;

public class LiarClass extends AbstractUserClass  {

	/**
	 * type associated to this subClass of AbstractUserClass
	 */
	private static final String TYPE = "liar";

	public LiarClass(String id) {
		super(id, TYPE);
	}

	public void post(Post post) throws NotAllowedForUserException {

		if (post.isHonest()) {
			throw new NotAllowedForUserException();

		} else {
			addPost(post);
			giveMeAndFriendsAccessToPost(post);
			addLie();

		}
	}

	public void comment(Post post, boolean positivity, String message) throws CantCommentPostException, InvalidCommentStanceException {

		if (!positivity && post.isHonest() || (positivity && !post.isHonest())) {

			Comment comment = new CommentClass(this, positivity, message, post);
			post.addComment(comment);
			addComment(comment);
			addLie();
		}

		else {
			throw new InvalidCommentStanceException();
		}
	}

}
