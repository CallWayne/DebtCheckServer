package de.debtcheck.dto;

import java.util.List;

/**
 * Response der Forderungsliste
 * 
 * @author Edgar Seibel
 * @author Josua Suren
 *
 */
public class ClaimListResponse extends ReturnCodeResponse {

	/**
	 * Attribute
	 */
	private static final long serialVersionUID = -5754928488884226775L;

	private List<ClaimTO> claimList;
	
	/**
	 * leerer Konstruktor
	 */
	public ClaimListResponse() {
	}

	/**
	 * Methode zur Rückgabe der Forderungsliste
	 * @return Forderungsliste
	 */
	public List<ClaimTO> getDebtList() {
		return claimList;
	}

	/**
	 *  Methode zum Setzen der Forderungsliste
	 * @param claimList Forderungsliste
	 */
	public void setClaimList(List<ClaimTO> claimList) {
		this.claimList = claimList;
	}

}