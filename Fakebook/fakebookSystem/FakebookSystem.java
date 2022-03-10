package fakebookSystem;

import java.util.Iterator;
import java.util.List;

import Comment.Comment;
import exceptions.*;
import fanaticism.Fanaticism;
import post.Post;
import topics.Topic;
import user.User;

public interface FakebookSystem {

	/**
	 * @param id - id of the User we want to return
	 * @return the User of the respective id
	 * @throws UserDoNotExistsException - if that id does not corresponds to a
	 *                                  existence User in the system
	 */
	User getUser(String id) throws UserDoNotExistsException;

	/**
	 * @param user   - User who owns the post
	 * @param postId - the id of that post in the User
	 * @return the respective Post owned by the respective User
	 * @throws UserDontHaveThatPost     - if the User does not have the Post in his
	 *                                  List
	 * @throws UserDoNotExistsException - if the User does not exist in the System
	 */
	Post getUserPost(String user, int postId) throws UserDontHaveThatPost, UserDoNotExistsException;

	/**
	 * This method creates a new ArrayList for Fanaticism objects
	 * 
	 * @param size of the ArrayList that this method creates
	 * @return the new ArrayList created
	 */
	List<Fanaticism> createNewFanaticismList(int size);

	/**
	 * This method creates a new ArrayList for Topic objects
	 * 
	 * @param size of the ArrayList that this method creates
	 * @return the new ArrayList created
	 */
	List<Topic> createNewTopicList(int size);

	/**
	 * @return the Iterator of the User collection of the System
	 * @throws UsersListEmptyException
	 */
	Iterator<User> getUsersIterator() throws UsersListEmptyException;

	/**
	 * @param user - id of the User who we want to access Post list
	 * @return the Iterator of the Post list of the User
	 * @throws UserDoNotExistsException   - if that id does not corresponds to a
	 *                                    existence User in the system
	 * @throws UserPostListEmptyException - if the Users post list has no posts
	 */
	Iterator<Post> getUserPostsIterator(String user) throws UserDoNotExistsException, UserPostListEmptyException;

	/**
	 * @param user   - User who owns the Post we want to access
	 * @param postId - id of the Post in the User posts list
	 * @return the Iterator of the Comments list of that Post
	 * @throws UserDoNotExistsException - if the owner of the Post does not exist in
	 *                                  the System
	 * @throws UserDontHaveThatPost     - if the User does not has the Post in his
	 *                                  posts list
	 */
	Iterator<Comment> getPostCommentsIterator(String user, int postId)
			throws UserDoNotExistsException, UserDontHaveThatPost;

	/**
	 * @param id - id of the User we want to access
	 * @return the Iterator of the friends collection of that User
	 * @throws UserDoNotExistsException  - if that id does not corresponds to a
	 *                                   existence User in the system
	 * @throws UserHasNoFriendsException - if the user dont have any friend
	 */
	Iterator<User> getFriendsIterator(String id) throws UserDoNotExistsException, UserHasNoFriendsException;

	/**
	 * 
	 * @param topic - topic that we want to iterate it fanatics
	 * @return the Iterator of the fanatics that have that topic as fanaticism
	 * @throws NonexistantFanaticismException - if the <code>topic</code> doesnt
	 *                                        exist
	 */
	Iterator<User> getTopicFanaticsIterator(String topic) throws NonexistantFanaticismException;

	/**
	 * 
	 * @param id - the id of the User that we want to iterate his comments
	 * @return the Iterator of the comments of the User that have that
	 *         <code>id</code>
	 * @throws UserDoNotExistsException - if the User with that <code>id</code>
	 *                                  doesnt exist
	 */
	Iterator<Comment> getUserCommentsIterator(String id) throws UserDoNotExistsException;

	/**
	 * 
	 * @param hashtag - the hashtag of the Topic that we want to iterate it Posts
	 * @return the Iterator of Posts of the Topic that has that <code>hashtag</code>
	 * @throws NonexistantTopicException - if the Topic with that
	 *                                   <code>hashtag</code>
	 */
	Iterator<Post> getTopicPostsIterator(String hashtag) throws NonexistantTopicException;

	/**
	 * 
	 * @return the top poster (the User with more Posts)
	 * @throws ZeroPostsOnSystemException - if the Fakebook has 0 Posts on it
	 * @throws UsersListEmptyException    - this exception is not used in this
	 *                                    method
	 */
	User getTopPoster() throws ZeroPostsOnSystemException, UsersListEmptyException;

	/**
	 * 
	 * @return the top liar (the User with more lies)
	 * @throws NoLiesException         - if there is 0 lies on Fakebook
	 * @throws UsersListEmptyException - this exception is not used in this method
	 */
	User getShameless() throws NoLiesException, UsersListEmptyException;

	/**
	 * 
	 * @return the User with more percentage of comments by the Posts he has access
	 * @throws UsersListEmptyException - this exception is not used in this method
	 * @throws ResponsiveException     - if there are no posts or comments in the
	 *                                 Fakebook
	 */
	User getResponsive() throws UsersListEmptyException, ResponsiveException;

	/**
	 * @param topic - string that represent a hashtag
	 * @return the Topic object associated to that string
	 */
	Topic getTopic(String topic);

	/**
	 * @return the Post in the FakeBookSystem with more comments on it
	 * @throws NoPostsOrCommentsException - if there are no posts in the system or
	 *                                    no comments in any Post
	 */
	Post getPopularPost() throws NoPostsOrCommentsException;

	/**
	 * @param kind - used to create a new User
	 * @return <code>true</code> if the kind equals "fanatic" 
	 *         <code>false</code> if the opposite happens
	 */
	boolean isKindFanatic(String kind);

	/**
	 * @param id of the User
	 * @return <code>true</code> if that User is of kind Fanatic
	 *         <code>false</code> if the opposite happens
	 * @throws UserDoNotExistsException - if that id does not corresponds to a
	 *                                  existence User in the system
	 */
	boolean isFanatic(String id) throws UserDoNotExistsException;

	/**
	 * @param id we want to check User existence
	 * @return <code>true</code> if exists a User with that id 
	 *        <code>false</code> if the opposite happens
	 */
	boolean hasUser(String id);

	/**
	 * This method checks if a specific Topic exists in some Fanaticism of the
	 * fanaticisms list
	 * 
	 * @param list  of fanaticisms
	 * @param topic - Topic we want to check existence in the list
	 * @return <code>true</code> if some Fanaticism of the list has the specific Topic 
	 *         <code>false</code> if the opposite happens
	 */
	boolean fanaticismListHasTopic(List<Fanaticism> list, Topic topic);

	/**
	 * This method checks if a specific Topic exists in a topics list
	 * 
	 * @param list  of topics
	 * @param topic - Topic we want to check existence in the list
	 * @return <code>true</code> if Topic exists in that list 
	 * <code>false</code> if the opposite happens
	 */
	boolean topicListHasTopic(List<Topic> list, Topic topic);

	/**
	 * @param value - String that can equals "loves" or "hates";
	 * @return <code>true</code> if value equals "loves" 
	 * <code>false</code> if the opposite happens
	 * @throws InvalidTendencyForFanaticismException - if the value received does
	 *                                               not equals neither "loves" or
	 *                                               "hates"
	 */
	boolean doesItLoves(String value) throws InvalidTendencyForFanaticismException;

	/**
	 * @param value - String that can equals "honest" or "fake";
	 * @return - <code>true</code> if value equals "honest"
	 *           <code>false</code> if the opposite happens
	 * @throws InvalidHonestyForPostException - if the value received does not
	 *                                        equals neither "honest" or "fake"
	 */
	boolean isHonest(String value) throws InvalidHonestyForPostException;

	/**
	 * @param value - String that can equals "positive" or "negative";
	 * @return - <code>true</code> if value equals "positive"
	 *           <code>false</code> if the opposites happens
	 * @throws InvalidCommentStanceException - if the value received does not equals
	 *                                       neither "positive" or "negative"
	 */
	boolean isPositive(String value) throws InvalidCommentStanceException;

	/**
	 * Checks if the list of comments of a Post is empty or not;
	 * 
	 * @param user   - id of the owner of the Post
	 * @param postId - id of the Post owned by the User with that id
	 * @return <code>true</code> if the comments collection of the Post is Empty
	 *         <code>false</code> if the opposite happens
	 * @throws UserDoNotExistsException - if that id does not corresponds to a
	 *                                  existence User in the system
	 * @throws UserDontHaveThatPost     - if the User does not has the Post in his
	 *                                  posts list
	 */

	boolean isPostCommentsListEmpty(String user, int postId) throws UserDoNotExistsException, UserDontHaveThatPost;

	/**
	 * @param user    we want to check if has comments about some topic
	 * @param hashtag - hashtag associated to a topic
	 * @return <code>true</code> if the user has at least one comment about that topic
	 *          <code>false</code> if the opposite happens
	 * @throws UserDoNotExistsException
	 */
	boolean userHasCommentsOnTopic(String user, String hashtag) throws UserDoNotExistsException;

	/**
	 * @param user   we want to check if has access
	 * @param owner  of the Post
	 * @param postId - id of the Post
	 * @return <code>true</code> if user has access to that Post
	 *         <code>false</code> if the opposite happens
	 * @throws UserDoNotExistsException - if one of the users (user or owner) does
	 *                                  not exist in the System
	 * @throws UserDontHaveThatPost     - if the owner is not actually the owner of
	 *                                  the Post
	 */
	boolean hasUserAccessToPost(String user, String owner, int postId)
			throws UserDoNotExistsException, UserDontHaveThatPost;

	/**
	 * @param id of a User we want to access
	 * @return the number of friends of that User
	 * @throws UserDoNotExistsException - if that id does not corresponds to a
	 *                                  existence User in the system
	 */
	int getUserNumberOfFriends(String id) throws UserDoNotExistsException;

	/**
	 * This method returns the id of the last post inserted in a User posts list
	 * 
	 * @param id of a User we want to access
	 * @return the id of the last post inserted in the User posts list
	 * @throws UserDoNotExistsException - if that id does not corresponds to a
	 *                                  existence User in the system
	 */
	int getUserLastPostId(String id) throws UserDoNotExistsException;

	/**
	 * This method regists a new User in the system users collection
	 * 
	 * @param kind - type of user to create
	 * @param id   of the user to create
	 * @throws UnknownKindException       - if the kind for user inserted is unknown
	 *                                    for the system
	 * @throws UserAlreadyExistsException - if that id does already correspond to an
	 *                                    existent User
	 */

	void registerUser(String kind, String id) throws UnknownKindException, UserAlreadyExistsException;

	/**
	 * This method sets a Fanaticism list in a Fanatic Fanaticism list
	 * 
	 * @param id   of the fanatic User to access
	 * @param list - list of Fanaticism
	 * @throws UserDoNotExistsException - if the <code>id</code> does not correspond to a
	 *                                  existence user in the system
	 */
	void setFanaticFanaticismList(String id, List<Fanaticism> list) throws UserDoNotExistsException;

	/**
	 * This method establish a friendship between two users given
	 * 
	 * @param user1 - id of the first user
	 * @param user2 - id of the second user
	 * @throws UserDoNotExistsException      - if ate least one of the <code>id</code> of the users given
	 *                                       does not correspond to a existence user
	 *                                       in the system;
	 * @throws AlreadyFriendsException       - if the two users are already friends
	 * @throws UsersCannotBeTheSameException - if the two given users are actually
	 *                                       the same
	 */
	void makeFriendship(String user1, String user2)
			throws UserDoNotExistsException, AlreadyFriendsException, UsersCannotBeTheSameException;

	/**
	 * This method creates a post by a User and saves it in the User posts list
	 * 
	 * @param user       - id of the user who creates the Post
	 * @param message    of the Post to create
	 * @param honesty    of the Post to create (save true if equals "honest", false
	 *                   if equals "fake", else throws exception );
	 * @param topicsList - list of topics of the Post to create
	 * @throws NotAllowedForUserException     - if the Post variables are not
	 *                                        allowed for the user kind used
	 * @throws UserDoNotExistsException       - if the id does not correspond to a
	 *                                        existence user in the system
	 * @throws InvalidHonestyForPostException - if honesty neither equals "honest"
	 *                                        or "fake"
	 */

	void post(String user, String message, String honesty, List<Topic> topicsList)
			throws NotAllowedForUserException, UserDoNotExistsException, InvalidHonestyForPostException;

	/**
	 * This method creates a comment by a User to an existent Post (of any User
	 * existent)
	 * 
	 * @param id         of the user who will comment
	 * @param owner      - id of the User who owns the Post which will be commented
	 * @param postId     id of the Post
	 * @param positivity of the Comment (save true if positivity equals "positive",
	 *                   false if equals "negative", else throws exception)
	 * @param comment    - message associated to the comment
	 * @throws UserDoNotExistsException      - if the id of the users given
	 *                                       does not correspond to a existence user
	 *                                       in the system
	 * @throws UserDontHaveThatPost          - if the owner User does not have that
	 *                                       Post in his posts list
	 * @throws CantCommentPostException      - if the Comment variables are not
	 *                                       allowed for the kind of the user who
	 *                                       want to comment
	 * @throws InvalidCommentStanceException - if positivity neither equals
	 *                                       "positive" or "negative"
	 * @throws NoAccessToPostException
	 */
	void comment(String id, String owner, int postId, String positivity, String comment)
			throws UserDoNotExistsException, UserDontHaveThatPost, CantCommentPostException,
			InvalidCommentStanceException, NoAccessToPostException;

	/**
	 * When Fanatic User is registered, the topics that he is fanatic about (saved
	 * in the Fanaticisms list) will regist that fanatic User in their fanatics
	 * list; This method will also regist the topics that the User is fanatic about
	 * in the System topics Collection, if that collection do not contain them yet
	 * 
	 * @param id   - of the Fanatic
	 * @throws UserDoNotExistsException - if the <code>id</code> does not correspond to a
	 *                                  existence user in the system
	 */
	void registFanaticInHisTopics(String id) throws UserDoNotExistsException;

}
