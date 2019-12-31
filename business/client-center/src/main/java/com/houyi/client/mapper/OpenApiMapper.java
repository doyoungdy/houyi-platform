package com.houyi.client.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.houyi.common.model.client.OpenApi;
import com.houyi.db.mapper.SuperMapper;

/**
 * 开放api
 * @Author: houyi
 * 
 */
@Mapper
public interface OpenApiMapper extends SuperMapper<OpenApi>{

	/**
	 * 通过客户端ID查询可访问的apis
	 * @param clientId
	 * @return
	 */
	List<OpenApi> findApisByClientId(@Param("clientId") Long clientId);
	
	/**
	 * 通过客户端应用标识查询可访问的apis
	 * @param client_id 应用标识
	 * @return
	 */
	List<OpenApi> findApisByClient_Id(@Param("client_id") String client_id);
}
