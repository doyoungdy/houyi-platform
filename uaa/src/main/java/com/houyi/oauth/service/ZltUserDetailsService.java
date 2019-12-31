package com.houyi.oauth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author: houyi
 * @date 2018/12/28
 */
public interface ZltUserDetailsService extends UserDetailsService {
    /**
     * 根据电话号码查询用户
     *
     * @param mobile
     * @return
     */
    UserDetails loadUserByMobile(String mobile);
    
    /**
     * 通过资源ID查询用户
     *
     * @param id
     * @return
     */
    UserDetails loadUserByUserId(Long id);
}
