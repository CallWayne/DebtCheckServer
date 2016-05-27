package de.debtcheck.dto;

import java.math.BigDecimal;



public class ClaimTO extends DataTransferObject {
	
	private static final long serialVersionUID = 3440740273700082798L;
	
	private int id;
	private BigDecimal amount;
	private int ownerId;
	
	
	public ClaimTO() {
	}

	public ClaimTO(int id, BigDecimal amount, int ownerId) {
		super();
		this.id = id;
		this.amount = amount;
		this.ownerId = ownerId;
	}


	public String toString() {
		return "Account " + this.id + " (Balance=" + this.amount + ", Owner=" + this.getOwnerId() + ")";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal balance) {
		this.amount = balance;
	}


	public int getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	
}
