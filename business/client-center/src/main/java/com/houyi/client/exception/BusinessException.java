package com.houyi.client.exception;
/**
 * 业务相关错误异常
 *
 */
public class BusinessException extends Exception{
	private static final long serialVersionUID = 1L;

	private String errorCode = ClientErrorCode.BIZ_CODE;
	public BusinessException() {
		super("业务出错");
	}

	public BusinessException(String message) {
		super(message);
	}
    
	/**
	 * 可字定义错误码
	 * @param code
	 * @param message
	 */
	public BusinessException(String code,String message) {
		super(message);
		this.errorCode = code;
	}

	public String getErrorCode() {
		return errorCode;
	}	
}
