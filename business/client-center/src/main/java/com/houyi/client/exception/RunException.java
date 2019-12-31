package com.houyi.client.exception;

/**
 * 运行时的异常
 *
 */
public class RunException extends Exception{
	private static final long serialVersionUID = 1L;

	private String errorCode = ClientErrorCode.SYS_CODE;
	public RunException() {
		super("运行错误");
	}

	public RunException(String message) {
		super(message);
	}
    
	public RunException(Throwable cause) {
		super(cause);
	}
	
	public RunException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public RunException(String code,String message, Throwable cause) {
		super(message, cause);
		this.errorCode = code;
	}
	
	/**
	 * 可字定义错误码
	 * @param code
	 * @param message
	 */
	public RunException(String code,String message) {
		super(message);
		this.errorCode = code;
	}

	public String getErrorCode() {
		return errorCode;
	}	
}