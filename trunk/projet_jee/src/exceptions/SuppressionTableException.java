/**
 * 
 */
package exceptions;

/**
 * @author id2954
 *
 */
@SuppressWarnings("serial")
public class SuppressionTableException extends Exception {

	/**
	 * 
	 */
	public SuppressionTableException() {
	}

	/**
	 * @param message
	 */
	public SuppressionTableException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SuppressionTableException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SuppressionTableException(String message, Throwable cause) {
		super(message, cause);
	}

}
