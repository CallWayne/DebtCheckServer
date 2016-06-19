package de.debtcheck.Online;

/**
 * Exceptionklasse
 * 
 * @author Josua Suren
 * @author Edgar Seibel
 *
 */
public class DebtCheckException extends Exception {
	
	/**
	 * Attribute
	 */
	private static final long serialVersionUID = -1658425297634781761L;

	private int errorCode;
	
	/**
	 * Konstruktor
	 * 
	 * @param errorCode ErrorCode
	 * @param message Nachricht der Exception
	 */
	public DebtCheckException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * Methode zur Abfrage des ErrorCodes
	 * @return ErrorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * Methode zum Setzen des ErrorCodes
	 * @param errorCode ErrorCode
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}


