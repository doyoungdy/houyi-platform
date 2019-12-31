package com.houyi.client.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.houyi.common.model.client.ClientPaas;
import com.houyi.db.mapper.SuperMapper;

/**
 * 平台微服务
 * @Author: houyi
 * 
 */
@Mapper
public interface PaasMapper extends SuperMapper<ClientPaas>{

}
