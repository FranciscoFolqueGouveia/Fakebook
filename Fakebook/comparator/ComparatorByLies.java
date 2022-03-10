package comparator;

import java.util.Comparator;

import user.*;

public class ComparatorByLies implements Comparator<User> {

	@Override
	public int compare(User user1, User user2) {
		int result = 1;

		if (user1.getNumberOfLies() > user2.getNumberOfLies()) {
			result = 1;
		} else if (user1.getNumberOfLies() < user2.getNumberOfLies()) {
			result = -1;
		}
		else if (user1.getNumberOfLies() == user2.getNumberOfLies()) {
			if (user1.postsPlusComments() > user2.postsPlusComments()) {
				result = -1;
			} else if (user1.postsPlusComments() < user2.postsPlusComments()) {
				result = 1;
			} else {
				result = 1;
			}
		}
		return result;
	}
}
