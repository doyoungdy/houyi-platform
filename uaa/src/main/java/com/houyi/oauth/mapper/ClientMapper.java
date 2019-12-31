package com.houyi.oauth.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houyi.common.model.client.Client;
import com.houyi.db.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: houyi
 */
public interface ClientMapper extends SuperMapper<Client> {
    List<Client> findList(Page<Client> page, @Param("params") Map<String, Object> params );
}
