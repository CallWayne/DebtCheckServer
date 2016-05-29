package de.debtcheck.dto;

import java.math.BigDecimal;



public class PayDebtResponsee extends ReturnCodeResponse {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal newAmount;
	
	public PayDebtResponsee() {
	}

	public BigDecimal getNewAmount() {
		return newAmount;
	}

	public void setNewAmount(BigDecimal newAmount) {
		this.newAmount = newAmount;
	}

}
