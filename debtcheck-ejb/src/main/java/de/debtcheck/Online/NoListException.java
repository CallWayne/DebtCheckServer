package de.debtcheck.Online;

public class NoListException extends DebtCheckException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int CODE = 50;

	public NoListException(String message) {
		super(CODE, message);
	}

}