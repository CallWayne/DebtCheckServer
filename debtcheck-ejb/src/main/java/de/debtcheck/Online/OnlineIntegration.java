package de.debtcheck.Online;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.debtcheck.dao.debtcheckDAOLocal;
import de.debtcheck.dto.ClaimListResponse;
import de.debtcheck.dto.DebtListResponse;
import de.debtcheck.dto.DtoAssembler;
import de.debtcheck.dto.ReturnCodeResponse;
import de.debtcheck.dto.UserLoginResponse;
import de.debtcheck.dto.AddNewDebtResponsee;
import de.debtcheck.entities.Account;
import de.debtcheck.entities.Claim;
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

public ClaimListResponse getMyClaims(int sessionId) {
	ClaimListResponse response = new ClaimListResponse();
	try {
		Session session = getSession(sessionId);
		List<Claim> accountList = session.getUser().getClaims();
		response.setClaimList(dtoAssembler.makeClaimDTO(accountList));
		logger.info("Result of getMyClaims(): "+accountList);		
	}
	catch (DebtCheckException e) {
		response.setReturnCode(e.getErrorCode());
		response.setMessage(e.getMessage());
	}
	return response;
}


public AddNewDebtResponsee addNewDebt (int sessionId, String targetAccount, BigDecimal amount){
	AddNewDebtResponsee response = new AddNewDebtResponsee();
	try {
		Session session = getSession(sessionId);
		Account source = session.getUser();
		Account target = this.dao.findAccountByName(targetAccount);
		if (source!=null && target!=null) {
			Claim claim = new Claim(source, amount);
			new Debt(target, amount);
			response.setNewAmount(claim.getAmount());
		}
	}
	catch (DebtCheckException e) {
		response.setReturnCode(e.getErrorCode());
		response.setMessage(e.getMessage());
	}
	return response;
	}
}
