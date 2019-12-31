package com.houyi.oauth.client;

import java.util.Map;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.houyi.common.constant.MerchantConstant;
import com.houyi.oauth.service.ZltUserDetailsService;
import com.houyi.oauth.service.impl.RedisClientDetailsService;
import com.houyi.oauth2.common.token.ClientAuthenticationToken;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: houyi
 */
@Setter
@Slf4j
public class ClientAuthenticationProvider implements AuthenticationProvider{

    private ZltUserDetailsService userDetailsService;
    
    private RedisClientDetailsService redisClientDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) {
    	ClientAuthenticationToken authenticationToken = (ClientAuthenticationToken) authentication;
    	//通过clientId在Redis取出对应Client,在其resourceIds中拿出一个username,商户类型的client默认只有一个username
        String clientId = (String) authenticationToken.getPrincipal();
        ClientDetails clientDetails = redisClientDetailsService.loadClientByClientId(clientId);
        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在");
        }
        Map<String, Object> infos = clientDetails.getAdditionalInformation();
        if (infos == null || infos.isEmpty()) {
            throw new UnapprovedClientAuthenticationException("客户端类型错误-无有效用户");
        }
        UserDetails user = null;
        try{
            //通过username拿到对应的用户信息
            user = userDetailsService.loadUserByUserId(Long.parseLong(infos.get(MerchantConstant.CLIENT_MERCHANTID_KEY).toString()));	
        }catch (DisabledException e) {
        	log.error("exceptionHandler-error:", e);
        	throw new InternalAuthenticationServiceException(e.getMessage());
		}catch (Exception e) {
        	log.error("exceptionHandler-error:", e);
        	throw new InternalAuthenticationServiceException("查询商户信息失败");
		}
        if (user == null) {
            throw new InternalAuthenticationServiceException("用户不存在");
        }
        if(!user.isEnabled()){
        	throw new InternalAuthenticationServiceException("用户已失效");
        }
        ClientAuthenticationToken authenticationResult = new ClientAuthenticationToken(user, user.getAuthorities());
        authenticationResult.setDetails(MerchantConstant.AUTHEN_CLIENT_DETAIL_PRE+clientId);
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ClientAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
