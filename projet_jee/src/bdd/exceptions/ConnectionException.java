/**
 * 
 */
package bdd.exceptions;

/**
 * @author id2954
 *
 */
@SuppressWarnings("serial")
public class ConnectionException extends Exception {

	/**
	 * 
	 */
	public ConnectionException() {
	}

	/**
	 * @param arg0
	 */
	public ConnectionException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public ConnectionException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ConnectionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
