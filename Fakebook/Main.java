import java.util.Scanner;
import java.util.Iterator;
import java.util.List;

import Comment.Comment;
import exceptions.*;
import fakebookSystem.*;
import fanaticism.*;
import post.*;
import topics.*;
import user.*;

/**
 * @author Martim Costa/Francisco Gouveia
 *
 */
public class Main {

	/**
	 * Constants that defines commands
	 */
	private static final String REGISTER = "REGISTER";
	private static final String USERS = "USERS";
	private static final String ADDFRIEND = "ADDFRIEND";
	private static final String FRIENDS = "FRIENDS";
	private static final String POST = "POST";
	private static final String USERPOSTS = "USERPOSTS";
	private static final String COMMENT = "COMMENT";
	private static final String READPOST = "READPOST";
	private static final String COMMENTSBYUSER = "COMMENTSBYUSER";
	private static final String TOPICFANATICS = "TOPICFANATICS";
	private static final String TOPICPOSTS = "TOPICPOSTS";
	private static final String POPULARPOST = "POPULARPOST";
	private static final String TOPPOSTER = "TOPPOSTER";
	private static final String RESPONSIVE = "RESPONSIVE";
	private static final String SHAMELESS = "SHAMELESS";
	private static final String HELP = "HELP";
	private static final String EXIT = "EXIT";

	/**
	 * Constants that defines the messages to users; HELP command messages
	 */

	private static final String REGISTER_HELP = "register - registers a new user";
	private static final String USERS_HELP = "users - lists all users";
	private static final String ADDFRIEND_HELP = "addfriend - adds a new friend";
	private static final String FRIENDS_HELP = "friends - lists the user friends";
	private static final String POST_HELP = "post - posts a new message";
	private static final String USERPOSTS_HELP = "userposts - lists all posts by a user";
	private static final String COMMENT_HELP = "comment - user comments on a post";
	private static final String READPOST_HELP = "readpost - prints detailed info on a post";
	private static final String COMMENTSBYUSER_HELP = "commentsbyuser - shows all the comments by a user on a given post";
	private static final String TOPICFANATICS_HELP = "topicfanatics - shows a list of fanatic users on a topic";
	private static final String TOPICPOSTS_HELP = "topicposts - shows a list of posts on a given topic";
	private static final String POPULARPOST_HELP = "popularpost - shows the most commented post";
	private static final String TOPPOSTER_HELP = "topposter - shows the user with more posts";
	private static final String RESPONSIVE_HELP = "responsive - shows the user with a higher percentage of commented posts";
	private static final String SHAMELESS_HELP = "shameless - shows the top liars";
	private static final String HELP_HELP = "help - shows the available commands";
	private static final String EXIT_HELP = "exit - terminates the execution of the program";

	/**
	 * Success messages
	 */
	private static final String REGISTERED = "%s registered.\n";
	private static final String FRIEND_ADDED = "%s is friend of %s.\n";
	private static final String POSTED = "%s sent a %s post to %d friends. Post id = %s.\n";
	private static final String LIST_POSTS = "%s posts:\n";
	private static final String COMMENT_ADDED = "Comment added!";

	/**
	 * EXIT and UKNOWNCOMMAND messages
	 */
	private static final String EXITING = "Bye!\n";
	private static final String UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.";

	public static void main(String[] args) throws UnknownKindException, UserAlreadyExistsException,
			UserDoNotExistsException, InvalidFanaticismListException, AlreadyFriendsException,
			UsersCannotBeTheSameException, NotAllowedForUserException, WrongHashtagListException,
			InvalidTendencyForFanaticismException, UserPostListEmptyException, CantCommentPostException,
			InvalidCommentStanceException, NoAccessToPostException, UserDontHaveThatPost,
			PostCommentsListEmptyException, InvalidHonestyForPostException, ZeroPostsOnSystemException, NoLiesException,
			UserHasNoCommentsException, NonexistantFanaticismException, UsersListEmptyException,
			WrongNumberToListPostsException, NonexistantTopicException, ResponsiveException {

		Scanner in = new Scanner(System.in);
		FakebookSystem fbs = new FakebookSystemClass();
		String comm = getCommand(in);

		while (!comm.equals(EXIT)) {
			switch (comm) {

			case REGISTER:
				register(in, fbs);
				break;
			case USERS:
				listUsers(fbs);

				break;
			case ADDFRIEND:
				addFriend(in, fbs);

				break;
			case FRIENDS:
				listFriends(in, fbs);
				break;
			case POST:
				post(in, fbs);

				break;
			case USERPOSTS:
				listPosts(in, fbs);
				break;

			case COMMENT:
				comment(in, fbs);
				break;

			case READPOST:
				readPost(in, fbs);
				break;
			case COMMENTSBYUSER:
				commentsByUser(in, fbs);
				break;
			case TOPICFANATICS:
				topicFanatics(in, fbs);
				break;

			case TOPICPOSTS:
				topicPosts(in, fbs);
				break;
			case POPULARPOST:
				popularPost(fbs);
				break;
			case TOPPOSTER:
				topPoster(fbs);
				break;
			case RESPONSIVE:
				responsive(fbs);
				break;
			case SHAMELESS:
				shameless(fbs);
				break;
			case HELP:
				help();
				break;
			default:
				System.out.println(UNKNOWN_COMMAND);
				in.nextLine();
				break;

			}

			comm = getCommand(in);
		}
		System.out.print(EXITING);

		in.close();
	}

	private static String getCommand(Scanner in) {
		String input;
		input = in.next().toUpperCase();
		return input;
	}

	/**
	 * This method regists a new User in the system. It scans the user id and the
	 * kind of user to create;
	 * 
	 * @param in  - Scanner
	 * @param fbs - FakebookSystem
	 * @throws InvalidFanaticismListException        - if the Fanaticisms list
	 *                                               scanned is incorrect (in case
	 *                                               of registering a Fanatic user)
	 * @throws UnknownKindException                  - if the kind of User to create
	 *                                               is not recognize
	 * @throws UserAlreadyExistsException            - if there already exists a
	 *                                               user with the same id in the
	 *                                               system
	 * @throws InvalidTendencyForFanaticismException - if the tendency associated to
	 *                                               a Topic is not either "loves"
	 *                                               or "hates";
	 * @throws UserDoNotExistsException              - if the user was not
	 *                                               registered
	 */
	private static void register(Scanner in, FakebookSystem fbs)
			throws InvalidFanaticismListException, UnknownKindException, UserAlreadyExistsException,
			UserDoNotExistsException, InvalidTendencyForFanaticismException {

		String kind = in.next();
		String id = in.nextLine().trim();

		try {
			if (fbs.isKindFanatic(kind)) {
				Main.registFanatic(in, fbs, kind, id);
			} else {
				fbs.registerUser(kind, id);
				System.out.printf(REGISTERED, id);
			}

		} catch (InvalidFanaticismListException exception) {
			System.out.println(exception.getMessage());
		} catch (UserAlreadyExistsException exception2) {
			System.out.println(exception2.getMessage());
		} catch (UnknownKindException exception3) {
			System.out.println(exception3.getMessage());
		}
	}

	/**
	 * This method scans as many fanaticisms as we define and save them in a
	 * Fanaticism list
	 * 
	 * @param in     - Scanner
	 * @param fbs    - FakeBookSystem
	 * @param toScan - number of fanaticisms (hashtag and tendecy) to scan
	 * @param list   - Fanaticism list to save the Fanaticisms
	 * @throws InvalidTendencyForFanaticismException - if the tendency associated to
	 *                                               a Topic is not either "loves"
	 *                                               or "hates";
	 * @throws InvalidFanaticismListException        - if the Fanaticisms list
	 *                                               scanned is incorrect (in case
	 *                                               of registering a Fanatic user).
	 *                                               Fanaticisms cannot be
	 *                                               duplicated
	 */
	private static void scanFanaticismList(Scanner in, FakebookSystem fbs, int toScan, List<Fanaticism> list)
			throws InvalidTendencyForFanaticismException, InvalidFanaticismListException {

		int scanned = 0;

		while (scanned < toScan) {
			String tendency = in.next();
			String hashtag = in.next().trim();
			scanned++;
			Topic topic = new TopicClass(hashtag);
			Fanaticism fanaticism = new FanaticismClass(topic, fbs.doesItLoves(tendency));

			if (!fbs.fanaticismListHasTopic(list, topic)) {
				list.add(fanaticism);
			} else {
				while (scanned < toScan) {
					in.next();
					in.next();
					scanned++;
				}
				throw new InvalidFanaticismListException();
			}
		}
	}

	/**
	 * This method regists a fanatic user and give him his Fanaticism list
	 * 
	 * @param in   - Scanner
	 * @param fbs  - FakeBookSystem
	 * @param kind - of User to create (in this case must equals "fanatic")
	 * @param id   - of the new User
	 * @throws InvalidFanaticismListException        - if the Fanaticisms list
	 *                                               scanned is incorrect (in case
	 *                                               of registering a Fanatic user)
	 * @throws UnknownKindException                  - if the kind of User to create
	 *                                               is not recognize
	 * @throws UserAlreadyExistsException            - if there already exists a
	 *                                               user with the same id in the
	 *                                               system
	 * @throws UserDoNotExistsException              - if the user was not
	 *                                               registered
	 * @throws InvalidTendencyForFanaticismException - if the tendency associated to
	 *                                               a Topic is not either "loves"
	 *                                               or "hates";
	 */

	private static void registFanatic(Scanner in, FakebookSystem fbs, String kind, String id)
			throws InvalidFanaticismListException, UnknownKindException, UserAlreadyExistsException,
			UserDoNotExistsException, InvalidTendencyForFanaticismException {

		int toScan;
		toScan = in.nextInt();
		List<Fanaticism> list = fbs.createNewFanaticismList(toScan);
		Main.scanFanaticismList(in, fbs, toScan, list);

		fbs.registerUser(kind, id);
		fbs.setFanaticFanaticismList(id, list);
		fbs.registFanaticInHisTopics(id);

		System.out.printf(REGISTERED, id);
	}

	/**
	 * This method list all users in the System collection of users
	 * 
	 * @param fbs - FakeBookSystem
	 * @throws UsersListEmptyException - if there are no users in the System
	 *                                 collection
	 */
	private static void listUsers(FakebookSystem fbs) throws UsersListEmptyException {
		try {
			Iterator<User> users = fbs.getUsersIterator();

			while (users.hasNext()) {
				User user = users.next();
				System.out.println(user.getId() + " [" + user.getType() + "] " + user.getNumberOfFriends() + " "
						+ +user.getNumberOfPosts() + " " + user.getNumberOfComments());

			}

		} catch (UsersListEmptyException exception) {
			System.out.println(exception.getMessage());

		}
	}

	/**
	 * This method creates a friendship between two users; For that, it scans the
	 * first and the second User id;
	 * 
	 * @param in  - Scanner
	 * @param fbs - FakeBookSystem
	 * @throws UserDoNotExistsException      - if one of the users does not exist in
	 *                                       the system
	 * @throws AlreadyFriendsException       - if those two users are already
	 *                                       friends
	 * @throws UsersCannotBeTheSameException - if the two id given to create
	 *                                       friendship are equal; User cannot be
	 *                                       friend of himself
	 */
	private static void addFriend(Scanner in, FakebookSystem fbs)
			throws UserDoNotExistsException, AlreadyFriendsException, UsersCannotBeTheSameException {

		String user1 = in.nextLine().trim();
		String user2 = in.nextLine().trim();

		try {
			fbs.makeFriendship(user1, user2);
			System.out.printf(FRIEND_ADDED, user1, user2);

		} catch (UserDoNotExistsException exception) {
			System.out.println(exception.getMessage());

		} catch (UsersCannotBeTheSameException exception) {
			System.out.println(exception.getMessage());

		} catch (AlreadyFriendsException exception) {
			System.out.println(exception.getMessage());

		}

	}

	/**
	 * This method scans a id of a User, to list all friends of that User
	 * 
	 * @param in  - Scanner
	 * @param fbs - FakeBookSystem
	 */
	private static void listFriends(Scanner in, FakebookSystem fbs) {

		String name = in.nextLine().trim();

		try {
			Iterator<User> it = fbs.getFriendsIterator(name);
			while (it.hasNext()) {
				User user = it.next();
				if (it.hasNext()) {
					System.out.print(user.getId() + ", ");
				} else {
					System.out.print(user.getId() + ".\n");
				}
			}

		} catch (UserDoNotExistsException exception) {
			System.out.println(exception.getMessage());
		} catch (UserHasNoFriendsException exception) {
			System.out.println(exception.getMessage());
		}

	}

	/**
	 * 
	 * @param in  - Scanner
	 * @param fbs - FakebookSystem
	 * @throws UserDoNotExistsException       -if the user doesnt exists
	 * @throws NotAllowedForUserException     - if the user is not allowed to post
	 *                                        that type of post
	 * @throws WrongHashtagListException      - if there is difference between the
	 *                                        number scanned and the number of
	 *                                        hashtags scanned
	 * @throws InvalidHonestyForPostException - if the honesty of the post is
	 *                                        invalid
	 * 
	 *                                        This method will scan the id of the
	 *                                        user, the number of hashtags that will
	 *                                        be scanned, the honesty of the post
	 *                                        and the message. It will create a post
	 *                                        on the user post collection
	 */
	private static void post(Scanner in, FakebookSystem fbs) throws UserDoNotExistsException,
			NotAllowedForUserException, WrongHashtagListException, InvalidHonestyForPostException {

		String id = in.nextLine().trim();
		int toScan = in.nextInt();

		List<Topic> list = fbs.createNewTopicList(toScan);

		try {
			Main.scanTopicsList(in, fbs, toScan, list);
			String honesty = in.next();
			String message = in.nextLine().trim();
			fbs.post(id, message, honesty, list);
			System.out.printf(POSTED, id, honesty, fbs.getUserNumberOfFriends(id), fbs.getUserLastPostId(id));

		} catch (UserDoNotExistsException exception) {
			System.out.println(exception.getMessage());
		} catch (NotAllowedForUserException exception) {
			System.out.println(exception.getMessage());
		} catch (WrongHashtagListException exception) {
			System.out.println(exception.getMessage());
		}
	}

	/**
	 * 
	 * @param in     - Scanner
	 * @param fbs    - FakebookSystem
	 * @param toScan - number of the hashtags to be scanned
	 * @param list   -list of topics
	 * @throws WrongHashtagListException - if there is difference between the number
	 *                                   scanned and the number of hashtags scanned
	 * 
	 *                                   This method will scan the hashtags, and
	 *                                   will create a temporary list of topic
	 *                                   verifying of there is none repetead hashtag
	 *                                   scanned
	 */
	private static void scanTopicsList(Scanner in, FakebookSystem fbs, int toScan, List<Topic> list)
			throws WrongHashtagListException {

		int scanned = 0;
		while (scanned < toScan) {
			String hashtag = in.next().trim();
			scanned++;
			Topic topic = new TopicClass(hashtag);

			if (!fbs.topicListHasTopic(list, topic)) {
				list.add(topic);

			} else {
				while (scanned < toScan) {
					in.next();
					scanned++;
				}
				in.next();
				in.nextLine();
				throw new WrongHashtagListException();
			}
		}
	}

	/**
	 * 
	 * @param in  -Scanner
	 * @param fbs - FakebookSystem
	 * @throws UserDoNotExistsException   - if the user doesnt exist
	 * @throws UserPostListEmptyException - if the user doesnt have any post
	 * 
	 *                                    This method will print the posts of the
	 *                                    user
	 */
	private static void listPosts(Scanner in, FakebookSystem fbs)
			throws UserDoNotExistsException, UserPostListEmptyException {
		String user = in.nextLine().trim();

		try {
			Iterator<Post> it = fbs.getUserPostsIterator(user);

			System.out.printf(LIST_POSTS, user);
			while (it.hasNext()) {
				Post post = it.next();
				System.out.println(post.getId() + ". [" + post.getVeracity() + "] " + post.getMessage() + " ["
						+ post.getNumberOfComments() + " comments]");
			}

		} catch (UserDoNotExistsException exception) {
			System.out.println(exception.getMessage());
		} catch (UserPostListEmptyException exception) {
			System.out.println(exception.getMessage());
		}

	}

	/**
	 * 
	 * @param in  - Scanner
	 * @param fbs - FakebookSystem
	 * @throws UserDoNotExistsException      - if the user doesnt exists
	 * @throws CantCommentPostException      - if the user cant comment the post
	 * @throws InvalidCommentStanceException - if the stance of the comment is
	 *                                       invalid
	 * @throws NoAccessToPostException       - if the user doesnt have access to the
	 *                                       post
	 * @throws UserDontHaveThatPost          - if the owner of the post dont have
	 *                                       that post
	 * 
	 *                                       This method will scan the commenter id
	 *                                       , the owner id , the post id , the
	 *                                       stance of the comment and the text of
	 *                                       the comment, and will create a comment
	 *                                       in the post
	 */
	private static void comment(Scanner in, FakebookSystem fbs) throws UserDoNotExistsException,
			CantCommentPostException, InvalidCommentStanceException, NoAccessToPostException, UserDontHaveThatPost {
		String commenter = in.nextLine().trim();
		String owner = in.nextLine().trim();
		int postId = in.nextInt();
		String positivity = in.next();
		String comment = in.nextLine().trim();

		try {
			fbs.comment(commenter, owner, postId, positivity, comment);
			System.out.println(COMMENT_ADDED);

		} catch (UserDoNotExistsException exception) {
			System.out.println(exception.getMessage());
		} catch (UserDontHaveThatPost exception) {
			System.out.println(exception.getMessage());
		} catch (NoAccessToPostException exception) {
			System.out.println(exception.getMessage());
		} catch (CantCommentPostException exception) {
			System.out.println(exception.getMessage());
		} catch (InvalidCommentStanceException exception) {
			System.out.println(exception.getMessage());
		}
	}

	/**
	 * 
	 * @param in  - Scanner
	 * @param fbs -FakebookSystem
	 * @throws UserDontHaveThatPost - if the user scanner does not have the post scanned
	 * @throws PostCommentsListEmptyException - if the post scanned has no comments
	 * 
	 *                                        This method will scan the user and the
	 *                                        post id and then will print the post, the
	 *                                        user id, the post veracity and the
	 *                                        text of the post. After that it will posts all the comments made in that post
	 */
	private static void readPost(Scanner in, FakebookSystem fbs)
			throws UserDontHaveThatPost, PostCommentsListEmptyException {

		String user = in.nextLine().trim();
		int postId = in.nextInt();

		try {
			Post post = fbs.getUserPost(user, postId);
			System.out.println("[" + user + " " + post.getVeracity() + "] " + post.getMessage());

			Iterator<Comment> it = fbs.getPostCommentsIterator(user, postId);
			Main.printPostComments(in, fbs, user, postId, it);

		} catch (UserDoNotExistsException exception) {
			System.out.println(exception.getMessage());
		} catch (PostCommentsListEmptyException exception) {
			System.out.println(exception.getMessage());
		} catch (UserDontHaveThatPost exception) {
			System.out.println(exception.getMessage());
		}

	}

	/**
	 * 
	 * @param in     - Scanner
	 * @param fbs    - FakebookSystem
	 * @param user   - this id of the user
	 * @param postId - the id of the Post
	 * @param it     - the iterator of the user
	 * @throws UserDoNotExistsException       - if the user does not exist
	 * @throws UserDontHaveThatPost           - if the post does not exist in the User
	 *                                        post collection
	 * @throws PostCommentsListEmptyException - if the Post does not have comments
	 * 
	 *                                        This method will print the comments of
	 *                                        a post
	 */
	private static void printPostComments(Scanner in, FakebookSystem fbs, String user, int postId, Iterator<Comment> it)
			throws UserDoNotExistsException, UserDontHaveThatPost, PostCommentsListEmptyException {

		if (fbs.isPostCommentsListEmpty(user, postId)) {
			throw new PostCommentsListEmptyException();

		} else {
			while (it.hasNext()) {
				Comment comment = it.next();
				System.out.println("[" + comment.getAuthorName() + " " + comment.getStance() + "] " + comment.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param in  -Scanner
	 * @param fbs - FakebookSystem
	 * @throws UserDoNotExistsException   - if the user does not exist
	 * @throws UserHasNoCommentsException - if the user never comment
	 * 
	 *                                    This method will print the comments of the
	 *                                    user of a certain topic
	 */
	private static void commentsByUser(Scanner in, FakebookSystem fbs)
			throws UserDoNotExistsException, UserHasNoCommentsException {

		String id = in.nextLine().trim();
		String hashtag = in.next().trim();
		try {
			Iterator<Comment> it = fbs.getUserCommentsIterator(id);
			if (fbs.userHasCommentsOnTopic(id, hashtag)) {
				while (it.hasNext()) {
					Comment comment = it.next();
					Post p = comment.getPost();
					if (comment.hasTopic(hashtag)) {
						System.out.println("[" + p.getAuthorName() + " " + p.getVeracity() + " " + p.getId() + " "
								+ comment.getStance() + "] " + comment.getMessage());
					}
				}
			} else
				throw new UserHasNoCommentsException();

		} catch (UserDoNotExistsException exception) {
			System.out.println(exception.getMessage());
		} catch (UserHasNoCommentsException exception) {
			System.out.println(exception.getMessage());
		}
	}

	/**
	 * 
	 * @param in  - Scanner
	 * @param fbs -FakebookSystem
	 * @throws NonexistantFanaticismException- if the fanaticism does not exist
	 * @throws NonexistantTopicException       - if the topic does not exist
	 * 
	 *                                         This method will print the fanatics
	 *                                         of a certain topic
	 */
	private static void topicFanatics(Scanner in, FakebookSystem fbs)
			throws NonexistantFanaticismException, NonexistantTopicException {
		String topic = in.next().trim();
		try {
			Iterator<User> it = fbs.getTopicFanaticsIterator(topic);

			while (it.hasNext()) {
				User u = it.next();
				if (it.hasNext()) {
					System.out.print(u.getId() + ", ");
				} else
					System.out.println(u.getId() + ".");
			}
		} catch (NonexistantFanaticismException exception) {
			System.out.println(exception.getMessage());
		}

	}

	/**
	 * 
	 * @param in  - Scanner
	 * @param fbs -FakebookSystem
	 * @throws WrongNumberToListPostsException - if the the number scanned is 0 or
	 *                                         inferior
	 * @throws NonexistantTopicException       - if the Topic doesnt exist
	 * 
	 *                                         This method will scan the hashtag of
	 *                                         the topic and the number of Posts to
	 *                                         iterate
	 */
	private static void topicPosts(Scanner in, FakebookSystem fbs)
			throws WrongNumberToListPostsException, NonexistantTopicException {
		String topic = in.next().trim();
		int number = in.nextInt();

		try {
			if (number >= 1) {
				printTopicPosts(fbs, topic, number);
			} else
				throw new WrongNumberToListPostsException();
		} catch (WrongNumberToListPostsException exception) {
			System.out.println(exception.getMessage());
		} catch (NonexistantTopicException exception) {
			System.out.println(exception.getMessage());
		}
	}

	/**
	 * 
	 * @param fbs    - FakebookSystem
	 * @param topic  - the Topic that we want to iterate it Posts
	 * @param number - number of Posts to iterate
	 * 
	 *               This method will print the Posts of a Topic
	 */
	private static void printTopicPosts(FakebookSystem fbs, String topic, int number) {

		if (number > 10) {
			number = 10;
		}
		Iterator<Post> it = fbs.getTopicPostsIterator(topic);
		int counter = 0;
		while (it.hasNext() && counter < number) {
			Post post = it.next();
			System.out.println(post.getAuthorName() + " " + post.getId() + " " + post.getNumberOfComments() + ": "
					+ post.getMessage());
			counter++;
		}
	}

	/**
	 * 
	 * @param fbs - FakebookSystem This method will print the post with more
	 *            comments
	 */
	private static void popularPost(FakebookSystem fbs) {
		try {
			Post post = fbs.getPopularPost();

			System.out.println(post.getAuthorName() + " " + post.getId() + " " + post.getNumberOfComments() + ": "
					+ post.getMessage());

		} catch (NoPostsOrCommentsException exception) {
			System.out.println(exception.getMessage());
		}
	}

	/**
	 * 
	 * @param fbs - FakebookSystem
	 * @throws ZeroPostsOnSystemException - if there is no posts on the System
	 * @throws UsersListEmptyException    - this exception is not used on this
	 *                                    method
	 */
	private static void topPoster(FakebookSystem fbs) throws ZeroPostsOnSystemException, UsersListEmptyException {
		try {
			User user = fbs.getTopPoster();
			System.out.println(user.getId() + " " + user.getNumberOfPosts() + " " + user.getNumberOfComments() + ".");

		} catch (ZeroPostsOnSystemException exception) {
			System.out.println(exception.getMessage());
		}
	}

	/**
	 * 
	 * @param fbs - FakebookSystem
	 * @throws ResponsiveException     - if there is no Posts or Comments on
	 *                                 Fakebook
	 * @throws UsersListEmptyException -this exception is not used on this method;
	 * 
	 *                                 This method will print the user with more
	 *                                 percentage of the Post with at least one
	 *                                 comment by the total of Posts that he has
	 *                                 access
	 */
	private static void responsive(FakebookSystem fbs) throws ResponsiveException, UsersListEmptyException {

		try {
			User user = fbs.getResponsive();

			System.out.println(
					user.getId() + " " + user.getNumberOfPostsCommented() + " " + user.totalPostsUserHasAccess() + ".");

		} catch (ResponsiveException exception) {
			System.out.println(exception.getMessage());
		}

	}

	/**
	 * 
	 * @param fbs - FakebookSystem
	 * @throws NoLiesException         - if there is no lies on FakeBook
	 * @throws UsersListEmptyException - this exception is not used on this method
	 * 
	 *                                 This method will print the user with more
	 *                                 lies
	 */
	private static void shameless(FakebookSystem fbs) throws NoLiesException, UsersListEmptyException {

		try {

			User user = fbs.getShameless();
			System.out.println(user.getId() + " " + user.getNumberOfLies() + ".");

		} catch (NoLiesException exception) {
			System.out.println(exception.getMessage());
		}
	}

	/**
	 * This method will print the possible commands, and what they do, of this
	 * program
	 */
	private static void help() {
		System.out.println(REGISTER_HELP);
		System.out.println(USERS_HELP);
		System.out.println(ADDFRIEND_HELP);
		System.out.println(FRIENDS_HELP);
		System.out.println(POST_HELP);
		System.out.println(USERPOSTS_HELP);
		System.out.println(COMMENT_HELP);
		System.out.println(READPOST_HELP);
		System.out.println(COMMENTSBYUSER_HELP);
		System.out.println(TOPICFANATICS_HELP);
		System.out.println(TOPICPOSTS_HELP);
		System.out.println(POPULARPOST_HELP);
		System.out.println(TOPPOSTER_HELP);
		System.out.println(RESPONSIVE_HELP);
		System.out.println(SHAMELESS_HELP);
		System.out.println(HELP_HELP);
		System.out.println(EXIT_HELP);
	}

}
