package de.debtcheck.dto;

import java.util.List;


public class ClaimListResponse extends ReturnCodeResponse {

	private static final long serialVersionUID = -5754928488884226775L;

	private List<ClaimTO> claimList;
	
	public ClaimListResponse() {
	}

	public List<ClaimTO> getDebtList() {
		return claimList;
	}

	public void setClaimList(List<ClaimTO> claimList) {
		this.claimList = claimList;
	}

}