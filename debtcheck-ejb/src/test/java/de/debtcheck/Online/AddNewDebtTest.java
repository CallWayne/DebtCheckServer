package de.debtcheck.Online;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.debtcheck.dto.AddNewDebtResponsee;
import de.debtcheck.dto.UserLoginResponse;

@RunWith(Arquillian.class)
public class AddNewDebtTest {

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
    * Testet ob ein Schuld angelegt werden kann
    */
    	public void addDebtTest() throws Exception {
    			bean.registerNewAccount("Max", "max123", "max@mail.de");
    			bean.registerNewAccount("Paul", "paul123", "paul@mail.de");
    			UserLoginResponse loginResponse = bean.login("max@mail.de", "max123");
    			int sessionId = loginResponse.getSessionId();
    		    AddNewDebtResponsee addDebtResponse = bean.addNewDebt(sessionId, "Paul", new BigDecimal(100.55), "ein Grund");
    		    assertEquals(addDebtResponse.getReturnCode(), 0);
    		    assertEquals(addDebtResponse.getNewAmount(), new BigDecimal(100.55));
    		 
    			bean.logout(sessionId);
    		}

    	}
	

