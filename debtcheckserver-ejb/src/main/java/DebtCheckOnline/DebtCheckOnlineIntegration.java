package DebtCheckOnline;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import account.AccountRegistry;




@WebService
@Stateless
public class DebtCheckOnlineIntegration {
	
	@EJB
	private AccountRegistry accountRegistry;

}
