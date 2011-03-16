package comparaison.exceptions;

/**
 * @author Samuel Maistre
 *
 */
@SuppressWarnings("serial")
public class JaroException extends Exception {

	/**
	 * 
	 */
	public JaroException() {
	}

	/**
	 * @param message
	 */
	public JaroException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public JaroException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JaroException(String message, Throwable cause) {
		super(message, cause);
	}

}
