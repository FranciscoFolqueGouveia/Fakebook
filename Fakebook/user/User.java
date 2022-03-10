package user;

import java.util.Iterator;
import Comment.Comment;
import exceptions.AlreadyFriendsException;
import exceptions.CantCommentPostException;
import exceptions.InvalidCommentStanceException;
import exceptions.NotAllowedForUserException;
import exceptions.UserDontHaveThatPost;
import post.Post;

public interface User {

	/**
	 * 
	 * @param postId - the id of the post
	 * @return  Post  with that <code>postId</code> of the User 
	 * @throws UserDontHaveThatPost - if the User dont have that Post with that <code>postId</code>
	 */
	Post getPost(int postId) throws UserDontHaveThatPost;

	/**
	 * 
	 * @return the Friends Iterator
	 */
	Iterator<User> getFriendsIterator();

	/**
	 * 
	 * @return the Posts Iterator
	 */
	Iterator<Post> getPostsIterator();

	/**
	 * 
	 * @return the Comments Iterator
	 */
	Iterator<Comment> getCommentsIterator();

	/**
	 * 
	 * @return the id of the User
	 */
	String getId();

	/**
	 * 
	 * @return  the type of the User
	 */
	String getType();

	/**
	 * 
	 * @return the number of friends of the User
	 */
	int getNumberOfFriends();

	/**
	 * 
	 * @return the number of Posts of the User
	 */
	int getNumberOfPosts();

	/**
	 * 
	 * @return the number of Comments of the User
	 */
	int getNumberOfComments();

	/**
	 * 
	 * @return the number of received Posts.
	 * This Posts are the Posts that the User receive from friends
	 */
	int getNumberOfReceivedPosts();

	/**
	 * 
	 * @return the number of Lies
	 */
	int getNumberOfLies();

	/**
	 * 
	 * @return the id of the Post
	 * This method is used when a new Post is created and added to the Post List
	 * It defines the Post id
	 */
	int setIdForPost();

	/**
	 * 
	 * @return the number of Posts of the User
	 */
	int getPostsListSize();
	
	

	/**
	 * 
	 * @return the total number of Comments and Posts made by the User
	 */
	int postsPlusComments();

	/**
	 * 
	 * @return the Posts that have at least one comment authored by the User
	 * 
	 */
	int getNumberOfPostsCommented();
	
	/**
	 * 
	 * @return the total posts that User has access
	 * This total includes the posts that the User created, and the Posts shared by his friends
	 */
	int totalPostsUserHasAccess();
	
	
	/**
	 * @return the percentage of number of comments in different Posts
	 */
	double getPercentageOfCommentedPosts();

	/**
	 * 
	 * @param hashtag- the hashtag of a Topic
	 * @return <code>true</code> if the User has at least one Comment with that Topic which has that <code>hashtag</code>
	 *        <code>false</code> if the opposite happens
	 */
	boolean hasCommentsOnTopic(String hashtag);

	/**
	 * 
	 * @param id - id of the friend that we want to search
	 * @return <code>true</code> if the  User with that <code>id</code> is in this User list of friends
	 *        <code>false</code> if the opposite happens
     */
	boolean hasFriend(String id);

	/**
	 * 
	 * @return <code>true</code> if the User list of friends is empty
	 *         <code>false</code> if the opposite happens
	 */ 
	boolean isFriendsListEmpty();

	/**
	 * 
	 * @return <code>true</code> if the User list of Posts is empty
	 *         <code>false</code> if the opposite happens
	 */
	boolean isPostsListEmpty();


	/**
	 * This method regist another User in the User friends collection
	 * @param friendId - the id of the friend 
	 * @param friend - Object User, friend that this User its gonna add  
	 * @throws AlreadyFriendsException - if the friend with that <code>id</code> is already in the friends list of this User
	 */
	void addFriend(String friendId, User friend) throws AlreadyFriendsException;

	/**
	 * This method regist the comment in the User comments collection;
	 * @param comment made by the User
	 */
	void addComment(Comment comment);

	/**
	 * This method increases by one the number of received posts of a User
	 */
	void receivePost();

	/**
	 * This method increases by one the number of lies of a User;
	 */
	void addLie();

	/**
	 * In this method the User makes a Post and the access to that Post is given to him and his friends;
	 * The author also saves the Post in his posts list;
	 * @param post created
	 * @throws NotAllowedForUserException - If the User (depends on the subclass type) cannot publish that post
	 */
	void post(Post post) throws NotAllowedForUserException;

	/**
	 * In this method the User make a comment in a Post he has access to
	 * @param post on which the comment will be made
	 * @param positivity - if the post is positive or not (negative)
	 * @param message of the comment 
	 * @throws CantCommentPostException - if the User is SelCentered and is trying to comment another User post
	 * @throws InvalidCommentStanceException - if the User (less SelfCentered ones), cannot comment on that stance 
	 */
	void comment(Post post, boolean positivity, String message)
			throws CantCommentPostException, InvalidCommentStanceException;

}
