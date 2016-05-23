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
import dao.DebtCheckDAOLocal;
import debt.Debt;
import dto.DtoAssembler;
import dto.ReturnCodeResponse;
import dto.UserLoginResponse;
import dto.addNewDebtResponse;



@WebService
@WebContext(contextRoot="/debtcheck")
@Stateless
public class DebtCheckOnlineIntegration {
	
	private static final Logger logger = Logger.getLogger(DebtCheckOnlineIntegration.class);
	
	@EJB
	private DebtCheckDAOLocal dao;
	
	@EJB
	private DtoAssembler dtoAssembler;
	
	
	private DebtCheckSession getSession(int sessionId) throws NoSessionException {
		DebtCheckSession session = dao.findSessionById(sessionId);
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
			DebtCheckSession session = getSession(sessionId);
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
	


