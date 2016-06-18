package de.debtcheck.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

/**
 * Klasse für den Account eines Benutzers
 * 
 * @author Josua Suren
 * @author Edgar Seibel
 *
 */
@Entity
public class Account implements Serializable {
	
	/**
	 * Attribute
	 */
	private static final long serialVersionUID = 1L;
	private static int lastID = 0;
	
	@Id
	private int id;
	@Column(nullable=false)
	private String userName;
	@Column(unique=true, nullable=false)
	private String email;
	private String password;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="debtor") @MapKey
	private Map<Integer, Debt> debts;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="creditor") @MapKey
	private Map<Integer, Debt> claims;
	
	/**
	 * leerer Konstruktor
	 */
	public Account (){}
	
	/**
	 * Konstruktor
	 * 
	 * @param userName Benuztername
	 * @param email E-Mail des Benutzers
	 * @param password Passwort des Benuters
	 */
	public Account (String userName, String email, String password){
		this.id = lastID++;
		this.userName = userName;
		this.password = password;
		this.debts = new HashMap<Integer, Debt>();
		this.claims = new HashMap<Integer, Debt>();
		this.email = email;
	}
	
	/**
	 * Methode zum hinzufügen einer neuen Schuld
	 * @param newDebt Debt Objekt
	 */
	public void addNewDebt(Debt newDebt) {
		this.debts.put(newDebt.getId(), newDebt);
	}
	
	/**
	 * Methode zum hinzufügen einer neuen Forderung
	 * @param newClaim Debt Objekt
	 */
	public void addNewClaim(Debt newClaim) {
		this.claims.put(newClaim.getId(), newClaim);
	}
	
	/**
	 * Methode zum Abfragen des Benutzernamens
	 * @return Benutzername
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * Methode zum Abfragen des Passworts
	 * @return Passwort
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * Methode zum Abfragen der E-Mail
	 * @return E-Mail
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Methode zum Abfragen einer Schuld
	 * @param debtId ID der Schuld
	 * @return Debt Objekt
	 */
	public Debt getDebtById(int debtId) {
		return debts.get(debtId);
	}
	
	/**
	 * Methode zum Abfragen aller Schulden
	 * @return Liste aller Schulden
	 */
	public List<Debt> getDebts() {
		return new ArrayList<Debt>(debts.values());
	}
	
	/**
	 * Methode zum löschen einer Schuld
	 * @param id ID der Schuld
	 */
	public void removeDebt(int id){
		debts.remove(id);
	}
	
	/**
	 * Methode zum Abfragen einer Forderung
	 * @param ClaimID ID der Forderung
	 * @return Debt Objekt
	 */
	public Debt getClaimById(int ClaimId) {
		return claims.get(ClaimId);
	}
	
	/**
	 * Methode zum Abfragen aller Forderungen
	 * @return Liste aller Forderungen
	 */
	public List<Debt> getClaims() {
		return new ArrayList<Debt>(claims.values());
	}
	
	/**
	 * Methode zum löschen einer Forderung
	 * @param id ID der Forderung
	 */
	public void removeClaim(int id){
		claims.remove(id);
	}
	
	/**
	 * Methode zum Abfragen der Account ID
	 * @return ID des Accounts
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Methode zum Setzen der Account ID
	 * @param accountId Account ID
	 */
	public void setId(int accountId) {
		this.id = accountId;
	}
	
	/**
	 * Account toString Methode
	 */
	public String toString() {
		return "Account (" + this.getId() + "): " + this.getUserName() + "/" + this.getPassword();
	}
	
	
	
}
