package de.debtcheck.dto;

import java.math.BigDecimal;



public class AddNewDebtResponsee extends ReturnCodeResponse {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DebtTO debt;
	private BigDecimal newAmount;
	
	public AddNewDebtResponsee() {
	}

	public BigDecimal getNewAmount() {
		return newAmount;
	}

	public void setNewAmount(BigDecimal newAmount) {
		this.newAmount = newAmount;
	}
	
	public DebtTO getDebt() {
		return this.debt;
	}

	public void setDebt(DebtTO debt) {
		this.debt = debt;
	}

}
