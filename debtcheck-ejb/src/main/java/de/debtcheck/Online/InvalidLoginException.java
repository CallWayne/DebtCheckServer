package de.debtcheck.Online;

public class InvalidLoginException extends DebtCheckException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int CODE = 20;

	public InvalidLoginException(String message) {
		super(CODE, message);
	}

}