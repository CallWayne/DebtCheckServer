package de.debtcheck.Online;

/**
 * Exceptionklasse
 * 
 * @author Josua Suren
 * @author Edgar Seibel
 *
 */
public class DebtException extends DebtCheckException {

/**
 * Attribute
 */
	private static final long serialVersionUID = 8759021636475023682L;
	private static final int CODE = 30;
	
/**
 * Konstruktor
 * 
 * @param message Nachricht der Exception
 */
	public DebtException(String message) {
		super(CODE, message);
	}

}