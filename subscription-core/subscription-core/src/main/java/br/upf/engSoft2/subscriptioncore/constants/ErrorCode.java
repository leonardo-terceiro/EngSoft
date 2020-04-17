package br.upf.engSoft2.subscriptioncore.constants;

public enum ErrorCode {

	NOT_FOUND("404","NOT FOUND");

	private final String code;
	private final String message;
	
	private ErrorCode (String code, String message) {
		this.code = code;
		this.message = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
}
