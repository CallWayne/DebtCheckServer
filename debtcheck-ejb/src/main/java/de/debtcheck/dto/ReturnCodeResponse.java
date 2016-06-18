package de.debtcheck.dto;

/**
 * Klasse für den ReturnCode
 * 
 * @author Josua Suren
 * @author Edgar Seibel
 *
 */
public class ReturnCodeResponse extends DataTransferObject {

	/**
	 * Attribute
	 */
	private static final long serialVersionUID = 1L;

	private static final int CODE_OK = 0;
	
	private int returnCode;
	private String message;
	
	/**
	 * Konstruktor
	 */
	public ReturnCodeResponse() {
		this.returnCode = CODE_OK;
	}

	/**
	 * Methode zur Abfrage des ReturnCodes
	 * @return ReturnCode
	 */
	public int getReturnCode() {
		return returnCode;
	}

	/**
	 * Methode zum Setzen des ReturnCodes
	 * @param returnCode ReturnCode
	 */
	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	/**
	 * Methode zum Abfragen der mitgegebenen Nachricht
	 * @return Nachricht
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Methode zum Setzen der mitzugebenden Nachricht
	 * @param message Nachricht
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}

