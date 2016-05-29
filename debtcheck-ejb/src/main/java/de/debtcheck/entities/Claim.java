package de.debtcheck.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Claim implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static int lastID=0;

	@Id @GeneratedValue
	private int id;
	private BigDecimal amount;
	@ManyToOne
	private Account creditor;
	private Account debtor;
	
	public Claim() {}
	
	public Claim(Account creditor, Account debtor, BigDecimal amount) {
		this.id = ++lastID;
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
