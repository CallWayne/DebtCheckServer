package DebtCheckOnline;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import account.AccountRegistry;
import de.xbank.banking.Account;
import de.xbank.dto.TransferMoneyResponse;
import de.xbank.onlinebanking.XbankException;
import de.xbank.session.XbankSession;




@WebService
@Stateless
public class DebtCheckOnlineIntegration {
	
	@EJB
	private AccountRegistry accountRegistry;
	
	
	//Hier fehlt noch Session glaube ich (auch als Parameterübergabe) und ?Response?
	public DebtAddedResponse addNewDebt (int SourceAccount, int targetAccount, BigDecimal amount){
		//TransferMoneyResponse response = new TransferMoneyResponse();
		try {
			//DebtCheckSession session = getSession(sessionId);
			//Account source = session.getUser().getAccountById(sourceAccount);
			Account target = this.accountRegistry.findAccountById(targetAccount);
			if (source!=null && target!=null) {
				new Claim(source, amount);
				new Debt(target, amount);
				//response.setNewBalance(source.getBalance());
			}
		}
		//catch (DebtCheckException e) {
			//response.setReturnCode(e.getErrorCode());
			//response.setMessage(e.getMessage());
		}
		//return response;
	}
	}

}
