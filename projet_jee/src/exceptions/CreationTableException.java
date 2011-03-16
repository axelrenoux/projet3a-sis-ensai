/**
 * 
 */
package exceptions;

/**
 * @author id2954
 *
 */
@SuppressWarnings("serial")
public class CreationTableException extends Exception {

	/**
	 * 
	 */
	public CreationTableException() {
	}

	/**
	 * @param arg0
	 */
	public CreationTableException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public CreationTableException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public CreationTableException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
