package topics;

import java.util.Iterator;

import post.Post;
import user.User;

public interface Topic {

	/**
	 * 
	 * @return the Post Iterator of the Topic
	 */
		Iterator<Post> getPostsIterator();
		
	/**
	 * 
	 * @return the User (of type Fanatic) Iterator of the Topic
	 */
		Iterator<User> getFanaticIterator();
		
	/**
	 * 
	 * @return the hashtag of the Topic
	 */
		String getHashtag();
		
	/**
	 * 
	 * @param t - Topic that it will be compared with this Topic
	 * @return <code>true</code> if the two Topics have the same hashtag
	 *         <code>false</code> if the opposites happens
	 */
		boolean hasTheSameHashtag(Topic t);
/**
 * 
 * @param id- id of the User(type Fanatic) that will be registed
 * @param fanatic - the User that will be registed
 * This method will add the User with that <code>id</code> to the Collection of Fanatics of the Topic
 */
	void registFanatic(String id, User fanatic);
	
/**
 * 
 * @param post - Post that will be add to the Collection of Posts of the Topic
 * This method will add the Post to the Collection of Post of the Topic
 */
	void registPost(Post post);

}
