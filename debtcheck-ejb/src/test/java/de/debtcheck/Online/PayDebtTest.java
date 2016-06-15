package de.debtcheck.Online;

import java.math.BigDecimal;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.debtcheck.dto.AddNewDebtResponsee;
import de.debtcheck.dto.PayDebtResponsee;
import de.debtcheck.dto.UserLoginResponse;

	
	@RunWith(Arquillian.class)
	public class PayDebtTest {
		
		@EJB
		OnlineIntegration bean;
		
		@Deployment
	    public static WebArchive createDeployment() {
	    	return ShrinkWrap.create(WebArchive.class, "test.war")
	               .addPackages(true,"de/debtcheck")
	               .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")               
	               .addAsWebInfResource("META-INF/ejb-jar.xml", "ejb-jar.xml");
	    }
		
		
		@Test
		/**
		 * Testet ob ein Account angelegt werden kann
		 */
		public void testPayDebt() throws Exception{
			bean.registerNewAccount("Max", "max123", "max@mail.de");
			bean.registerNewAccount("Paul", "paul123", "paul@mail.de");
			UserLoginResponse loginResponse = bean.login("max@mail.de", "max123");
			int sessionId = loginResponse.getSessionId();
		    AddNewDebtResponsee addNewDebtResponse = bean.addNewDebt(sessionId, "Paul", new BigDecimal(100.55), "ein Grund");
		    int debtId = addNewDebtResponse.getDebt().getId();
		    bean.logout(sessionId);
		    UserLoginResponse loginResponse2 = bean.login("paul@mail.de", "paul123");
		    int sessionId2 = loginResponse2.getSessionId();
		    PayDebtResponsee payDebtResponse = bean.payDebt(sessionId2, "Max", new BigDecimal(55.55), debtId);
		    bean.logout(sessionId2);
		    assert payDebtResponse.getReturnCode()==0 : "Bezahlung fehlgeschlagen";
		    assert payDebtResponse.getNewAmount()==new BigDecimal(45) : "Falsche Restsumme";
			}
	}




