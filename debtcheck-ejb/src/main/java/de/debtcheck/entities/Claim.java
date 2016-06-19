package de.debtcheck.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Klasse für die Forderung eines Benutzers
 * 
 * @author Edgar Seibel
 * @author Josua Suren
 *
 */
@Entity
public class Claim implements Serializable {
	
	/**
	 * Attribute
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	//@GeneratedValue
	private int id;
	private BigDecimal amount;
	@ManyToOne
	private Account creditor;
	@ManyToOne
	private Account debtor;
	
	/**
	 * leerer Konstruktor
	 */
	public Claim() {}
	
	/**
	 * Konstruktor
	 * 
	 * @param id
	 * @param creditor Gläubiger
	 * @param debtor Schuldner
	 * @param amount die Höhe der Forderung
	 */
	public Claim(int id, Account creditor, Account debtor, BigDecimal amount) {
		this.id = id;
		this.amount = amount;
		this.creditor = creditor;
		this.debtor = debtor;
	}
	
	/**
	 * Methode zum Abfragen der ID
	 * @return ClaimID
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Methode zum Abfragen der Höhe der Forderung
	 * @return Höhe der Forderung
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * Methode zum Abfragen des Gläubigers zur Forderung
	 * @return Account des Gläubigers
	 */
	public Account getCreditor() {
		return creditor;
	}
	
	/**
	 * Methode zum Abfragen des Schuldners zur Forderung
	 * @return Account des Schuldners
	 */
	public Account getDebtor() {
		return debtor;
	}
	
	/**
	 * Methode zum erhöhen der Forderung
	 * @param amount Betrag
	 */
	public void increase(BigDecimal amount) {
		this.amount = this.amount.add(amount);
	}
	
	/**
	 * Methode zum verringern der Forderung
	 * @param amount Betrag
	 */
	public void decrease(BigDecimal amount) {
		this.amount = this.amount.subtract(amount);
	}
	
	/**
	 * Claim toString Methode
	 */
	public String toString() {
		return "Debt " + this.id + " (Amount=" + this.amount + ", Owner=" + this.getCreditor().getUserName() + ")";
	}
}
