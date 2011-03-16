package comparaison.exceptions;

/**
 * @author Samuel Maistre
 *
 */
@SuppressWarnings("serial")
public class ChaineVideException extends Exception {

	/**
	 * 
	 */
	public ChaineVideException() {
	}

	/**
	 * @param arg0
	 */
	public ChaineVideException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public ChaineVideException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ChaineVideException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
