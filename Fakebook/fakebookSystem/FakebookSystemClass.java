package fakebookSystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import Comment.Comment;
import comparator.ComparatorByCommmentsPercentage;
import comparator.ComparatorByLies;
import comparator.ComparatorByNumberOfPosts;
import comparator.PostsComparator;
import exceptions.*;
import fanaticism.Fanaticism;
import post.*;
import topics.*;
import user.*;

public class FakebookSystemClass implements FakebookSystem {

	private static final String NAIVE = "naive";
	private static final String SELFCENTERED = "selfcentered";
	private static final String FANATIC = "fanatic";
	private static final String LIAR = "liar";
	private static final String LOVES = "loves";
	private static final String HATES = "hates";
	private static final String HONEST = "honest";
	private static final String FAKE = "fake";
	private static final String POSITIVE = "positive";
	private static final String NEGATIVE = "negative";

	/**
	 * List with objects User of the system
	 */
	private SortedMap<String, User> users;
/**
 * topics - List of all Topics in FakeBook
 */
	private Map<String, Topic> topics;
/**
 * allposts - List of all Posts in FakeBook
 */
	private Set<Post> allposts;

	/**
	 * Constructor of FakebookSystemClass; Initialize new TreepMap<String, User>()
	 * to save the Users of the system; The Map Key is a String which is the User id
	 * 
	 * 
	 */
	public FakebookSystemClass() {

		this.users = new TreeMap<String, User>();
		this.topics = new HashMap<String, Topic>();
		this.allposts = new HashSet<Post>();

	}

	@Override
	public User getUser(String id) throws UserDoNotExistsException {
		if (hasUser(id)) {
			return users.get(id);
		} else
			throw new UserDoNotExistsException(id);
	}
	@Override
	public Post getUserPost(String user, int postId) throws UserDontHaveThatPost, UserDoNotExistsException {
		return getUser(user).getPost(postId);
	}
	@Override
	public Post getPopularPost() throws NoPostsOrCommentsException {
		Comparator<Post> comparator = new PostsComparator();
		int max = 0;

		if (allposts.size() > 0) {
			Iterator<Post> it = allposts.iterator();

			Post popular = it.next();

			while (it.hasNext()) {
				Post post = it.next();
				if (comparator.compare(post, popular) < 0) {
					popular = post;
					max = post.getNumberOfComments();
				}
			}
			if (max > 0) {
				return popular;
			} else
				throw new NoPostsOrCommentsException();
		} else
			throw new NoPostsOrCommentsException();
	}

	@Override
	public List<Fanaticism> createNewFanaticismList(int size) {

		return new ArrayList<Fanaticism>(size);

	}
	@Override
	public List<Topic> createNewTopicList(int size) {

		return new ArrayList<Topic>(size);

	}

	@Override
	public Iterator<User> getUsersIterator() throws UsersListEmptyException {
		if (!isUsersListEmpy()) {

			Collection<User> values = users.values();
			return values.iterator();
		} else
			throw new UsersListEmptyException();
	}
	@Override
	public Iterator<Post> getUserPostsIterator(String user)
			throws UserDoNotExistsException, UserPostListEmptyException {
		if (!isUserPostListEmpty(user)) {
			return getUser(user).getPostsIterator();
		} else
			throw new UserPostListEmptyException(user);
	}
	@Override
	public Iterator<Comment> getPostCommentsIterator(String user, int postId)
			throws UserDoNotExistsException, UserDontHaveThatPost {

		Post post = getUserPost(user, postId);
		return post.getCommentsIterator();
	}

	@Override
	public Iterator<User> getFriendsIterator(String id) throws UserDoNotExistsException, UserHasNoFriendsException {
		if (!isUsersFriendsListEmpty(id)) {
			User user = getUser(id);

			return user.getFriendsIterator();
		} else
			throw new UserHasNoFriendsException(id);
	}

	@Override
	public Iterator<User> getTopicFanaticsIterator(String topic) throws NonexistantFanaticismException {
		if (hasTopic(topic)) {
			return getTopic(topic).getFanaticIterator();
		} else
			throw new NonexistantFanaticismException(topic);

	}
	@Override
	public Iterator<Comment> getUserCommentsIterator(String id) throws UserDoNotExistsException {
		return getUser(id).getCommentsIterator();
	}
	@Override
	public Iterator<Post> getTopicPostsIterator(String hashtag) throws NonexistantTopicException {

		if (hasTopic(hashtag)) {
			Topic topic = getTopic(hashtag);
			return topic.getPostsIterator();
		} else
			throw new NonexistantTopicException(hashtag);
	}
	@Override
	public Topic getTopic(String topic) {
		return topics.get(topic);

	}
	@Override
	public User getTopPoster() throws ZeroPostsOnSystemException, UsersListEmptyException {
		if (allposts.size() > 0) {

			Comparator<User> comparator = new ComparatorByNumberOfPosts();
			Iterator<User> it = this.getUsersIterator();
			User top = it.next();

			while (it.hasNext()) {
				User user = it.next();
				if (comparator.compare(top, user) < 0) {
					top = user;
				}
			}
			return top;
		} else
			throw new ZeroPostsOnSystemException();
	}
	@Override
	public User getShameless() throws NoLiesException, UsersListEmptyException {
		if (users.size() > 0) {

			Comparator<User> comparator = new ComparatorByLies();
			Iterator<User> it = this.getUsersIterator();
			User top = it.next();

			while (it.hasNext()) {
				User user = it.next();
				if (comparator.compare(top, user) < 0) {
					top = user;
				}
			}
			if (top.getNumberOfLies() > 0) {
				return top;
			} else
				throw new NoLiesException();
		} else {
			throw new NoLiesException();
		}
	}
	@Override
	public User getResponsive() throws UsersListEmptyException, ResponsiveException {
		if (allposts.size() > 0) {
			Comparator<User> comparator = new ComparatorByCommmentsPercentage();
			Iterator<User> it = getUsersIterator();
			User resp = it.next();
			while (it.hasNext()) {
				User user = it.next();
				if (comparator.compare(resp, user) < 0) {
					resp = user;
				}
			}
			if (resp.getNumberOfComments() > 0) {
				return resp;
			} else
				throw new ResponsiveException();
		} else
			throw new ResponsiveException();

	}

	@Override
	public boolean isKindFanatic(String kind) {
		return kind.equalsIgnoreCase(FANATIC);
	}

	@Override
	public boolean isFanatic(String id) throws UserDoNotExistsException {
		boolean result = false;
		User user = getUser(id);
		if (user.getType().equalsIgnoreCase(FANATIC)) {
			result = true;

		}
		return result;
	}

	@Override
	public boolean hasUser(String id) {
		return users.containsKey(id);
	}


	@Override
	public boolean fanaticismListHasTopic(List<Fanaticism> list, Topic topic) {

		Iterator<Fanaticism> it = list.iterator();
		boolean result = false;

		while (it.hasNext() && !result) {
			Fanaticism f = it.next();

			if (topic.hasTheSameHashtag(f.getTopic()))
				result = true;

		}
		return result;
	}
	@Override
	public boolean topicListHasTopic(List<Topic> list, Topic topic) {

		Iterator<Topic> it = list.iterator();
		boolean result = false;

		while (it.hasNext() && !result) {
			Topic topic2 = it.next();

			if (topic.hasTheSameHashtag(topic2)) {

				result = true;
			}
		}
		return result;
	}

	
	@Override
	public boolean doesItLoves(String value) throws InvalidTendencyForFanaticismException {
		if (value.equalsIgnoreCase(LOVES)) {
			return true;
		} else if (value.equalsIgnoreCase(HATES)) {
			return false;
		} else
			throw new InvalidTendencyForFanaticismException();
	}
	@Override
	public boolean isHonest(String value) throws InvalidHonestyForPostException {
		if (value.equalsIgnoreCase(HONEST)) {
			return true;
		} else if (value.equalsIgnoreCase(FAKE)) {
			return false;
		} else
			throw new InvalidHonestyForPostException();
	}
	@Override
	public boolean isPositive(String value) throws InvalidCommentStanceException {
		if (value.equalsIgnoreCase(POSITIVE)) {
			return true;
		} else if (value.equalsIgnoreCase(NEGATIVE)) {
			return false;
		} else
			throw new InvalidCommentStanceException();
	}
	@Override
	public boolean isPostCommentsListEmpty(String user, int postId)
			throws UserDoNotExistsException, UserDontHaveThatPost {

		Post post = getUserPost(user, postId);
		return post.isCommentsListEmpty();

	}
	@Override
	public boolean userHasCommentsOnTopic(String user, String hashtag) throws UserDoNotExistsException {

		User user1 = getUser(user);
		return user1.hasCommentsOnTopic(hashtag);

	}
	@Override
	public boolean hasUserAccessToPost(String user, String owner, int postId)
			throws UserDoNotExistsException, UserDontHaveThatPost {

		User user1 = getUser(user);
		Post post = getUserPost(owner, postId);
		return post.hasUserInAccessList(user1);
	}
	@Override
	public int getUserNumberOfFriends(String user) throws UserDoNotExistsException {
		return getUser(user).getNumberOfFriends();

	}
	@Override
	public int getUserLastPostId(String user) throws UserDoNotExistsException {
		return getUser(user).getPostsListSize();
	}

	@Override
	public void registerUser(String kind, String id) throws UnknownKindException, UserAlreadyExistsException {

		User user;

		if (kind.equals(NAIVE)) {
			user = new NaiveClass(id);
		} else if (kind.equals(LIAR)) {
			user = new LiarClass(id);
		} else if (kind.equals(SELFCENTERED)) {
			user = new SelfCenteredClass(id);
		} else if (kind.equals(FANATIC)) {
			user = new FanaticClass(id);
		} else {
			throw new UnknownKindException(kind);
		}

		if (hasUser(id)) {

			throw new UserAlreadyExistsException(id);
		} else {
			users.put(id, user);
		}

	}

	@Override
	public void setFanaticFanaticismList(String id, List<Fanaticism> list) throws UserDoNotExistsException {
		User u = getUser(id);
		((Fanatic) (u)).setFanaticismList(list);

		
	}

	@Override
	public void makeFriendship(String user1, String user2)
			throws UserDoNotExistsException, AlreadyFriendsException, UsersCannotBeTheSameException {

		User u1 = getUser(user1);
		User u2 = getUser(user2);

		if (user1.equalsIgnoreCase(user2)) {
			throw new UsersCannotBeTheSameException(user1, user2);
		} else {

			u1.addFriend(user2, u2);
			u2.addFriend(user1, u1);
		}
	}
	@Override
	public void post(String userId, String message, String honesty, List<Topic> topicsList)
			throws NotAllowedForUserException, UserDoNotExistsException, InvalidHonestyForPostException {

		User user1 = getUser(userId);
		boolean honest = isHonest(honesty);
		Post post = new PostClass(user1, user1.setIdForPost(), message, honest, topicsList);
		user1.post(post);

		allposts.add(post);
		registPostInHisTopics(post);

	}
	@Override
	public void comment(String id, String owner, int postId, String positivity, String comment)
			throws UserDoNotExistsException, UserDontHaveThatPost, CantCommentPostException,
			InvalidCommentStanceException, NoAccessToPostException {

		if (hasUserAccessToPost(id, owner, postId)) {
			User user = getUser(id);
			Post post = getUserPost(owner, postId);
			boolean positive = isPositive(positivity);

			user.comment(post, positive, comment);
		} else
			throw new NoAccessToPostException(id, postId, owner);

	}
	@Override
	public void registFanaticInHisTopics(String id) throws UserDoNotExistsException {

		User user = getUser(id);
		Iterator<Fanaticism> it = ((Fanatic) (user)).getFanaticismIterator();

		while (it.hasNext()) {
			Fanaticism fanaticism = it.next();
			Topic topic = fanaticism.getTopic();

			registTopicInSystem(topic);
			Topic topic1 = getTopic(topic.getHashtag());
			topic1.registFanatic(id, user);

		}

	}

	/**
	 * This method regists the Post in his Topics; It also regists
	 * topics in the system topics collection if they do not exist there already
	 * 
	 * @param post to regist in each Topic posts list
	 */
	private void registPostInHisTopics(Post post) {
		Iterator<Topic> it = post.getTopicsIterator();
		while (it.hasNext()) {
			Topic topic = it.next();
			registTopicInSystem(topic);
			
			Topic topic1 = getTopic(topic.getHashtag());
			topic1.registPost(post);
		}

	}

	/** This method regists a Topic in the System 
	 * @param topic to regist in the System collection of Topics
	 */
	private void registTopicInSystem(Topic topic) {

		String hashtag = topic.getHashtag();

		topics.putIfAbsent(hashtag, topic);

	}
	
	
	
	/**
	 * Checks if the collection of users of the system is empty or not;
	 * 
	 * @return true if the collection is Empty; else return false;
	 */

	private boolean isUsersListEmpy() {

		return users.isEmpty();
	}

	/**
	 * Checks if the collection of friends of a User is empty or not;
	 * 
	 * @param id of a User we want to access
	 * @return true if the friends collection of the User is Empty; else return
	 *         false;
	 * @throws UserDoNotExistsException - if that id does not corresponds to a
	 *                                  existence User in the system
	 */
	private boolean isUsersFriendsListEmpty(String id) throws UserDoNotExistsException {
		User user = getUser(id);

		return user.isFriendsListEmpty();
	}

	/**
	 * Checks if the list of posts of a User is empty or not;
	 * 
	 * @param id of a User we want to access
	 * @return true if the posts collection of the User is Empty; else return false;
	 * @throws UserDoNotExistsException - if that id does not corresponds to a
	 *                                  existence User in the system
	 */

	private boolean isUserPostListEmpty(String user) throws UserDoNotExistsException {

		return getUser(user).isPostsListEmpty();
	}
	
	/**
	 * @param hashtag associated to a Topic
	 * @return <code>true</code> if exists a Topic with that hashtag; else return <code>false</code>;
	 */
	private boolean hasTopic(String hashtag) {
		return topics.containsKey(hashtag);
	}
	

}
