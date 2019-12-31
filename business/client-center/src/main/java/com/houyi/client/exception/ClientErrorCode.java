package com.houyi.client.exception;
/**
 * 客户端中心 错误码字典
 * @author AB051788
 *
 */
public interface ClientErrorCode {
	
	/**
	 * 默认错误编码
	 */
	String CLIENT_DEF = "5000";
	
	/**
	 * 参数相关错误码
	 */
	String PARAM_CODE = "5000_01";
	
	/**
	 * 业务相关错误码
	 */
	String BIZ_CODE = "5000_02";
	
	/**
	 * 数据库相关错误码
	 */
	String SQL_CODE = "5000_03";
	
	/**
	 * 系统相关错误码
	 */
	String SYS_CODE = "5000_04";
}
