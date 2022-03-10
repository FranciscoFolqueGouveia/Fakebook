package exceptions;

public class UnknownKindException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8153894275179560619L;
	private static final String MESSAGE = "%s is an invalid user kind!";

	public UnknownKindException(String kind) {
		super(String.format(MESSAGE, kind));
	}

}
