package DebtCheckOnline;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import Claim.Claim;
import DebtCheckSession.DebtCheckSession;
import account.Account;
import debt.Debt;



@WebService
@WebContext(contextRoot="/xbank")
@Stateless
public class DebtCheckOnlineIntegration {
	
	private DebtCheckSession getSession(int sessionId) throws NoSessionException {
		//DebtCheckSession session = dao.findSessionById(sessionId);
		//if (session==null)
			throw new NoSessionException("Bitte zunächst ein Login durchführen.");
	//	else
		//	return session;
	}

	
	//Hier fehlt noch Response glaube ich (auch als Rückgabewert)
	public addNewDebtResponse addNewDebt (int sessionId, int sourceAccount, int targetAccount, BigDecimal amount){
		//TransferMoneyResponse response = new TransferMoneyResponse();
		try {
			DebtCheckSession session = getSession(sessionId);
			Account source = session.getUser();
			//Account target = this.dao.findAccountById(targetAccount);
			//if (source!=null && target!=null) {
				new Claim(source, amount);
				//new Debt(target, amount);
				//response.setNewBalance(source.getBalance());
			//}
		}
		catch (DebtCheckException e) {
			//response.setReturnCode(e.getErrorCode());
			//response.setMessage(e.getMessage());
		}
		//return response;
	}
}
	


