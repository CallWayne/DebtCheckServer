package DebtCheckSession;

import java.util.Date;

import account.Account;


public class DebtCheckSession {
	
	private int id;
	private Account user;
	private Date creationTime;

	
	public DebtCheckSession(Account user) {
		this.user = user;
		this.creationTime = new Date();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public Date getCreationTime() {
		return creationTime;
	}

}
