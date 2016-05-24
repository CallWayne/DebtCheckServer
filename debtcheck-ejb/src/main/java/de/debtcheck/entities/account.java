package de.debtcheck.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import de.debtcheck.entities.*;

public class account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static int lastID = 0;
	
	private int id;
	private String userName;
	private String password;
	private HashMap<Integer,debt> debts;
	private HashMap<Integer,claim> claims;
	private ArrayList<account> friends;
	
	public account (String userName, String password){
		this.id = lastID++;
		this.userName = userName;
		this.password = password;
		this.debts = new HashMap<>();
		this.claims = new HashMap<>();
		this.friends = new ArrayList<>();
	}
	
	public void addNewDebt(debt newDebt) {
		this.debts.put(newDebt.getId(), newDebt);
	}
	
	public void addNewClaim(claim newClaim) {
		this.claims.put(newClaim.getId(), newClaim);
	}
	
	public String getUserName() {
		return this.userName;
	}
	public String getPassword() {
		return this.password;
	}
	public debt getDebtById(int debtId) {
		return debts.get(debtId);
	}
	public List<debt> getDebts() {
		return new ArrayList<debt>(debts.values());
	}
	
	public claim getClaimById(int ClaimId) {
		return claims.get(ClaimId);
	}
	public List<claim> getClaim() {
		return new ArrayList<claim>(claims.values());
	}
	public int getId() {
		return id;
	}
	public void setId(int accountId) {
		this.id = accountId;
	}
	
	public List<account> getFriends(){
		return friends;
	}
	
	public String toString() {
		return "Account (" + this.getId() + "): " + this.getUserName() + "/" + this.getPassword();
	}
	
}
