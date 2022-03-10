package post;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Comment.Comment;
import topics.Topic;
import user.User;

/**
 * @author Francisco Gouveia / Martim Costa
 *
 */
public class PostClass implements Post {

	private static final String HONEST = "honest";
	private static final String FAKE = "fake";

	/**
	 * author - User who upload the Post
	 */
	private User author;

	/**
	 * id - unique identifier of the Post
	 */
	private int id;

	/**
	 * message associated to the Post
	 */
	private String message;

	/**
	 * veracity of the Post (either honest or fake)
	 */
	private boolean honesty;

	/**
	 * array of String that contain the hashtags of the Post
	 */
	private List<Topic> topics;

	private List<Comment> comments;

	private Set<User> usersWithAccess;

	/**
	 * 
	 * constructor of PostClass
	 * 
	 * @param user     - author of the Post
	 * @param id       - identifier of the Post
	 * @param message  - message of the Post
	 * @param nTopics  - number of Hashtags in the Post
	 * @param veracity - either honest or fake
	 * @param topics   - Set of Strings (hashtags of the Post)
	 */
	public PostClass(User user, int id, String message, boolean veracity, List<Topic> list) {
		this.author = user;
		this.id = id;
		this.message = message;
		this.honesty = veracity;
		this.topics = list;
		this.comments = new ArrayList<Comment>();
		this.usersWithAccess = new HashSet<User>();

	}

	@Override
	public User getAuthor() {
		return author;
	}
	@Override
	public Iterator<Topic> getTopicsIterator() {
		return topics.iterator();
	}
	@Override
	public Iterator<Comment> getCommentsIterator() {
		return comments.iterator();
	}

	@Override
	public int getId() {
		return id;
	}
	@Override
	public int getNumberOfComments() {
		return this.comments.size();
	}
	@Override
	public String getAuthorName() {
		return author.getId();
	}

	@Override
	public String getMessage() {
		return message;
	}
	@Override
	public String getVeracity() {
		if (honesty) {
			return HONEST;
		} else
			return FAKE;

	}

	@Override
	public boolean isHonest() {
		return honesty;
	}
	@Override
	public boolean isCommentsListEmpty() {
		return comments.isEmpty();
	}
	@Override
	public boolean hasTopic(String hashtag) {

		Iterator<Topic> it = topics.iterator();
		boolean result = false;

		while (it.hasNext() && !result) {
			Topic topic2 = it.next();

			if (hashtag.equalsIgnoreCase(topic2.getHashtag())) {

				result = true;
			}
		}
		return result;

	}
	@Override
	public boolean hasUserInAccessList(User u) {
		return usersWithAccess.contains(u);
	}
	

	@Override
	public void registTopic(Topic topic) {

		topics.add(topic);

	}
	@Override
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	@Override
	public void registUserInAccesList(User user) {

		this.usersWithAccess.add(user);
	}

}
