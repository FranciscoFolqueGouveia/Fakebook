package user;

import java.util.Iterator;
import java.util.List;
import fanaticism.Fanaticism;

public interface Fanatic {
	/**
	 * 
	 * @return the Iterator of Fanaticisms of the Fanantic
	 */
	Iterator<Fanaticism> getFanaticismIterator();

	/**
	 * This method sets a list of Fanaticisms in the Fanatic 
	 * 
	 * @param list of Fanaticism
	 */
	void setFanaticismList(List<Fanaticism> list);

}
