package de.debtcheck.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import de.debtcheck.entities.*;

@Entity
public class claim implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static int lastID=0;

	@Id @GeneratedValue
	private int id;
	private BigDecimal amount;
	@ManyToOne
	private account owner;
	
	public claim(account owner, BigDecimal amount) {
		this.id = ++lastID;
		this.amount = amount;
		this.owner = owner;
		this.owner.addNewClaim(this);
	}
	
	public int getId() {
		return id;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public account getOwner() {
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
