package de.debtcheck.dto;

import java.math.BigDecimal;


/**
 * Response beim Bezahlen der Schuld
 * 
 * @author Josua Suren
 * @author Edgar Seibel
 *
 */
public class PayDebtResponsee extends ReturnCodeResponse {


	/**
	 * Attribute
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal newAmount;
	
	/**
	 * leerer Konstruktor
	 */
	public PayDebtResponsee() {
	}

	/**
	 * Methode zum Abfragen der neuen Restschuld
	 * @return Restschuld
	 */
	public BigDecimal getNewAmount() {
		return newAmount;
	}

	/**
	 * Methode zum Setzen der Restschuld
	 * @param newAmount Restschuld
	 */
	public void setNewAmount(BigDecimal newAmount) {
		this.newAmount = newAmount;
	}

}
