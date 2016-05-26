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
public class account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static int lastID = 0;
	
	@Id @GeneratedValue
	private int id;
	@Column(nullable=false)
	private String userName;
	@Column(unique=true, nullable=false)
	private String email;
	private String password;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="owner") @MapKey
	private Map<account,debt> debts;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="owner") @MapKey
	private Map<account,claim> claims;
	
	@ManyToOne
	@JoinColumn(name = "parentId" )
	private account parent;
	@OneToMany(mappedBy="parent") 
	private Set<account> friends;
	
	public account (){}
	
	public account (String userName, String password, String email){
		this.id = lastID++;
		this.userName = userName;
		this.password = password;
		this.debts = new HashMap<>();
		this.claims = new HashMap<>();
		this.friends = new HashSet<>();
		this.email = email;
	}
	
	public void addNewDebt(account friend, debt newDebt) {
		this.debts.put(friend, newDebt);
	}
	
	public void addNewClaim(claim newClaim) {
		this.claims.put(this, newClaim);
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
	
	public Set<account> getFriends(){
		return friends;
	}
	
	public String toString() {
		return "Account (" + this.getId() + "): " + this.getUserName() + "/" + this.getPassword();
	}
	
}
