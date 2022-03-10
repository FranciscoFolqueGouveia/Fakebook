package comparator;

import java.util.Comparator;

import post.*;

public class PostsComparator implements Comparator<Post> {

	@Override
	public int compare(Post post1, Post post2) {

		int result = 0;

		if (post1.getNumberOfComments() > post2.getNumberOfComments()) {
			result = -1;

		} else if (post1.getNumberOfComments() < post2.getNumberOfComments()) {
			result = 1;

		} else if (post1.getAuthorName().compareTo(post2.getAuthorName()) > 0) {
			result = 1;

		} else if (post1.getAuthorName().compareTo(post2.getAuthorName()) < 0) {
			result = -1;

		} else if (post1.getId() > post2.getId()) {
			result = -1;
		} else {
			result = 1;
		}
		return result;
	}
}
