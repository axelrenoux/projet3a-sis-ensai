/**
 * 
 */
package bdd.exceptions;

/**
 * @author id2954
 *
 */
@SuppressWarnings("serial")
public class ChargementException extends Exception {

	/**
	 * 
	 */
	public ChargementException() {
	}

	/**
	 * @param message
	 */
	public ChargementException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ChargementException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ChargementException(String message, Throwable cause) {
		super(message, cause);
	}

}
