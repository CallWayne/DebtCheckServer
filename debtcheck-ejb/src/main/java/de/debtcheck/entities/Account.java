package de.debtcheck.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static int lastID = 0;
	
	@Id 
	//@GeneratedValue
	private int id;
	@Column(nullable=false)
	private String userName;
	@Column(unique=true, nullable=false)
	private String email;
	private String password;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="debtor") @MapKey
	private Map<Integer, Debt> debts;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="creditor") @MapKey
	private Map<Integer, Claim> claims;
	
	@ManyToOne
	@JoinColumn(name = "parentId" )
	private Account parent;
	@OneToMany(mappedBy="parent") 
	private Set<Account> friends;
	
	public Account (){}
	
	public Account (String userName, String password, String email){
		this.id = lastID++;
		this.userName = userName;
		this.password = password;
		this.debts = new HashMap<>();
		this.claims = new HashMap<>();
		this.friends = new HashSet<>();
		this.email = email;
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
	
	public String getEmail() {
		return this.email;
	}
	
	public Debt getDebtById(int debtId) {
		return debts.get(debtId);
	}
	
	public List<Debt> getDebts() {
		return new ArrayList<Debt>(debts.values());
	}
	
	public void removeDebt(int id){
		debts.remove(id);
	}
	
	public Claim getClaimById(int ClaimId) {
		return claims.get(ClaimId);
	}
	
	public List<Claim> getClaims() {
		return new ArrayList<Claim>(claims.values());
	}
	
	public void removeClaim(int id){
		claims.remove(id);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int accountId) {
		this.id = accountId;
	}
	
	public Set<Account> getFriends(){
		return friends;
	}
	
	public String toString() {
		return "Account (" + this.getId() + "): " + this.getUserName() + "/" + this.getPassword();
	}
	
}
