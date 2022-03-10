package fanaticism;

import topics.Topic;

public class FanaticismClass implements Fanaticism {

	/**
	 * tendency - the tendency of the User(Fanatic) about a Topic
	 */
	private boolean tendency;
	
	/**
	 * topic - the Topic of the User(Fanatic)
	 */
	private Topic topic;
	
	/**
	 * Constructor of FanaticismClass
	 * 
	 * @param topic -Topic of the User(Fanatic)
	 * @param tendency - the tendency of the User(Fanatic) about the Topic
	 */
	public FanaticismClass(Topic topic, boolean tendency) {
	
		this.topic = topic;
		this.tendency = tendency;
	}
	@Override
	public Topic getTopic() {
		return this.topic;
	}
	@Override
	public boolean getTendency() {
		return this.tendency;
		
	}
}
