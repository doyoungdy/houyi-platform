/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.houyi.sentinel.dashboard.rule;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import com.houyi.sentinel.dashboard.config.NacosConfigProperties;
import com.houyi.sentinel.dashboard.config.NacosConfigConstant;

/**
 * @author Eric Zhao
 * @since 1.4.0
 */
@Component("degradeRuleNacosPublisher")
public class DegradeRuleNacosPublisher implements DynamicRulePublisher<List<DegradeRuleEntity>> {

    @Autowired
    private ConfigService configService;

	@Autowired
	private NacosConfigProperties nacosConfigProperties;

    @Override
    public void publish(String app, List<DegradeRuleEntity> rules) throws Exception {
    	   AssertUtil.notEmpty(app, "app name cannot be empty");
           if (rules == null) {
               return;
           }
           configService.publishConfig(app + NacosConfigConstant.DEGRADE_DATA_ID_POSTFIX,
           		nacosConfigProperties.getGroupId(), 
               JSON.toJSONString(rules.stream().map(DegradeRuleEntity::toDegradeRule).collect(Collectors.toList())));
    }
}
