package com.houyi.client.service;

import java.util.List;

import com.houyi.client.exception.BusinessException;
import com.houyi.client.exception.ParamException;
import com.houyi.client.exception.SqlException;
import com.houyi.common.model.Result;
import com.houyi.common.model.client.OpenApi;
import com.houyi.common.service.ISuperService;

/**
 * @Author: houyi
 */
public interface IOpenApiService extends ISuperService<OpenApi>{
	/**
	 * 新增/修改接口信息
	 * @param openapi
	 * @return
	 * @throws ParamException 
	 * @throws SqlException 
	 */
	boolean insertOrUpdate(OpenApi openapi) throws ParamException, SqlException;
	
	/**
	 * 修改接口状态
	 * @param openapi
	 * @return
	 * @throws ParamException 
	 * @throws BusinessException 
	 */
	Result<OpenApi> updateStatus(OpenApi openapi) throws ParamException, BusinessException;
	
	/**
	 * 查询全部的接口
	 * @param openapi
	 * @return
	 */
	List<OpenApi> findAllApis(OpenApi openapi);
	
	
	

}
