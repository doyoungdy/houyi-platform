package com.houyi.client.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.houyi.common.model.client.Client;
import com.houyi.db.mapper.SuperMapper;
/**
 * 客户端的增加修改操作
 * @author houyi
 *
 */
@Mapper
public interface ClientMapper extends SuperMapper<Client>{

}
