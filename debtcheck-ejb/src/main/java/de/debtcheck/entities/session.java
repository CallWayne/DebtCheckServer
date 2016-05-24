package de.debtcheck.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class session {

	@Id @GeneratedValue
	private int id;
	@ManyToOne
	private account user;
	private Date creationTime;
	
	public session() {	}
	
	
	public session(account user) {
		this.user = user;
		this.creationTime = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public account getUser() {
		return user;
	}

	public void setUser(account user) {
		this.user = user;
	}

	public Date getCreationTime() {
		return creationTime;
	}

}
