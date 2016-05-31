package de.debtcheck.Online;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.debtcheck.dao.debtcheckDAOLocal;
import de.debtcheck.dto.DebtListResponse;
import de.debtcheck.dto.DtoAssembler;
import de.debtcheck.dto.FriendListResponse;
import de.debtcheck.dto.PayDebtResponsee;
import de.debtcheck.dto.ReturnCodeResponse;
import de.debtcheck.dto.UserLoginResponse;
import de.debtcheck.dto.AddFriendResponsee;
import de.debtcheck.dto.AddNewDebtResponsee;
import de.debtcheck.entities.Account;
import de.debtcheck.entities.Debt;
import de.debtcheck.entities.Session;




@WebService
@WebContext(contextRoot="/debtcheck")
@Stateless
public class OnlineIntegration {
	
private static final Logger logger = Logger.getLogger(OnlineIntegration.class);

@EJB
private debtcheckDAOLocal dao;

@EJB
private DtoAssembler dtoAssembler;

private Session getSession(int sessionId) throws NoSessionException {
	Session session = dao.findSessionById(sessionId);
	if (session==null)
		throw new NoSessionException("Bitte zunächst ein Login durchführen.");
	else
		return session;
	}


public UserLoginResponse registerNewAccount(String userName, String password, String email) {
	UserLoginResponse response = new UserLoginResponse();
	try {
		Account user = dao.createAccount(userName, password, email);		
		if (user != null) {
			//create new session for the just created customer:
			int sessionId = dao.createSession(user);
			logger.info("Registrierung von \"" + userName + "\" erfolgreich. "
					  + "Erzeugte Session=" + sessionId);
			response.setSessionId(sessionId);
		}
		else {
			logger.info("Registrieren fehlgeschlagen, da der Username bereits existiert."
					  + " username=" + userName);
			throw new DebtCheckException(30, "Registrieren fehlgeschlagen, da der Username "
					  + "bereits existiert. username=" + userName);
		}
	}
	catch (DebtCheckException e) {
		response.setReturnCode(e.getErrorCode());
		response.setMessage(e.getMessage());
	}		
	return response;		
}

public UserLoginResponse login(String username, String password) {
	UserLoginResponse response = new UserLoginResponse();
	try {
		Account user = this.dao.findAccountByName(username);
		if (user != null && user.getPassword().equals(password)) {
			int sessionId = dao.createSession(user);
			response.setSessionId(sessionId);
			response.setAccount(this.dtoAssembler.makeDTO(user));
			logger.info("Login erfolgreich.");
		}
		else {
			logger.info("Login fehlgeschlagen, da Kunde unbekannt oder Passwort falsch. username=" + username);
			throw new InvalidLoginException("Login fehlgeschlagen, da Kunde unbekannt oder Passwort falsch. username=" + username);
		}
	}
	catch (DebtCheckException e) {
		response.setReturnCode(e.getErrorCode());
		response.setMessage(e.getMessage());
	}
	return response;
	}

public ReturnCodeResponse logout(int sessionId) {
	dao.closeSession(sessionId);
	ReturnCodeResponse response = new ReturnCodeResponse();
	logger.info("Logout successful.");
	return response;
	}

public DebtListResponse getMyDebts(int sessionId) {
	DebtListResponse response = new DebtListResponse();
	try {
		Session session = getSession(sessionId);
		List<Debt> accountList = session.getUser().getDebts();
		response.setDebtList(dtoAssembler.makeDebtDTO(accountList));
		logger.info("Result of getMyAccounts(): "+accountList);		
	}
	catch (DebtCheckException e) {
		response.setReturnCode(e.getErrorCode());
		response.setMessage(e.getMessage());
	}
	return response;
}

public DebtListResponse getMyClaims(int sessionId) {
	DebtListResponse response = new DebtListResponse();
	try {
		Session session = getSession(sessionId);
		List<Debt> accountList = session.getUser().getClaims();
		response.setDebtList(dtoAssembler.makeDebtDTO(accountList));
		logger.info("Result of getMyClaims(): "+accountList);		
	}
	catch (DebtCheckException e) {
		response.setReturnCode(e.getErrorCode());
		response.setMessage(e.getMessage());
	}
	return response;
}


public AddNewDebtResponsee addNewDebt (int sessionId, String debtorAccount, BigDecimal amount){
	AddNewDebtResponsee response = new AddNewDebtResponsee();
	try {
		Session session = getSession(sessionId);
		Account creditor = session.getUser();
		Account debtor = this.dao.findAccountByName(debtorAccount);
		if (creditor!=null && debtor!=null) {
			Debt debt = new Debt(debtor, creditor, amount);
			creditor.addNewClaim(debt);
			debtor.addNewDebt(debt);
			response.setNewAmount(debt.getAmount());
		}
	}
	catch (DebtCheckException e) {
		response.setReturnCode(e.getErrorCode());
		response.setMessage(e.getMessage());
	}
	return response;
	}

public PayDebtResponsee payDebt (int sessionId, String creditorAccount, BigDecimal amount, int id){
	PayDebtResponsee response = new PayDebtResponsee();
	try{
		Session session = getSession(sessionId);
		Account debtor = session.getUser();
		Account creditor = this.dao.findAccountByName(creditorAccount);
		if (debtor!=null && creditor!=null){
			Debt debt = debtor.getDebtById(id);
			BigDecimal debtHeight = debt.getAmount();
			int compare = debtHeight.compareTo(amount);
			if(compare == 0){
				debtor.removeDebt(id);
				creditor.removeClaim(id);
				response.setNewAmount(new BigDecimal(0));
			}
			if(compare == 1){
				debt.decrease(amount);
				response.setNewAmount(debt.getAmount());
			}
			return response;
		}
	}
		catch (DebtCheckException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}
			return response;
	}
/**
	public AddFriendResponsee addFriend(int sessionId, String userName){
		AddFriendResponsee response = new AddFriendResponsee();
		try{
			Session session = getSession(sessionId);
			Account account = session.getUser();
			Account friend = this.dao.findAccountByName(userName);
			if(account!=null && friend!= null){
				account.addFriend(friend);
				response.setFriendName(friend.getUserName());
			}
		}
			catch (DebtCheckException e) {
				response.setReturnCode(e.getErrorCode());
				response.setMessage(e.getMessage());
			}
				return response;
		
	}
	
	public FriendListResponse getMyFriends(int sessionId){
		FriendListResponse response = new FriendListResponse();
		try {
			Session session = getSession(sessionId);
			Set<Account> friendList = session.getUser().getFriends();
			response.setFriendList(dtoAssembler.makeAccountDTO(friendList));
			logger.info("Result of getMyClaims(): "+friendList);		
		}
		catch (DebtCheckException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}
		return response;
	}
	*/

}
