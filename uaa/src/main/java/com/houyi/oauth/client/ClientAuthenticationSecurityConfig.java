package com.houyi.oauth.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.stereotype.Component;

import com.houyi.oauth.service.ZltUserDetailsService;
import com.houyi.oauth.service.impl.RedisClientDetailsService;
@Component
public class ClientAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private ZltUserDetailsService userDetailsService;

    @Autowired(required = true)
    private RedisClientDetailsService redisClientDetailsService;

    @Override
    public void configure(HttpSecurity http) {
        //mobile provider
    	ClientAuthenticationProvider provider = new ClientAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setRedisClientDetailsService(redisClientDetailsService);
        http.authenticationProvider(provider);
    }
}
