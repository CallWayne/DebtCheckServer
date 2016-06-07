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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.swing.text.html.HTMLDocument.Iterator;

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
	
	/**
	@ManyToOne
	@JoinColumn(name = "parentId" )
	private Account parent;
	@OneToMany(mappedBy="parent") 
	private Set<Account> friends;
	*/
	
	/**
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="userName") @MapKey
	private Map<Integer, Account> friends;
	*/
	
	public Account (){}
	
	public Account (String userName, String email, String password){
		this.id = lastID++;
		this.userName = userName;
		this.password = password;
		this.debts = new HashMap<Integer, Debt>();
		this.claims = new HashMap<Integer, Debt>();
		//this.friends = new HashSet<>();
		this.email = email;
	}
	
	public void addNewDebt(Debt newDebt) {
		this.debts.put(newDebt.getId(), newDebt);
	}
	
	public void addNewClaim(Debt newClaim) {
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
	
	public Debt getClaimById(int ClaimId) {
		return claims.get(ClaimId);
	}
	
	public List<Debt> getClaims() {
		return new ArrayList<Debt>(claims.values());
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
	
	/**public void addFriend(Account friend){
		this.friends.add(friend);
	}
	
	public Account getFriend(Account userName){
	
	}
		
	
	
	public List<Account> getFriends(){
		ArrayList<Account> listFriends = new ArrayList<>();
		 Iterator<Account> it = this.friends.iterator();
	     while(it.hasNext()){
	        );
	}
	*/
	
	public String toString() {
		return "Account (" + this.getId() + "): " + this.getUserName() + "/" + this.getPassword();
	}
	
	
	
}
