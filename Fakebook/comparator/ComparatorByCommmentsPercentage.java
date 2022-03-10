package comparator;

import java.util.Comparator;

import user.*;

public class ComparatorByCommmentsPercentage implements Comparator<User> {

	@Override
	public int compare(User user1, User user2) {

		int result = 0;

		if (user1.getPercentageOfCommentedPosts() > user2.getPercentageOfCommentedPosts()) {
			result = 1;
		}

		else if (user1.getPercentageOfCommentedPosts() < user2.getPercentageOfCommentedPosts()) {
			result = -1;
		} else {
			result = 1;

		}
		return result;
	}
}
