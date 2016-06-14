package de.debtcheck.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static int lastID = 0;
	
	@Id
	private int id;
	@Column(nullable=false)
	private String userName;
	@Column(unique=true, nullable=false)
	private String email;
	private String password;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="debtor") @MapKey
	private Map<Integer, Debt> debts;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="creditor") @MapKey
	private Map<Integer, Debt> claims;
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="owner") @MapKey
	private Map<Integer, Friend> friends;
	
	
	public Account (){}
	
	public Account (String userName, String email, String password){
		this.id = lastID++;
		this.userName = userName;
		this.password = password;
		this.debts = new HashMap<Integer, Debt>();
		this.claims = new HashMap<Integer, Debt>();
		this.friends = new HashMap<Integer, Friend>();
		this.email = email;
	}
	
	public void addNewDebt(Debt newDebt) {
		this.debts.put(newDebt.getId(), newDebt);
	}
	
	public void addNewClaim(Debt newClaim) {
		this.claims.put(newClaim.getId(), newClaim);
	}
	
	public void addFriend(Friend friend) {
		this.friends.put(friend.getId(), friend);
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
	
	public Debt getClaimById(int ClaimId) {
		return claims.get(ClaimId);
	}
	
	public List<Debt> getClaims() {
		return new ArrayList<Debt>(claims.values());
	}
	
	public void removeClaim(int id){
		claims.remove(id);
	}
	
	public Debt getFriendById(int FriendId) {
		return claims.get(FriendId);
	}
	
	public List<Friend> getFriends() {
		return new ArrayList<Friend>(friends.values());
	}
	
	public void removeFriend(int id){
		friends.remove(id);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int accountId) {
		this.id = accountId;
	}
	

	
	public String toString() {
		return "Account (" + this.getId() + "): " + this.getUserName() + "/" + this.getPassword();
	}
	
	
	
}
