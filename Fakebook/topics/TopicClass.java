package topics;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import comparator.PostsComparator;
import user.User;
import post.*;

public class TopicClass implements Topic {
	/**
	 * hashtag - the unique identifier of the Topic
	 */
	private String hashtag;

	/**
	 * fanatics - Collection of Users(subclass Fanatic) of the Topic
	 */
	private SortedMap<String, User> fanatics;

	/**
	 * posts - Collection of Posts of the Topic
	 */
	private Set<Post> posts;

	/**
	 * Constructor of TopicClass
	 * 
	 * @param hashtag - identifier of the Topic
	 */
	public TopicClass(String hashtag) {

		this.hashtag = hashtag;
		this.fanatics = new TreeMap<String, User>();
		this.posts = new HashSet<Post>();

	}

	@Override
	public Iterator<Post> getPostsIterator() {
		Set<Post> set = new TreeSet<Post>(new PostsComparator());
		set.addAll(posts);
		return set.iterator();
	}

	@Override
	public Iterator<User> getFanaticIterator() {
		Collection<User> collection = fanatics.values();
		return collection.iterator();
	}

	@Override
	public String getHashtag() {
		return this.hashtag;
	}

	@Override
	public boolean hasTheSameHashtag(Topic t) {
		return this.hashtag.equalsIgnoreCase(t.getHashtag());
	}

	@Override
	public void registFanatic(String id, User fanatic) {

		fanatics.put(id, fanatic);

	}

	@Override
	public void registPost(Post post) {
		posts.add(post);

	}

}
