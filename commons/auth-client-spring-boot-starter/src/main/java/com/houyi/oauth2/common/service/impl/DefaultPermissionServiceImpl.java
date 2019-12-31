package com.houyi.oauth2.common.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.AntPathMatcher;

import com.houyi.common.constant.CommonConstant;
import com.houyi.common.model.SysMenu;
import com.houyi.common.model.client.OpenApi;
import com.houyi.oauth2.common.properties.SecurityProperties;
import com.houyi.oauth2.common.service.IPermissionService;
import com.houyi.oauth2.common.util.AuthUtils;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 请求权限判断service
 *
 * @Author: houyi
 * @date 2018/10/28
 */
@Slf4j
public abstract class DefaultPermissionServiceImpl implements IPermissionService {

    @Autowired
    private SecurityProperties securityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * 查询当前用户拥有的资源权限
     * @param roleCodes 角色code列表，多个以','隔开
     * @return
     */
    public abstract List<SysMenu> findMenuByRoleCodes(String roleCodes);

    /**
     * 查询当前商户对应的客户端可访问的api
     * @param clientId
     * @return
     */
    public abstract List<OpenApi> findApisByClientId(String clientId);
    
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
    	log.error("验证URL+=============================================");
        // 前端跨域OPTIONS请求预检放行 也可通过前端配置代理实现
        // 在这里放行具有一定风险,也可通过前端配置代理实现
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            //判断是否开启url权限验证
            if (!securityProperties.getAuth().isUrlEnabled()) {
                return true;
            }
            //超级管理员admin不需认证
            String username = AuthUtils.getUsername(authentication);
            if (CommonConstant.ADMIN_USER_NAME.equals(username)) {
                return true;
            }

            //判断认证通过后，所有用户都能访问的url
            for (String path : securityProperties.getIgnore().getMenusPaths()) {
                if (antPathMatcher.match(path, request.getRequestURI())) {
                    return true;
                }
            }
            //用clientId表示是商户的客户端请求
            String clientId = AuthUtils.getClientId(authentication);
            if(clientId != null){
            	List<OpenApi> apis = findApisByClientId(clientId);
                for (OpenApi api : apis) {
                    if (StringUtils.isNotEmpty(api.getApiPath()) && antPathMatcher.match(api.getApiPath(), request.getRequestURI())) {
                        return true;
                    }
                }
            }else{
                List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
                if (CollectionUtil.isEmpty(grantedAuthorityList)) {
                    log.warn("角色列表为空：{}", authentication.getPrincipal());
                    return false;
                }

                String roleCodes = grantedAuthorityList.stream().map(SimpleGrantedAuthority::getAuthority).collect(Collectors.joining(", "));
                List<SysMenu> menuList = findMenuByRoleCodes(roleCodes);
                for (SysMenu menu : menuList) {
                    if (StringUtils.isNotEmpty(menu.getPath()) && StringUtils.isNotEmpty(menu.getPathMethod())
                            && antPathMatcher.match(menu.getPath(), request.getRequestURI())
                            && request.getMethod().equalsIgnoreCase(menu.getPathMethod())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
