package user;

import java.util.Iterator;
import java.util.List;

import Comment.Comment;
import Comment.CommentClass;
import exceptions.CantCommentPostException;
import exceptions.InvalidCommentStanceException;
import exceptions.NotAllowedForUserException;
import fanaticism.Fanaticism;
import post.Post;
import topics.Topic;

public class FanaticClass extends AbstractUserClass implements Fanatic {

	/**
	 * type associated to this subClass of AbstractUserClass
	 */
	private static final String TYPE = "fanatic";
	/**
	 * Set of Strings that contain the hashtags (topic this User is fanatic about)
	 */
	private List<Fanaticism> fanaticisms;

	public FanaticClass(String id) {
		super(id, TYPE);

	}

	/**
	 * @param fanaticism of the Fanatic User
	 * @return the Topic of that Fanaticism
	 */
	private Topic getFanaticismTopic(Fanaticism fanaticism) {
		return fanaticism.getTopic();
	}

	@Override
	public Iterator<Fanaticism> getFanaticismIterator() {
		return this.fanaticisms.iterator();
	}

	/**
	 * @param fanaticism of the Fanatic User
	 * @return the tendency of the Fanaticism (true means User loves the Topic,
	 *         false means User hates the Topic)
	 */
	private boolean doesHeLovesIt(Fanaticism fanaticism) {
		return fanaticism.getTendency();
	}

	/**
	 * @param topics      - Iterator of Topics of the Post
	 * @param fanaticisms - Iterator of User Fanaticisms
	 * @param honesty     - honesty of the Post (true means hones, false means fake)
	 * @return true if the Topics of the Post are allowed. To be allowed, every
	 *         Topic of the Post must be in the Fanaticims of the User. And the user
	 *         can not post a fake message on a topic he loves or a honest post
	 *         on a Topic he hates;
	 */
	private boolean isTopicsAllowedForPost(Iterator<Topic> topics, Iterator<Fanaticism> fanaticisms, boolean honesty) {

		boolean result = true;
		boolean found = false;
		while (topics.hasNext() && result) {
			Topic topic1 = topics.next();

			while (fanaticisms.hasNext() && !found) {
				Fanaticism fanaticism = fanaticisms.next();
				Topic topic2 = getFanaticismTopic(fanaticism);

				if (topic1.hasTheSameHashtag(topic2)) {
					found = true;
					if (honesty && !doesHeLovesIt(fanaticism)) {
						result = false;
					} else if (!honesty && doesHeLovesIt(fanaticism)) {
						result = false;
					} else
						result = true;
				} else
					result = false;
			}
		}
		return result;
	}

	/**
	 * @param topics - iterator of Topics   
	 * @return true if the first Topic matched with a Fanaticism of the User is a Topic the User loves 
	 */
	private boolean isFananticismPositive(Iterator<Topic> topics) {
		boolean result = false;
		boolean found = false;

		Iterator<Fanaticism> fanaticisms = this.getFanaticismIterator();

		while (fanaticisms.hasNext() && !found) {
			Fanaticism fanaticism = fanaticisms.next();
			Topic topic1 = fanaticism.getTopic();

			while (topics.hasNext() && !found) {
				Topic topic2 = topics.next();

				if (topic1.hasTheSameHashtag(topic2)) {
					found = true;
					if (doesHeLovesIt(fanaticism)) {
						result = true;
					} else
						result = false;
				}
			}
		}
		return result;

	}

	@Override
	public void setFanaticismList(List<Fanaticism> list) {
		this.fanaticisms = list;

	}

	@Override
	public void post(Post post) throws NotAllowedForUserException {

		Iterator<Topic> it = post.getTopicsIterator();
		Iterator<Fanaticism> it2 = getFanaticismIterator();

		if (isTopicsAllowedForPost(it, it2, post.isHonest())) {

			addPost(post);
			giveMeAndFriendsAccessToPost(post);

			if (!post.isHonest()) {
				addLie();
			}

		} else
			throw new NotAllowedForUserException();

	}

	@Override
	public void comment(Post post, boolean positivity, String message)
			throws CantCommentPostException, InvalidCommentStanceException {

		Iterator<Topic> it = post.getTopicsIterator();

		if (post.isHonest() ^ positivity ^ isFananticismPositive(it)) {

			Comment comment = new CommentClass(this, positivity, message, post);
			post.addComment(comment);
			addComment(comment);

			if ((post.isHonest() && !positivity) || (!post.isHonest() && positivity)) {
				addLie();
			}

		} else {
			throw new InvalidCommentStanceException();
		}

	}

}
