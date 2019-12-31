package com.houyi.client.service;

import java.util.List;

import com.houyi.client.exception.BusinessException;
import com.houyi.client.exception.ParamException;
import com.houyi.client.exception.SqlException;
import com.houyi.client.model.ClientTemplate;
import com.houyi.common.model.client.Client;
import com.houyi.common.model.client.OpenApi;
import com.houyi.common.service.ISuperService;

public interface IClientService extends ISuperService<Client>{
	/**
	 * 保存更新客户端
	 * @param entity
	 * @return
	 * @throws ParamException 
	 * @throws SqlException 
	 * @throws BusinessException 
	 */
	Client insertOrUpdate(Client client) throws ParamException, SqlException, BusinessException;
	
	/**
	 * 客户端分配接口模板
	 * @param ct
	 * @throws ParamException
	 */
	boolean saveTemplate(ClientTemplate ct) throws ParamException;
	
	/**
	 * ID-查询客户端有权访问的接口
	 * @param clientId
	 * @return
	 * @throws ParamException 
	 * @throws SqlException 
	 * @throws BusinessException 
	 */
	List<OpenApi> findApis(Long clientId);
	
	/**
	 * 应用标识-查询客户端有权访问的接口
	 * @param client_id
	 * @return
	 * @throws ParamException 
	 * @throws SqlException 
	 * @throws BusinessException 
	 */
	List<OpenApi> findApis(String client_id);

}
