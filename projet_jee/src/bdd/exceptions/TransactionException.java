/**
 * 
 */
package bdd.exceptions;

/**
 * @author id2954
 *
 */
@SuppressWarnings("serial")
public class TransactionException extends Exception {

	/**
	 * 
	 */
	public TransactionException() {
	}

	/**
	 * @param arg0
	 */
	public TransactionException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public TransactionException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TransactionException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
