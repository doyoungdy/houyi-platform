package com.houyi.gateway.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.houyi.common.model.SysMenu;
import com.houyi.common.model.client.OpenApi;
import com.houyi.gateway.feign.ClientApiService;
import com.houyi.gateway.feign.MenuService;
import com.houyi.oauth2.common.service.impl.DefaultPermissionServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 请求权限判断service
 *
 * @Author: houyi
 * @date 2018/10/28
 */
@Slf4j
@Service("permissionService")
public class PermissionServiceImpl extends DefaultPermissionServiceImpl {
    @Resource
    private MenuService menuService;
    
    @Resource
    private ClientApiService clientApiService;

    @Override
    public List<SysMenu> findMenuByRoleCodes(String roleCodes) {
        return menuService.findByRoleCodes(roleCodes);
    }

	@Override
	public List<OpenApi> findApisByClientId(String clientId) {
		return clientApiService.findApisByClientId(clientId);
	}
}
