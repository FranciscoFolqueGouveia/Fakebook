package Comment;

import post.Post;
import user.User;

public interface Comment {

	/**
	 * @return User who created the Comment
	 */
	User getAuthor();
	
	/**
	 * 
	 * @return the Post where this Comment was created
	 */
	Post getPost();

	/**
	 * @return the id of the Comment author
	 */
	String getAuthorName();
	
	
	/**
	 * 
	 * @return the type of the Comment(positive or negative)
	 */
	String getStance();

	/**
	 * @return stance of the Comment <code>true</code> means it is positive
	 *                               <code>false</code> means it is negative
	 */
	boolean isPositive();
	
	/**
	 * 
	 * @param hashtag - hashtag that will be compared with the Topics of the Post where the Comment was created
	 * @return  <code>true</code> if the Post have a Topic with that hashtag
	 *          <code>false</code> if the opposite happens
	 */
	boolean hasTopic(String hashtag);

	/**
	 * @return message of the Comment
	 */
	String getMessage();
	
	

}
