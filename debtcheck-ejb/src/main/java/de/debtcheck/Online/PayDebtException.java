package de.debtcheck.Online;

/**
 * Exceptionklasse für fehlerhaftes Bezahlen einer Schuld
 * 
 * @author Josua Suren
 * @author Edgar Seibel
 *
 */
public class PayDebtException extends DebtCheckException {
	
	/**
	 * Attribute
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int CODE = 40;

	/**
	 * Konstruktor
	 * 
	 * @param message Nachricht der Exception
	 */
	public PayDebtException(String message) {
		super(CODE, message);
	}

}