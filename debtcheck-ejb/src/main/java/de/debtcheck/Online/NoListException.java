package de.debtcheck.Online;

/**
 * Exceptionklasse
 * 
 * @author Edgar Seibel
 * @author Josua Suren
 *
 */
public class NoListException extends DebtCheckException {
	
	/**
	 * Attribute
	 */
	private static final long serialVersionUID = 1L;
	
	private static final int CODE = 50;

	/**
	 * Konstruktor
	 * 
	 * @param message Nachricht der Exception
	 */
	public NoListException(String message) {
		super(CODE, message);
	}

}