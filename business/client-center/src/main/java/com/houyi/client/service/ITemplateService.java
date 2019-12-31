package com.houyi.client.service;

import java.util.Set;

import com.houyi.client.exception.BusinessException;
import com.houyi.client.exception.ParamException;
import com.houyi.client.exception.SqlException;
import com.houyi.common.model.Result;
import com.houyi.common.model.client.OpenTemplate;
import com.houyi.common.service.ISuperService;

public interface ITemplateService extends ISuperService<OpenTemplate>{
	/**
	 * 新增/修改接口信息
	 * @param openapi
	 * @return
	 * @throws ParamException 
	 * @throws SqlException 
	 */
	boolean insertOrUpdate(OpenTemplate template) throws ParamException, SqlException;
	
	/**
	 * 修改接口状态
	 * @param openapi
	 * @return
	 * @throws ParamException 
	 * @throws BusinessException 
	 */
	Result<OpenTemplate> updateStatus(OpenTemplate template) throws ParamException, BusinessException;
	
	/**
	 * 给模板分配接口
	 * @param roleId
	 * @param menuIds
	 * @throws ParamException 
	 */
	void grantApisToTemplate(Long templateId, Set<Long> apiIds) throws ParamException;
	
	

}
