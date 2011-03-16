/**
 * 
 */
package exceptions;

/**
 * @author id2954
 *
 */
@SuppressWarnings("serial")
public class UpdateException extends Exception {

	/**
	 * 
	 */
	public UpdateException() {
	}

	/**
	 * @param message
	 */
	public UpdateException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UpdateException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UpdateException(String message, Throwable cause) {
		super(message, cause);
	}

}
