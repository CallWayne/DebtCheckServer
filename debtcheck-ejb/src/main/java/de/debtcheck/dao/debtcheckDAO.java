package de.debtcheck.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.debtcheck.entities.Account;
import de.debtcheck.entities.Debt;
import de.debtcheck.entities.Session;

/**
 * 
 * @author Josua Suren
 * @author Edgar Seibel
 *
 */

@Stateless
public class debtcheckDAO implements debtcheckDAOLocal {
	
	@PersistenceContext
	EntityManager em;
	private List results;

	
	/**
	 * Methode holt die Session aus der Datenbank
	 * @param id
	 * @return Session
	 */
	public Session findSessionById(int id) {
    	return em.find(Session.class, id);
    }
	
	/**
	 * Methode persistiert eine Session in der Datenbank
	 * @param user Account des Benutzers
	 * @return SessionId
	 */
	public int createSession(Account user) {
        Session newSession = new Session(user);
        em.persist(newSession);
        return newSession.getId();
    }
	
	/**
	 * Methode löscht die Session aus der Datenbank
	 * @param id sessionId
	 */
	public void closeSession(int id) {
    	Session session = em.find(Session.class, id);
    	if (session != null) {
    		em.remove(session);
    	}
    }
	
	/**
	 * Methode zum finden eines Accounts aus der Datenbank
	 * @param userName Benutzername nach dem gesucht wird
	 * @return Account des gesuchten Benutzers
	 */
	public Account findAccountByName(String userName) {
    	List results = em.createQuery("SELECT a FROM Account a WHERE a.userName LIKE :accName")
    	                 .setParameter("accName", userName)
    	                 .getResultList();
    	if (results.size()==1) {
    	    return (Account) results.get(0);
    	}
    	else {
    		return null;
    	}
    }
	
	/**
	 * Methode zum finden eines Accounts aus der Datenbank
	 * @param email E-Mail nach der gesucht wird
	 * @return Account des gesuchten Benutzers
	 */
	public Account findAccountByEmail(String email) {
    	List results = em.createQuery("SELECT a FROM Account a WHERE a.email LIKE :accEmail")
    	                 .setParameter("accEmail", email)
    	                 .getResultList();
    	if (results.size()==1) {
    	    return (Account) results.get(0);
    	}
    	else {
    		return null;
    	}
    }	
	
	/**
	 * Methode persistiert den Account in der Datenbank
	 * @param userName Benutzername
	 * @param password Passwort des Benutzers
	 * @param email E-Mail des Benutzers
	 * @return Account des erstellten Benutzers
	 */
	public Account createAccount(String userName, String password, String email) {
		if(findAccountByName(userName) == null && findAccountByEmail(email) == null) {
			Account user = new Account(userName, password, email);
			em.persist(user);	
			return user;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Methode persistiert eine Schuld in der Datenbank
	 * @param debtor Account des Schuldner
	 * @param creditor Account des Gläubigers
	 * @param amount die Höhe der Schuld
	 * @param reason der Grund der Schuld
	 * @return die erstellte Debt
	 */
	public Debt createDebt(Account debtor, Account creditor, BigDecimal amount, String reason) {
			Debt debt = new Debt(debtor, creditor, amount, reason);
			em.persist(debt);	
			return debt;
		}
	
	/**
	 * Methode zum aktualisieren einer Schuld in der Datenbank
	 * @param debtId ID der Schuld
	 */
	public void refreshDebt(int debtId){
		Debt debt = em.find(Debt.class, debtId);
		em.refresh(debt);
	}
	
	/**
	 * Methode zum löschen einer Schuld aus der Datenbank
	 * @param debtId ID der Schuld
	 */
	public void removeDebt(int debtId) {
    	Debt debt = em.find(Debt.class, debtId);
    	if (debt != null) {
    		em.remove(debt);
    	}
	}
	

}