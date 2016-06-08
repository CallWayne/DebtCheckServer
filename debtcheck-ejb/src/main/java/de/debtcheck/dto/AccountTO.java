package de.debtcheck.dto;

import java.util.HashMap;

import de.debtcheck.entities.Debt;



public class AccountTO extends DataTransferObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String userName;
	private String password;
	private String email;
	private HashMap<Integer,Debt> debts;
	private HashMap<Integer,Debt> claims;
	
	public AccountTO()  {
	}
	
	public AccountTO(int id, String userName, String password, String email, 
			HashMap<Integer, Debt> debts, HashMap<Integer, Debt> claims ) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.debts = debts;
		this.claims = claims;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public HashMap<Integer, Debt> getDebts() {
		return debts;
	}

	public void setDebts(HashMap<Integer, Debt> debts) {
		this.debts = debts;
	}
	
	public HashMap<Integer, Debt> getClaims() {
		return claims;
	}
	
	public void setClaims(HashMap<Integer, Debt> claims) {
		this.claims = claims;
	}
	
	
}
