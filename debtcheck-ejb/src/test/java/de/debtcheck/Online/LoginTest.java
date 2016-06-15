package de.debtcheck.Online;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.debtcheck.dto.UserLoginResponse;

@RunWith(Arquillian.class)
public class LoginTest {
	
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
	 * Testet ob man sich in einen Account einloggen kann
	 */
	public void testLogin() throws Exception{
		     bean.registerNewAccount("Max", "max123", "max@mail.de");
		     UserLoginResponse loginResponse = bean.login("max@mail.de", "max123");
		     assertEquals(loginResponse.getReturnCode(), 0);
		 	
		    int sessionId = loginResponse.getSessionId();
			bean.logout(sessionId);
		}

	}

