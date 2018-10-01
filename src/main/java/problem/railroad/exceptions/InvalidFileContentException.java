package problem.railroad.exceptions;

public class InvalidFileContentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFileContentException(String errorMessage) {
		super(errorMessage);
	}

}
