package de.debtcheck.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import de.debtcheck.entities.Account;

@Entity
public class Debt implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static int lastID=0;
	
	@Id 
	private int id;
	private BigDecimal amount;
	@ManyToOne
	private Account debtor;
	@ManyToOne
	private Account creditor;
	
	public Debt(){};
	
	public Debt(Account debtor, Account creditor, BigDecimal amount) {
		this.id = ++lastID;
		this.amount = amount;
		this.debtor = debtor;
		this.creditor = creditor;
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
		return "Debt " + this.id + " (Amount=" + this.amount + ", Owner=" + this.getDebtor().getUserName() + ")";
	}
}
