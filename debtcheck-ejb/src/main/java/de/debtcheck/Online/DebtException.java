package de.debtcheck.Online;

public class DebtException extends DebtCheckException {


	private static final long serialVersionUID = 8759021636475023682L;
	private static final int CODE = 30;
	

	public DebtException(String message) {
		super(CODE, message);
	}

}