package exceptions;

public class NonexistantTopicException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3261175845112786483L;
	private static final String MESSAGE = "Oh please, who would write about %s?";

	public NonexistantTopicException(String topic) {
		super(String.format(MESSAGE, topic));
	}

}
