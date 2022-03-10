package post;

import java.util.Iterator;

import Comment.Comment;
import topics.Topic;
import user.User;

public interface Post {

	/**
	 * @return User who upload the Post
	 */
	User getAuthor();

	/**
	 * @return the Topics Iterator of the Post
	 */
	Iterator<Topic> getTopicsIterator();

	/**
	 * @return the Comments Iterator of the Post
	 */
	Iterator<Comment> getCommentsIterator();

	/**
	 * @return Id of the Post
	 */
	int getId();

	/**
	 * @return the number of comments made in this Post
	 */
	int getNumberOfComments();

	/**
	 * @return the name of the User who created the Post
	 */
	String getAuthorName();

	/**
	 * @return message associated to the Post
	 */
	String getMessage();

	/**
	 * @return the veracity of the Post (honest or fake)
	 */
	String getVeracity();

	/**
	 * @return veracity of the Post (true means fake false means honest)
	 */
	boolean isHonest();

	/**
	 * @return <code>true</code> if Post has no comments
	 *          <code>false</code>
	 * 
	 */
	boolean isCommentsListEmpty();

	/**
	 * @param hashtag of a Certain Topic
	 * @return <code>true</code> if the Post has a Topic with the same hashtag
	 *         <code>false</code> if the opposites happens
	 */
	boolean hasTopic(String hashtag);

	/**
	 * @param user we want to check if has acces to Post
	 * @return <code>true</code> if the User is in Post access list of Users
	 *         <code>false</code> if the opposites happens
	 */
	boolean hasUserInAccessList(User user);

	/** This method regists a Topic in the Post topics collection
	 * @param Topic covered at the Post
	 */
	void registTopic(Topic Topic);

	/** This method add a comment in the Post comments Collection
	 * @param comment made about the Post
	 */
	void addComment(Comment comment);

	/** This method regist a User in the Post access list
	 * @param user we want to provide access to the Post
	 */
	void registUserInAccesList(User user);

}
