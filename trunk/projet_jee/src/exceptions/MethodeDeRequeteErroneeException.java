package exceptions;
/**
 * @deprecated
 */
public class MethodeDeRequeteErroneeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MethodeDeRequeteErroneeException() {super();
	}

	/**
	 * @param arg0
	 */
	public MethodeDeRequeteErroneeException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public MethodeDeRequeteErroneeException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public MethodeDeRequeteErroneeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
