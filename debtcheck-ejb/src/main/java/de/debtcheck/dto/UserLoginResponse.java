package de.debtcheck.dto;

/**
 * Response beim Login
 * 
 * @author Edgar Seibel
 * @author Josua Suren
 *
 */
public class UserLoginResponse extends ReturnCodeResponse {

	/**
	 * Attribute
	 */
	private static final long serialVersionUID = -3173158310918408228L;

	private int sessionId;
	private AccountTO account;
	
	/**
	 * Methode zur Abfrage der SessionID
	 * @return sessionId
	 */
	public int getSessionId() {
		return sessionId;
	}

	/**
	 * Methode zum Setzen der SessionID
	 * @param sessionId
	 */
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Methode gibt den Account zurück
	 * @return AccountTO
	 */
	public AccountTO getAccount() {
		return this.account;
	}

	/**
	 * Methode zum Setzen des Accounts
	 * @param account AccountTO
	 */
	public void setAccount(AccountTO account) {
		this.account = account;
	}

}
