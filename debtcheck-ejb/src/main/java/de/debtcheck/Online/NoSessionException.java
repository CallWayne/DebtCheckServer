package de.debtcheck.Online;

/**
 * Exceptionklasse für fehlende Session
 * 
 * @author Edgar Seibel
 * @author Josua Suren
 *
 */
public class NoSessionException extends DebtCheckException {

/**
 * Attribute
 */
	private static final long serialVersionUID = 8759021636475023682L;
	private static final int CODE = 5;
	
/**
 * Konstruktor
 * 
 * @param message Nachricht der Exception
 */
	public NoSessionException(String message) {
		super(CODE, message);
	}

}
