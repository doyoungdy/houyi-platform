package com.houyi.client.exception;

public class SqlException extends Exception{
	private static final long serialVersionUID = 1L;

	private String errorCode = ClientErrorCode.SQL_CODE;
	
	public SqlException() {
		super("操作失败");
	}

	public SqlException(Throwable cause) {
		super("操作失败",cause);
	}
	
	public SqlException(String message) {
		super(message);
	}
	
	public SqlException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SqlException(String code,String message, Throwable cause) {
		super(message, cause);
		this.errorCode = code;
	}
	
	/**
	 * 可字定义错误码
	 * @param code
	 * @param message
	 */
	public SqlException(String code,String message) {
		super(message);
		this.errorCode = code;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
