package de.debtcheck.dto;

import java.util.List;


public class DebtListResponse extends ReturnCodeResponse {

	private static final long serialVersionUID = -5754928488884226775L;

	private List<DebtTO> debtList;
	
	public DebtListResponse() {
	}

	public List<DebtTO> getDebtList() {
		return debtList;
	}

	public void setDebtList(List<DebtTO> debtList) {
		this.debtList = debtList;
	}

}