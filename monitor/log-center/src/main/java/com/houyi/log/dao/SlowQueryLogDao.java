package com.houyi.log.dao;

import com.houyi.log.model.SlowQueryLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @Author: houyi
 */
@Component
public interface SlowQueryLogDao extends ElasticsearchRepository<SlowQueryLog, String> {

}