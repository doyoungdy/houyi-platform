package com.houyi.client.exception;

/**
 * 参数为空或错误异常
 *
 */
public class ParamException extends Exception{
	private static final long serialVersionUID = 1L;

	private String errorCode = ClientErrorCode.PARAM_CODE;
	public ParamException() {
		super("参数错误");
	}

	public ParamException(String message) {
		super(message);
	}
    
	/**
	 * 可字定义错误码
	 * @param code
	 * @param message
	 */
	public ParamException(String code,String message) {
		super(message);
		this.errorCode = code;
	}

	public String getErrorCode() {
		return errorCode;
	}	
}
