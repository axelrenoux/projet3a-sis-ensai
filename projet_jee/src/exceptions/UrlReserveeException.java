package exceptions;

public class UrlReserveeException extends Exception {
	/**
	 * 
	 */
	public UrlReserveeException() {
	}

	/**
	 * @param message
	 */
	public UrlReserveeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UrlReserveeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UrlReserveeException(String message, Throwable cause) {
		super(message, cause);
	}
}
