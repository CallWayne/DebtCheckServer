package dto;


public class ReturnCodeResponse extends DataTransferObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int CODE_OK = 0;
	
	private int returnCode;
	private String message;
	
	public ReturnCodeResponse() {
		this.returnCode = CODE_OK;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

