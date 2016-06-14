package de.debtcheck.dao;

import java.math.BigDecimal;

import javax.ejb.Local;

import de.debtcheck.entities.*;


@Local
public interface debtcheckDAOLocal {
	
	public Session findSessionById(int id);
	public int createSession(Account user);
	public void closeSession(int id);
	public Account findAccountByName(String username);
	public Account findAccountByEmail(String email);
	public Account createAccount(String userName, String password, String email);
	public Debt createDebt(Account debtor, Account creditor, BigDecimal amount, String reason);
	public void removeDebt(int debtId);
	
	/**
	public Friend createFriend(Account owner, String userName);
	public void removeFriend(int friendId);
	*/
	
	
}
	
	

