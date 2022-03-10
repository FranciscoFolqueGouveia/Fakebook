package comparator;

import java.util.Comparator;

import user.User;

public class ComparatorByNumberOfPosts implements Comparator<User> {

	@Override
	public int compare(User user1, User user2) {
		int result = -1;
		if (user1.getNumberOfPosts() > user2.getNumberOfPosts()) {
			result = 1;
		}

		else if (user1.getNumberOfPosts() == user2.getNumberOfPosts()) {
			if (user1.getNumberOfComments() == user2.getNumberOfComments()) {
				result = 1;
			} else
				result = -1;
		} else {
			result = -1;
		}
		return result;
	}
}