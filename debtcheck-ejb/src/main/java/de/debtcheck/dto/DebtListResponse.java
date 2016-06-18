package de.debtcheck.dto;

import java.util.List;

/**
 * Response der Schuldenliste
 * 
 * @author Edgar Seibel
 * @author Josua Suren
 *
 */
public class DebtListResponse extends ReturnCodeResponse {

	private static final long serialVersionUID = -5754928488884226775L;

	private List<DebtTO> debtList;
	
	public DebtListResponse() {
	}

	/**
	 * Methode zur Rückgabe der Schuldenliste
	 * @return Schuldenliste
	 */
	public List<DebtTO> getDebtList() {
		return debtList;
	}

	/**
	 * Methode zum Setzen der Schuldenliste
	 * @param debtList Schuldenliste
	 */
	public void setDebtList(List<DebtTO> debtList) {
		this.debtList = debtList;
	}

}