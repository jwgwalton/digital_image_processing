package main.java.exceptions;

public class ReadPGMException extends Exception {

	/**
	 * Exception to be thrown when can't read to PGM File
	 */
	private static final long serialVersionUID = 1L;

	public ReadPGMException() {
	}

	public ReadPGMException(String message) {
		super(message);
	}
}
