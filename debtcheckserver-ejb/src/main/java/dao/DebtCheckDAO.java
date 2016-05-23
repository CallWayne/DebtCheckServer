package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import DebtCheckSession.DebtCheckSession;
import account.Account;


@Stateless
public class DebtCheckDAO implements DebtCheckDAOLocal {
	
	@PersistenceContext
	EntityManager em;
	
	
    public int createSession(Account user) {
        DebtCheckSession newSession = new DebtCheckSession(user);
        em.persist(newSession);
        return newSession.getId();
    }

	
	public DebtCheckSession findSessionById(int id) {
    	return em.find(DebtCheckSession.class, id);
    }
	
	public void closeSession(int id) {
    	DebtCheckSession session = em.find(DebtCheckSession.class, id);
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
