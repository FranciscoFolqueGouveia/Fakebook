package fanaticism;

import topics.Topic;

public interface Fanaticism {
	
	
/**
 * 
 * @return the Topic of the Fanaticism 
 */
	public Topic getTopic();

	
	/**
	 * 
	 * @return the tendency of the Fanaticism
	 *  <code>true</code> if the Fanatic loves the Topic
	 *  <code>false</code> if the Fanatic hates The Topic
	 */
	public boolean getTendency();

}
