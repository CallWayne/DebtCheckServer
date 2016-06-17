package de.debtcheck.dto;

import java.math.BigDecimal;


/**
 * TransferObject für die Klasse Claim
 * 
 * @author Josua Suren
 * @author Edgar Seibel
 *
 */
public class ClaimTO extends DataTransferObject {
	
	private static final long serialVersionUID = 3440740273700082798L;
	
	private int id;
	private BigDecimal amount;
	private int ownerId;
	
	
	public ClaimTO() {
	}

	/**
	 * 
	 * @param id ID der Forderung
	 * @param amount Höhe der Forderung
	 * @param ownerId Gläubiger der Forderung
	 */
	public ClaimTO(int id, BigDecimal amount, int ownerId) {
		super();
		this.id = id;
		this.amount = amount;
		this.ownerId = ownerId;
	}

/**
 * toString Methode
 */
	public String toString() {
		return "Account " + this.id + " (Balance=" + this.amount + ", Owner=" + this.getOwnerId() + ")";
	}

/**
 * Methode zum Abfragen der ID
 * @return ID der Forderung
 */
	public int getId() {
		return id;
	}

/**
 * Methode zum Setzen der ID
 * @param id ID der Forderung
 */
	public void setId(int id) {
		this.id = id;
	}

/**
 * Methode zum Abfragen der Höhe der Forderung
 * @return Höhe der Forderung
 */
	public BigDecimal getAmount() {
		return amount;
	}

/**
 * Methode zum Setzen der Höhe der Forderung
 * @param balance Höhe der Forderung
 */
	public void setAmount(BigDecimal balance) {
		this.amount = balance;
	}

/**
 * Methode zum Anfragen des Gläubigers
 * @return ID des Gläubigeraccounts
 */
	public int getOwnerId() {
		return ownerId;
	}

/**
 * Methode zum Setzen des Gläubigers
 * @param ownerId ID des Gläubigeraccounts
 */
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
}
