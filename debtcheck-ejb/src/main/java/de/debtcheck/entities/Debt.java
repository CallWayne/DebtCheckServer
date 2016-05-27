package de.debtcheck.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import de.debtcheck.entities.Account;

@Entity
public class Debt implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static int lastID=0;
	
	@Id @GeneratedValue
	private int id;
	private BigDecimal amount;
	@ManyToOne
	private Account owner;
	
	public Debt(){};
	
	public Debt(Account owner, BigDecimal amount) {
		this.id = ++lastID;
		this.amount = amount;
		this.owner = owner;
		//this.owner.addNewDebt(this);
	}
	
	public int getId() {
		return id;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public Account getOwner() {
		return owner;
	}
	
	public void increase(BigDecimal amount) {
		this.amount = this.amount.add(amount);
	}
	
	public void decrease(BigDecimal amount) {
		this.amount = this.amount.subtract(amount);
	}
	
	public String toString() {
		return "Debt " + this.id + " (Amount=" + this.amount + ", Owner=" + this.getOwner().getUserName() + ")";
	}
}
