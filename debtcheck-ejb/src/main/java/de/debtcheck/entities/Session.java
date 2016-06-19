package de.debtcheck.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Klasse für eine Session
 * 
 * @author Josua Suren
 * @author Edgar Seibel
 *
 */
@Entity
public class Session {

	/**
	 * Attribute
	 */
	@Id @GeneratedValue
	private int id;
	@ManyToOne
	private Account user;
	private Date creationTime;
	
	/**
	 * leerer Konstruktor
	 */
	public Session() {	}
	
	/**
	 * Konstruktor
	 * 
	 * @param user Accountobjekt des Users
	 */
	public Session(Account user) {
		this.user = user;
		this.creationTime = new Date();
	}

	/**
	 * Methode zum Abfragen der SessionID
	 * @return SessionID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Methode zum Setzen der SessionID
	 * @param id SessionID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Methode zum Abfragen des Benutzers zur Session
	 * @return Accountobjekt des Benutzers
	 */
	public Account getUser() {
		return user;
	}

	/**
	 * Methode zum Setzen des Benutzers zur Session
	 * @param user Accountobjekt des Benutzers
	 */
	public void setUser(Account user) {
		this.user = user;
	}

	/**
	 * Methode zur Abfrage des Erstellungszeitpunkts der Session
	 * @return Erstellungszeitpunkt
	 */
	public Date getCreationTime() {
		return creationTime;
	}

}
