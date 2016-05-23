package dto;

import java.math.BigDecimal;



public class addNewDebtResponse extends ReturnCodeResponse {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal newAmount;
	
	public addNewDebtResponse() {
	}

	public BigDecimal getNewAmount() {
		return newAmount;
	}

	public void setNewAmount(BigDecimal newAmount) {
		this.newAmount = newAmount;
	}

}
