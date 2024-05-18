/**
 * Class that extends Exception class and define a new exception type.
 */
public class EmptyListException extends Exception {
	/* Default constructor for EmptyListException */
	public EmptyListException() {
		super();
	}

	public EmptyListException(String message) {
		super(message);
	}

}
