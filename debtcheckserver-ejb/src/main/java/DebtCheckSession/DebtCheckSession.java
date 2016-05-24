package DebtCheckSession;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import account.Account;


public class DebtCheckSession {
	
	@Id @GeneratedValue
	private int id;
	@ManyToOne
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
