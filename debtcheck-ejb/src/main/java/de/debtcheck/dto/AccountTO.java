package de.debtcheck.dto;

import java.util.HashMap;

import de.debtcheck.entities.Debt;


/**
 * TransferObject für die Klasse Account
 * 
 * @author Josua Suren
 * @author Edgar Seibel
 *
 */
public class AccountTO extends DataTransferObject{
	
	/**
	 * Attribute
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String userName;
	private String email;
	private String password;
	private HashMap<Integer,Debt> debts;
	private HashMap<Integer,Debt> claims;
	
	/**
	 * leerer Konstruktor
	 */
	public AccountTO()  {
	}
	/**
	 * Konstruktor
	 * 
	 * @param id ID des Accounts
	 * @param userName Benuztername
	 * @param email E-Mail des Benutzers
	 * @param password Passwort des Benuters
	 * @param debts Liste der Schulden
	 * @param claims Liste der Forderungen
	 */
	public AccountTO(int id, String userName, String email, String password, 
			HashMap<Integer, Debt> debts, HashMap<Integer, Debt> claims ) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.debts = debts;
		this.claims = claims;
	}

	/**
	 * Methode zum Abfragen der ID
	 * @return ID des Accounts
	 */
	public int getId() {
		return id;
	}

	/**
	 * Methode zum Setzen der ID
	 * @param id ID des Accounts
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Methode zum Abfragen des Benutzernamen
	 * @return Benutzername
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Methode zum Setzen des Benutzernamen
	 * @param userName Benutzername
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Methode zum Abfragen des Passworts
	 * @return Passwort des Benutzers
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Methode zum Setzen des Passworts
	 * @param password Passwort des Benutzers
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Methode zum Abfragen der E-Mail
	 * @return E-Mail des Benutzers
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Methode zum Setzen der E-Mail
	 * @param email E-Mail des Benutzers
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Methode zum Abfragen der Schuldenliste
	 * @return Liste der Schulden
	 */
	public HashMap<Integer, Debt> getDebts() {
		return debts;
	}

	/**
	 * Methode zum Setzen der Schulden
	 * @param debts Liste der Schulden
	 */
	public void setDebts(HashMap<Integer, Debt> debts) {
		this.debts = debts;
	}
	
	/**
	 * Methode zum Abfragen der Forderungsliste
	 * @return Liste der Forderungen
	 */
	public HashMap<Integer, Debt> getClaims() {
		return claims;
	}
	
	/**
	 * Methode zum Setzen der Forderungen
	 * @param claims Liste der Forderungen
	 */
	public void setClaims(HashMap<Integer, Debt> claims) {
		this.claims = claims;
	}
	
	
}
