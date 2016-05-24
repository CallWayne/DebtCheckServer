package de.debtcheck.dao;

import javax.ejb.Local;

import de.debtcheck.entities.*;


@Local
public interface debtcheckDAOLocal {
	
	public session findSessionById(int id);
	public int createSession(account user);
	public void closeSession(int id);
	public account findAccountByName(String username);
	
}
