package de.debtcheck.dto;

import java.util.Set;


public class FriendListResponse extends ReturnCodeResponse {

	private static final long serialVersionUID = -5754928488884226775L;

	private Set<AccountTO> accountList;
	
	public FriendListResponse() {
	}

	public Set<AccountTO> getAccountList() {
		return accountList;
	}

	public void setFriendList(Set<AccountTO> accountList) {
		this.accountList = accountList;
	}

}