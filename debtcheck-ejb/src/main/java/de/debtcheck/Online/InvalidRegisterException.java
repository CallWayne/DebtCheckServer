package de.debtcheck.Online;

public class InvalidRegisterException extends DebtCheckException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int CODE = 10;

	public InvalidRegisterException(String message) {
		super(CODE, message);
	}

}