package de.debtcheck.dto;

import java.math.BigDecimal;

import de.debtcheck.entities.Account;


/**
 * TransferObject für die Klasse Debt
 * 
 * @author Josua Suren
 * @author Edgar Seibel
 *
 */
public class DebtTO extends DataTransferObject {
	
	/**
	 * Attribute
	 */
	private static final long serialVersionUID = 3440740273700082798L;
	
	
	private int id;
	private String reason;
	private BigDecimal amount;
	private String debtor;
	private String creditor;

	
	/**
	 * leerer Konstruktor
	 */
	public DebtTO() {}

	/**
	 * 
	 * @param debtor Schuldner
	 * @param creditor Gläubiger
	 * @param amount die Höhe der Schuld
	 * @param reason der Grund der Schuld
	 */
	public DebtTO(String debtor , String creditor, BigDecimal amount, String reason) {
		super();
		this.setDebtor(debtor);
		this.setCreditor(creditor);
		this.amount = amount;
		this.setReason(reason);
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal balance) {
		this.amount = balance;
	}
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCreditor() {
		return creditor;
	}

	public void setCreditor(String creditor) {
		this.creditor = creditor;
	}

	public String getDebtor() {
		return debtor;
	}

	public void setDebtor(String debtor) {
		this.debtor = debtor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
