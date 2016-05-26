package de.debtcheck.Online;

import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.debtcheck.dao.debtcheckDAOLocal;
import de.debtcheck.dto.DtoAssembler;
import de.debtcheck.dto.ReturnCodeResponse;
import de.debtcheck.dto.UserLoginResponse;
import de.debtcheck.dto.addNewDebtResponse;
import de.debtcheck.entities.account;
import de.debtcheck.entities.claim;
import de.debtcheck.entities.debt;
import de.debtcheck.entities.session;



@WebService
@WebContext(contextRoot="/debtcheck")
@Stateless
public class OnlineIntegration {
	
private static final Logger logger = Logger.getLogger(OnlineIntegration.class);

@EJB
private debtcheckDAOLocal dao;

@EJB
private DtoAssembler dtoAssembler;

private session getSession(int sessionId) throws NoSessionException {
	session session = dao.findSessionById(sessionId);
	if (session==null)
		throw new NoSessionException("Bitte zunächst ein Login durchführen.");
	else
		return session;
	}

public UserLoginResponse login(String username, String password) {
	UserLoginResponse response = new UserLoginResponse();
	try {
		account user = this.dao.findAccountByName(username);
		if (user != null && user.getPassword().equals(password)) {
			int sessionId = dao.createSession(user);
			response.setSessionId(sessionId);
			response.setAccount(this.dtoAssembler.makeDTO(user));
			logger.info("Login erfolgreich.");
		}
		else {
			logger.info("Login fehlgeschlagen, da Kunde unbekannt oder Passwort falsch. username=" + username);
			throw new InvalidLoginException("Login fehlgeschlagen, da Kunde unbekannt oder Passwort falsch. username="+username);
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

public addNewDebtResponse addNewDebt (int sessionId, int sourceAccount, String targetAccount, BigDecimal amount){
	addNewDebtResponse response = new addNewDebtResponse();
	try {
		session session = getSession(sessionId);
		account source = session.getUser();
		account target = this.dao.findAccountByName(targetAccount);
		if (source!=null && target!=null) {
			claim claim = new claim(source, amount);
			new debt(target, amount);
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
