package com.houyi.client.service;

import java.util.List;
import java.util.Map;

import com.houyi.client.exception.ParamException;
import com.houyi.client.exception.SqlException;
import com.houyi.common.model.Result;
import com.houyi.common.model.client.ClientPaas;
import com.houyi.common.service.ISuperService;

/**
 * @Author: houyi
 */
public interface IPaasService extends ISuperService<ClientPaas> {
	
	/**
	 * 新增/修改
	 * @param params
	 * @return
	 * @throws ParamException 
	 * @throws SqlException 
	 */
	boolean insertOrUpdate(ClientPaas entity) throws ParamException, SqlException;
	
	/**
	 * 状态变更
	 * @param params
	 * @return
	 */
	Result<Object> updateStastus(Map<String, Object> params);
	
	/**
	 * 查询所有服务
	 */
	List<ClientPaas> findAll();

}
