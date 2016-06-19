package de.debtcheck.dto;

import java.math.BigDecimal;


/**
 * Response beim Anlegen einer neuen Schuld
 * 
 * @author Edgar Seibel
 * @author Josua Suren
 *
 */
public class AddNewDebtResponsee extends ReturnCodeResponse {


	/**
	 * Attribute
	 */
	private static final long serialVersionUID = 1L;
	
	private DebtTO debt;
	private BigDecimal newAmount;
	
	/**
	 * leerer Konstruktor
	 */
	public AddNewDebtResponsee() {
	}

	/**
	 * Methode zum Abfragen des neuen Schuldbetrags
	 * @return neuer Schuldbetrag
	 */
	public BigDecimal getNewAmount() {
		return newAmount;
	}

	/**
	 * Methode zum Setzen des neuen Schuldbetrags
	 * @param newAmount neuer Schuldbetrag
	 */
	public void setNewAmount(BigDecimal newAmount) {
		this.newAmount = newAmount;
	}
	
	/**
	 * Methode zur Rückgabe der Schuld
	 * @return
	 */
	public DebtTO getDebt() {
		return this.debt;
	}

	/**
	 * Methode zum Setzen der Schuld
	 * @param debt
	 */
	public void setDebt(DebtTO debt) {
		this.debt = debt;
	}

}
