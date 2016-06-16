package de.debtcheck.Online;

public class PayDebtException extends DebtCheckException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int CODE = 40;

	public PayDebtException(String message) {
		super(CODE, message);
	}

}