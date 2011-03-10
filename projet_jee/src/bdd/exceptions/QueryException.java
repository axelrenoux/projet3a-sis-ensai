/**
 * 
 */
package bdd.exceptions;

/**
 * @author id2954
 *
 */
@SuppressWarnings("serial")
public class QueryException extends Exception {

	/**
	 * 
	 */
	public QueryException() {
	}

	/**
	 * @param message
	 */
	public QueryException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public QueryException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public QueryException(String message, Throwable cause) {
		super(message, cause);
	}

}
