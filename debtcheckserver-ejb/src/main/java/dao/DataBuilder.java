package dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;


import java.math.BigDecimal;

import account.Account;
import debt.Debt;


@Singleton
@Startup
public class DataBuilder {

private static final Logger logger = Logger.getLogger(DataBuilder.class);
	
	@PersistenceContext
	EntityManager em;
	
	@EJB
	DebtCheckDAOLocal dao;
	
	@Resource
	private String username1, password1, username2, password2;

	@PostConstruct
	private void createTestData() {

		//erzeuge ein paar Beispieldaten zu Kunden und Konten, falls sie noch nicht in der DB vorhanden sind.
		Account account1 = dao.findAccountByName(username1);
		if (account1 == null) {
			//Kunde noch nicht vorhanden, also mit 2 neuen Konten anlegen:
			account1 = new Account(username1, password1);
			em.persist(account1);
			BigDecimal amount = new BigDecimal(5000.00);
			Debt debt1 = new Debt(account1, amount);
			em.persist(debt1);
			Debt debt2 = new Debt(account1, amount);
			em.persist(debt2);
			logger.info("Neu angelegt:" + account1);
			logger.info("Neu angelegt: " + debt1);
			logger.info("Neu angelegt: " + debt2);			
		}

		Account account2 = dao.findAccountByName(username2);
		if (account2 == null) {
			//Kunde noch nicht vorhanden, also mit 2 neuen Konten anlegen:
			account2 = new Account(username2, password2);
			em.persist(account2);
			BigDecimal amount = new BigDecimal(4000.00);
			Debt debt1 = new Debt(account2, amount);
			em.persist(debt1);
			logger.info("Neu angelegt:" + account1);
			logger.info("Neu angelegt: " + debt1);
		
		}
	}


}