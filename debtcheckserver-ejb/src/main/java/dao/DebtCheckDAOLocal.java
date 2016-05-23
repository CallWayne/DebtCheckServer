package dao;

import javax.ejb.Local;

import account.Account;
import DebtCheckSession.DebtCheckSession;

@Local
public interface DebtCheckDAOLocal {

	public DebtCheckSession findSessionById(int id);
	public int createSession(Account user);
	public void closeSession(int id);
	public Account findAccountByName(String username);
}

