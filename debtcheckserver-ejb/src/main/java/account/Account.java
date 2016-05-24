package account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import Claim.Claim;
import debt.Debt;

@Entity
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static int lastID = 0;
	
	@Id @GeneratedValue
	private int id;
	@Column(unique=true, nullable=false)
	private String userName;
	private String password;
	private HashMap<Integer,Debt> debts;
	private HashMap<Integer,Claim> claims;
	private ArrayList<Account> friends;
	
	public Account (String userName, String password){
		this.id = lastID++;
		this.userName = userName;
		this.password = password;
		this.debts = new HashMap<>();
		this.claims = new HashMap<>();
		this.friends = new ArrayList<>();
	}
	
	public void addNewDebt(Debt newDebt) {
		this.debts.put(newDebt.getId(), newDebt);
	}
	
	public void addNewClaim(Claim newClaim) {
		this.claims.put(newClaim.getId(), newClaim);
	}
	
	public String getUserName() {
		return this.userName;
	}

	public String getPassword() {
		return this.password;
	}

	public Debt getDebtById(int debtId) {
		return debts.get(debtId);
	}

	public List<Debt> getDebts() {
		return new ArrayList<Debt>(debts.values());
	}
	
	public Claim getClaimById(int ClaimId) {
		return claims.get(ClaimId);
	}

	public List<Claim> getClaim() {
		return new ArrayList<Claim>(claims.values());
	}

	public int getId() {
		return id;
	}

	public void setId(int accountId) {
		this.id = accountId;
	}
	
	public List<Account> getFriends(){
		return friends;
	}
	
	public String toString() {
		return "Account (" + this.getId() + "): " + this.getUserName() + "/" + this.getPassword();
	}
	
}
