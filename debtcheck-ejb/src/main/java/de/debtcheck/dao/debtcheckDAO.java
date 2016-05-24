package de.debtcheck.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.debtcheck.entities.account;
import de.debtcheck.entities.session;



@Stateless
public class debtcheckDAO implements debtcheckDAOLocal {
	
	@PersistenceContext
	EntityManager em;

	public session findSessionById(int id) {
    	return em.find(session.class, id);
    }
	
	public int createSession(account user) {
        session newSession = new session(user);
        em.persist(newSession);
        return newSession.getId();
    }
	
	public void closeSession(int id) {
    	session session = em.find(session.class, id);
    	if (session != null) {
    		em.remove(session);
    	}
    }
	
	public account findAccountByName(String userName) {
    	List results = em.createQuery("SELECT a FROM Account a WHERE a.userName LIKE :accName")
    	                 .setParameter("accName", userName)
    	                 .getResultList();
    	if (results.size()==1) {
    	    return (account) results.get(0);
    	}
    	else {
    		return null;
    	}
    }

}