package de.debtcheck.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Friend {
	
	private static int lastID = 0;
	
	@Id
	private int id;
	@Column(nullable=false)
	private String userName;
	@ManyToOne
	private Account owner;

	
	public Friend (){}
	
	public Friend (Account owner, String userName){
		this.id = lastID++;
		this.owner = owner;
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getOwner() {
		return owner;
	}

	public void setOwner(Account owner) {
		this.owner = owner;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
	
	

}
