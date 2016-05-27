package de.debtcheck.dto;

import java.util.HashMap;

import de.debtcheck.entities.Claim;
import de.debtcheck.entities.Debt;


public class AccountTO extends DataTransferObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String userName;
	private String password;
	private HashMap<Integer,Debt> debts;
	private HashMap<Integer,Claim> claims;
	
	public AccountTO()  {
	}
	
	public AccountTO(int id, String userName, String password,
			HashMap<Integer, Debt> debts, HashMap<Integer, Claim> claims ) {
		super();
		this.id = id;
		this.userName = userName;
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

	public HashMap<Integer, Debt> getDebts() {
		return debts;
	}

	public void setDebts(HashMap<Integer, Debt> debts) {
		this.debts = debts;
	}
	
	public HashMap<Integer, Claim> getClaims() {
		return claims;
	}
	
	public void setClaims(HashMap<Integer, Claim> claims) {
		this.claims = claims;
	}
	
	
}
