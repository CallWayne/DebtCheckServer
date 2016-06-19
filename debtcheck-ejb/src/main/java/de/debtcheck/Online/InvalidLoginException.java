package de.debtcheck.Online;

/**
 * Exceptionklasse für fehlerhaften login
 * 
 * @author Edgar Seibel
 * @author Josua Suren
 *
 */
public class InvalidLoginException extends DebtCheckException {
	
	/**
	 * Attribute
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int CODE = 20;

	/**
	 * Konstruktor
	 * 
	 * @param message Nachricht der Exception
	 */
	public InvalidLoginException(String message) {
		super(CODE, message);
	}

}