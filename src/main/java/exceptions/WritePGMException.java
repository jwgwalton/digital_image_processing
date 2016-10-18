package main.java.exceptions;

public class WritePGMException extends Exception {

	/**
	 *  Exception to be thrown when can't Write to PGM file
	 */
	private static final long serialVersionUID = 5257296650094051411L;

	public WritePGMException() {
	}

	public WritePGMException(String message) {
		super(message);
	}
}
