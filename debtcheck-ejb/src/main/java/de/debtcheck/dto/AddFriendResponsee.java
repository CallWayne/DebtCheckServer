package de.debtcheck.dto;

public class AddFriendResponsee extends ReturnCodeResponse {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String friendName;
	
	public AddFriendResponsee(){}
	
	public void setFriendName(String name){
		this.friendName = name;
	}
	
	public String getFriendName(){
		return friendName;
	}
	
	

}
