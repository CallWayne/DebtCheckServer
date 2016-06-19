package de.debtcheck.Online;

/**
 * Exceptionklasse für fehlerhaftes registrieren
 * 
 * @author Edgar Seibel
 * @author Josua Suren
 *
 */
public class InvalidRegisterException extends DebtCheckException {
	
	/**
	 * Attribute
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int CODE = 10;

	/**
	 * Konstruktor
	 * 
	 * @param message Nachricht der Exception
	 */
	public InvalidRegisterException(String message) {
		super(CODE, message);
	}

}