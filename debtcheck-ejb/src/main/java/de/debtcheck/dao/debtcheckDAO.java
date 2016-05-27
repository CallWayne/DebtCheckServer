package de.debtcheck.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.debtcheck.entities.Account;
import de.debtcheck.entities.Session;



@Stateless
public class debtcheckDAO implements debtcheckDAOLocal {
	
	@PersistenceContext
	EntityManager em;

	public Session findSessionById(int id) {
    	return em.find(Session.class, id);
    }
	
	public int createSession(Account user) {
        Session newSession = new Session(user);
        em.persist(newSession);
        return newSession.getId();
    }
	
	public void closeSession(int id) {
    	Session session = em.find(Session.class, id);
    	if (session != null) {
    		em.remove(session);
    	}
    }
	
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

}