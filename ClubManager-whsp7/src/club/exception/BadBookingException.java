package club.exception;

public class BadBookingException extends Exception {
	private static final long serialVersionUID = 1L;

	public BadBookingException() {
	}

	public BadBookingException(String msg) {
		super(msg);
	}
}
