/**
 * 
 */
package bdd.exceptions;

/**
 * @author id2954
 *
 */
@SuppressWarnings("serial")
public class DescriptionTableException extends Exception {

	/**
	 * 
	 */
	public DescriptionTableException() {
	}

	/**
	 * @param message
	 */
	public DescriptionTableException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DescriptionTableException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DescriptionTableException(String message, Throwable cause) {
		super(message, cause);
	}

}
