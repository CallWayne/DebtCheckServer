package Claim;

import java.io.Serializable;
import java.math.BigDecimal;

import account.Account;

public class Claim implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static int lastID=0;
	
	private int id;
	private BigDecimal balance;
	private Account owner;
	
	public Claim(Account owner) {
		this.id = ++lastID;
		this.balance = BigDecimal.ZERO;
		this.owner = owner;
		this.owner.addNewClaim(this);
	}
	
	public int getId() {
		return id;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public Account getOwner() {
		return owner;
	}
	
	public void increase(BigDecimal amount) {
		this.balance = this.balance.add(amount);
	}
	
	public void decrease(BigDecimal amount) {
		this.balance = this.balance.subtract(amount);
	}
	
	public String toString() {
		return "Debt " + this.id + " (Balance=" + this.balance + ", Owner=" + this.getOwner().getUserName() + ")";
	}

}



