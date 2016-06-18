package de.debtcheck.Online;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import org.jboss.logging.Logger;
import org.jboss.ws.api.annotation.WebContext;

import de.debtcheck.dao.debtcheckDAOLocal;
import de.debtcheck.dto.DebtListResponse;
import de.debtcheck.dto.DtoAssembler;
import de.debtcheck.dto.PayDebtResponsee;
import de.debtcheck.dto.ReturnCodeResponse;
import de.debtcheck.dto.UserLoginResponse;
import de.debtcheck.dto.AddNewDebtResponsee;
import de.debtcheck.entities.Account;
import de.debtcheck.entities.Debt;
import de.debtcheck.entities.Session;


/**
 * 
 * @author Josua Suren
 * @author Edgar Seibel
 *
 */

@WebService
@WebContext(contextRoot="/debtcheck")
@Stateless
public class OnlineIntegration {
	
private static final Logger logger = Logger.getLogger(OnlineIntegration.class);

@EJB
private debtcheckDAOLocal dao;

@EJB
private DtoAssembler dtoAssembler;

@EJB
private OutputRequesterBean outputRequester;

/**
 * Gibt die aktuelle Session zurück
 * @param sessionId
 * @return session
 * @throws NoSessionException wenn keine Session vorhanden ist
 */
private Session getSession(int sessionId) throws NoSessionException {
	Session session = dao.findSessionById(sessionId);
	if (session==null)
		throw new NoSessionException("Bitte zunächst ein Login durchführen.");
	else
		return session;
	}

/**
 * Methode zum registrieren eines neuen Accounts
 * @param userName Benutzername
 * @param password Passwort des Benutzers
 * @param email E-Mail des Benutzers
 * @return UserLoginResponse wird bei erfolgreichem registrieren zurückgegeben
 * @throws InvalidRegisterException wird geworfen bei fehlerhaftem registrieren, da Benutzername oder E-Mail Adresse bereits vergeben ist
 */
public UserLoginResponse registerNewAccount(String userName, String password, String email) {
	UserLoginResponse response = new UserLoginResponse();
	try {
		Account user = dao.createAccount(userName, password, email);		
		if (user != null) {
			logger.info("Registrierung von \"" + userName + "\" erfolgreich. ");
			response.setAccount(this.dtoAssembler.makeDTO(user));
			response.setSessionId(0);
		}
		else {
			logger.info("Registrieren fehlgeschlagen, da der Username oder die Emailadresse bereits existiert."
					  + " username=" + userName);
			throw new InvalidRegisterException("Registrieren fehlgeschlagen, da der Username oder die Emailadresse "
					  + "bereits existiert. username=" + userName);
		}
	}
	catch (DebtCheckException e) {
		response.setReturnCode(e.getErrorCode());
		response.setMessage(e.getMessage());
	}		
	return response;		
}

/**
 * Methode für den Login eines bestehenden Accounts
 * @param email E-Mail des Benutzers
 * @param password Password des Benutzers
 * @return UserLoginResponse wird bei erfolgreichem login zurückgegeben
 * @throws InvalidLoginException wird geworfen bei fehlerhaftem login, da E-Mail oder Password des Benutzers falsch sind
 */
public UserLoginResponse login(String email, String password) {
	UserLoginResponse response = new UserLoginResponse();
	try {
		Account user = this.dao.findAccountByEmail(email);
		if (user != null && user.getPassword().equals(password)) {
			int sessionId = dao.createSession(user);
			response.setSessionId(sessionId);
			response.setAccount(this.dtoAssembler.makeDTO(user));
			logger.info("Login erfolgreich.");
		}
		else {
			logger.info("Login fehlgeschlagen, da Email unbekannt oder Passwort falsch. email=" + email);
			throw new InvalidLoginException("Login fehlgeschlagen, da Email unbekannt oder Passwort falsch. email=" + email);
		}
	}
	catch (DebtCheckException e) {
		response.setReturnCode(e.getErrorCode());
		response.setMessage(e.getMessage());
	}
	return response;
	}

/**
 * Methode zum ausloggen eines eingeloggten Accounts
 * @param sessionId die sessionId der aktuellen Session des Benutzers
 * @return ReturnCodeResponse mit einem ReturnCode der angibt ob alles fehlerfrei durchgelaufen ist oder einen Fehlercode zurückgibt
 */
public ReturnCodeResponse logout(int sessionId) {
	dao.closeSession(sessionId);
	ReturnCodeResponse response = new ReturnCodeResponse();
	logger.info("Logout successful.");
	return response;
	}

/**
 * Methode um eine Liste mit allen Schulden des eingeloggten Benutzers wiederzugeben
 * @param sessionId die sessionId der aktuellen Session des Benutzers
 * @return DebtListResponse gibt eine Liste aller Schulden zurück
 * @throws NoListException wird geworfen wenn keine Session oder keine Liste vorhanden ist
 */
public DebtListResponse getMyDebts(int sessionId) {
	DebtListResponse response = new DebtListResponse();
	try {
		Session session = getSession(sessionId);
		List<Debt> accountList = session.getUser().getDebts();
		if(session != null && accountList != null){
		response.setDebtList(dtoAssembler.makeDebtDTO(accountList));
		logger.info("Ergebnis von getMyAccounts(): "+accountList);
		}
		else {
			logger.info("Keine Session oder keine accountList vorhanden");
			throw new NoListException("Keine Session oder keine accountList vorhanden");
		}
	}
	catch (DebtCheckException e) {
		response.setReturnCode(e.getErrorCode());
		response.setMessage(e.getMessage());
	}
	return response;
}

/**
 * Methode um eine Liste mit allen Forderungen des eingeloggten Benutzers wiederzugeben
 * @param sessionId die sessionId der aktuellen Session des Benutzers
 * @return DebtListResponse gibt eine Liste aller Forderungen zurück
 * @throws NoListException wird geworfen wenn keine Session oder keine Liste vorhanden ist
 */
public DebtListResponse getMyClaims(int sessionId) {
	DebtListResponse response = new DebtListResponse();
	try {
		Session session = getSession(sessionId);
		List<Debt> accountList = session.getUser().getClaims();
		if(session != null && accountList != null){
		response.setDebtList(dtoAssembler.makeDebtDTO(accountList));
		logger.info("Ergebnis von getMyClaims(): "+accountList);		
	}
	else {
		logger.info("Keine Session oder keine accountList vorhanden");
		throw new NoListException("Keine Session oder keine accountList vorhanden");
		}
	}
	catch (DebtCheckException e) {
		response.setReturnCode(e.getErrorCode());
		response.setMessage(e.getMessage());
	}
	return response;
}

/**
 * Methode um eine neue Schuld für den Benutzer hinzuzufügen
 * @param sessionId die sessionId der aktuellen Session des Benutzers
 * @param debtorAccount der Benutzername des Schuldner Accounts
 * @param amount die Höhe der Schuld
 * @param reason der Grund der Schuld
 * @return AddNewDebtResponse wird mit den aufgelistenen Eingaben der erstellten Schuld zurückgegeben
 * @throws DebtException wird geworfen wenn der Schuldner nicht vorhanden ist
 */
public AddNewDebtResponsee addNewDebt (int sessionId, String debtorAccount, BigDecimal amount, String reason){
	AddNewDebtResponsee response = new AddNewDebtResponsee();
	try {
		Session session = getSession(sessionId);
		Account creditor = session.getUser();
		Account debtor = this.dao.findAccountByName(debtorAccount);
		Debt debt = dao.createDebt(debtor, creditor, amount, reason);
		if (creditor!=null && debtor!=null) {
			creditor.addNewClaim(debt);
			debtor.addNewDebt(debt);
			response.setNewAmount(debt.getAmount());
			response.setDebt(this.dtoAssembler.makeDTO(debt));
			String message = creditor.getUserName() + " has added a debt for you!";
			logger.info(message);
			outputRequester.debtAddNotification(message);
		}
		else {
			logger.info("Kein Schuldner und/oder Gläubiger vorhanden");
			throw new DebtException("Kein Schuldner und/oder Gläubiger vorhanden");
			}
	}
	catch (DebtCheckException e) {
		response.setReturnCode(e.getErrorCode());
		response.setMessage(e.getMessage());
	}
	return response;
	}


/**
 * Methode um eine bestehende Schuld zurückzuzahlen
 * @param sessionId die sessionId der aktuellen Session des Benutzers
 * @param creditorAccount der Benutzername des Gläubiger Accounts
 * @param amount die Höhe des zurückgezahlten Betrags
 * @param id die ID der Schuld die zurückgezahlt wird
 * @return PayDebtResponse wird mit den aufgelistenen Eingaben der gezahlten Schuld zurückgegeben
 * @throws DebtException wird geworfen wenn der Schuldner nicht vorhanden ist
 */
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
				this.dao.removeDebt(id);
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
		else {
			logger.info("Kein Schuldner und/oder Gläubiger vorhanden");
			throw new PayDebtException("Kein Schuldner und/oder Gläubiger vorhanden");			
		}
	}
		catch (DebtCheckException e) {
			response.setReturnCode(e.getErrorCode());
			response.setMessage(e.getMessage());
		}
			return response;
	}
}
