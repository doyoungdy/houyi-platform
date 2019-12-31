package com.houyi.log.dao;

import com.houyi.log.model.SysLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @Author: houyi
 */
@Component
public interface SysLogDao extends ElasticsearchRepository<SysLog, String> {

}