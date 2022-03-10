package user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import Comment.Comment;
import exceptions.*;
import post.Post;

public abstract class AbstractUserClass implements User {

	/**
	 * id - unique identifier of the User
	 */
	private String id;

	/**
	 * posts - Array to save the Posts of the User
	 */
	private List<Post> posts;

	/**
	 * type - type of the User (fanatic, selfCentered, liar or naive)
	 */
	private String type;

	/**
	 * List of the friends of the User
	 */
	private SortedMap<String, User> friends;

	/**
	 * List of comments of the User
	 */
	private List<Comment> comments;

	/**
	 * collection of the posts which was commented by the User
	 */
	private Set<Post> commentedPosts;

	/**
	 * numberLies - number of lies that the User made Includes posts and comments
	 */
	private int numberLies;

	/**
	 * receivedPosts - number of Posts that Friends posted after becoming friends
	 */
	private int receivedPosts;

	/**
	 * Constructor of AbstractUserClass
	 * 
	 * @param id - identifier of the User
	 */
	public AbstractUserClass(String id, String type) {

		this.id = id;
		this.posts = new ArrayList<Post>();
		this.receivedPosts = 0;
		this.type = type;
		this.friends = new TreeMap<String, User>();
		this.numberLies = 0;
		this.comments = new ArrayList<Comment>();
		this.commentedPosts = new HashSet<Post>();

	}

	@Override
	public Post getPost(int postId) throws UserDontHaveThatPost {
		int index = postId - 1;
		if (index < getPostsListSize() && index >= 0) {

			return posts.get(index);
		} else
			throw new UserDontHaveThatPost(getId(), postId);
	}

	@Override
	public Iterator<User> getFriendsIterator() {

		Collection<User> collection = friends.values();

		return collection.iterator();
	}

	@Override
	public Iterator<Post> getPostsIterator() {
		return this.posts.iterator();
	}

	@Override
	public Iterator<Comment> getCommentsIterator() {
		return this.comments.iterator();
	}

	@Override
	public String getId() {
		return id;

	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public int getNumberOfFriends() {
		return friends.size();
	}

	@Override
	public int getNumberOfPosts() {
		return this.posts.size();
	}

	@Override
	public int getNumberOfComments() {
		return this.comments.size();
	}

	@Override
	public int getNumberOfReceivedPosts() {
		return this.receivedPosts;
	}

	@Override
	public int setIdForPost() {
		return posts.size() + 1;
	}

	@Override
	public int getPostsListSize() {
		return posts.size();

	}

	@Override
	public int getNumberOfLies() {
		return this.numberLies;
	}

	@Override
	public int postsPlusComments() {
		int total = getNumberOfPosts() + getNumberOfComments();
		return total;
	}

	@Override
	public int getNumberOfPostsCommented() {
		return this.commentedPosts.size();
	}

	@Override
	public int totalPostsUserHasAccess() {
		return posts.size() + receivedPosts;
	}

	@Override
	public double getPercentageOfCommentedPosts() {
		double result = 0;
		if (totalPostsUserHasAccess() > 0) {
			result = (getNumberOfPostsCommented() * 100 / totalPostsUserHasAccess());

		}
		return result;
	}

	@Override
	public boolean hasCommentsOnTopic(String hashtag) {
		Iterator<Comment> it = comments.iterator();
		boolean result = false;

		while (it.hasNext() && !result) {
			Comment comment = it.next();
			if (comment.hasTopic(hashtag)) {
				result = true;
			}

		}

		return result;
	}

	@Override
	public boolean hasFriend(String id) {
		return friends.containsKey(id);
	}

	@Override
	public boolean isFriendsListEmpty() {
		return friends.isEmpty();
	}

	public boolean isPostsListEmpty() {
		return this.posts.isEmpty();
	}

	@Override
	public void addFriend(String friendId, User friend) throws AlreadyFriendsException {
		if (hasFriend(friendId)) {
			throw new AlreadyFriendsException(this.id, friendId);
		} else
			friends.put(friendId, friend);
	}

	/**
	 * This method regists the Post created in the Users posts collection
	 * 
	 * @param post made by the User
	 */
	protected void addPost(Post post) {

		this.posts.add(post);
	}

	public void receivePost() {
		this.receivedPosts++;
	}

	/**
	 * This method gives the User and his friends access to a Post
	 * 
	 * @param post made by the user
	 */
	protected void giveMeAndFriendsAccessToPost(Post post) {
		Iterator<User> it = this.getFriendsIterator();

		while (it.hasNext()) {
			User user = it.next();
			post.registUserInAccesList(user);
			user.receivePost();

		}
		post.registUserInAccesList(this);
	}

	@Override
	public void addComment(Comment comment) {

		this.comments.add(comment);
		Post commentedPost = comment.getPost();
		commentedPosts.add(commentedPost);
	}

	@Override
	public void addLie() {
		this.numberLies++;
	}

	public abstract void post(Post post) throws NotAllowedForUserException;

	public abstract void comment(Post post, boolean positivity, String message)
			throws CantCommentPostException, InvalidCommentStanceException;

}
