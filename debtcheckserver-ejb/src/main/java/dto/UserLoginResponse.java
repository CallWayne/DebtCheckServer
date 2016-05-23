package dto;


public class UserLoginResponse extends ReturnCodeResponse {

	private static final long serialVersionUID = -3173158310918408228L;

	private int sessionId;
	private AccountTO account;
	
	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public AccountTO getAccount() {
		return this.account;
	}

	public void setAccount(AccountTO account) {
		this.account = account;
	}

}
