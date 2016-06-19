package de.debtcheck.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import de.debtcheck.entities.Account;

/**
 * Klasse für die Schuld eines Benutzers
 * 
 * @author Edgar Seibel
 * @author Josua Suren
 *
 */
@Entity
public class Debt implements Serializable {
	
	/**
	 * Attribute
	 */
	private static final long serialVersionUID = 1L;
	private static int lastID=0;
	
	@Id 
	private int id;
	private String reason;
	private BigDecimal amount;
	@ManyToOne
	private Account debtor;
	@ManyToOne
	private Account creditor;
	
	/**
	 * leerer Konstruktor
	 */
	public Debt(){};
	
	/**
	 * Konstruktor
	 * 
	 * @param debtor Schuldner
	 * @param creditor Gläubiger
	 * @param amount die Höhe der Schuld
	 * @param reason Grund der Schuld
	 */
	
	public Debt(Account debtor, Account creditor, BigDecimal amount, String reason) {
		this.id = ++lastID;
		this.amount = amount;
		this.debtor = debtor;
		this.creditor = creditor;
		this.reason = reason;
	}
	
	/**
	 * Methode zum Abfragen der ID
	 * @return DebtID
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Methode zum Abfragen der Höhe der Schuld
	 * @return Höhe der Schuld
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * Methode zum Abfragen des Gläubigers zur Schuld
	 * @return Account des Gläubigers
	 */
	public Account getCreditor() {
		return creditor;
	}
	
	/**
	 * Methode zum Abfragen des Schuldners zur Schuld
	 * @return Account des Schuldners
	 */
	public Account getDebtor() {
		return debtor;
	}
	
	/**
	 * Methode zum erhöhen der Schuld
	 * @param amount Betrag
	 */
	public void increase(BigDecimal amount) {
		this.amount = this.amount.add(amount);
	}
	
	/**
	 * Methode zum verringern der Schuld
	 * @param amount Betrag
	 */
	public void decrease(BigDecimal amount) {
		this.amount = this.amount.subtract(amount);
	}
	
	/**
	 * Methode zum Abfragen des Grunds
	 * @return Grund
	 */
	public String getReason(){
		return reason;
	}
	
	/**
	 * Methode zum Setzen des Grunds
	 * @param reason Grund
	 */
	public void setReason(String reason){
		this.reason = reason;
	}
	
	/**
	 * Debt toString Methode
	 */
	public String toString() {
		return "Debt " + this.id + " (Amount=" + this.amount + ", Owner=" + this.getDebtor().getUserName() + ")";
	}
}
