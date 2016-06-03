package de.debtcheck.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Claim implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id 
	//@GeneratedValue
	private int id;
	private BigDecimal amount;
	@ManyToOne
	private Account creditor;
	@ManyToOne
	private Account debtor;
	
	public Claim() {}
	
	public Claim(int id, Account creditor, Account debtor, BigDecimal amount) {
		this.id = id;
		this.amount = amount;
		this.creditor = creditor;
		this.debtor = debtor;
	}
	
	public int getId() {
		return id;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public Account getCreditor() {
		return creditor;
	}
	
	public Account getDebtor() {
		return debtor;
	}
	
	public void increase(BigDecimal amount) {
		this.amount = this.amount.add(amount);
	}
	
	public void decrease(BigDecimal amount) {
		this.amount = this.amount.subtract(amount);
	}
	
	public String toString() {
		return "Debt " + this.id + " (Amount=" + this.amount + ", Owner=" + this.getCreditor().getUserName() + ")";
	}
}
