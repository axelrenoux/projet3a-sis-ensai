package bdd.exceptions;


/**
 * @author id2954
 *
 */
@SuppressWarnings("serial")
public class CanalException extends Exception {

	/**
	 * 
	 */
	public CanalException() {
	}

	/**
	 * @param message
	 */
	public CanalException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public CanalException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CanalException(String message, Throwable cause) {
		super(message, cause);
	}

}
