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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.dashboard.util.RuleUtils;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.nacos.api.config.ConfigService;
import com.houyi.sentinel.dashboard.config.NacosConfigProperties;
import com.houyi.sentinel.dashboard.config.NacosConfigConstant;

/**
 * @author Eric Zhao
 * @since 1.4.0
 */
@Component("degradeRuleNacosProvider")
public class DegradeRuleNacosProvider implements DynamicRuleProvider<List<DegradeRuleEntity>> {

    @Autowired
    private ConfigService configService;


	@Autowired
	private NacosConfigProperties nacosConfigProperties;

    @Override
    public List<DegradeRuleEntity> getRules(String appName) throws Exception {
        String rulesStr = configService.getConfig(appName + NacosConfigConstant.DEGRADE_DATA_ID_POSTFIX,
        		nacosConfigProperties.getGroupId(), 3000);
        if (StringUtil.isEmpty(rulesStr)) {
            return new ArrayList<>();
        }
        List<DegradeRule> rules = RuleUtils.parseDegradeRule(rulesStr);

        if (rules != null) {
            return rules.stream().map(rule -> DegradeRuleEntity.fromDegradeRule(appName, nacosConfigProperties.getIp(), Integer.valueOf(nacosConfigProperties.getPort()), rule))
                .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
