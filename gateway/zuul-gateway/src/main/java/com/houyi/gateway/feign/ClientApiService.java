package com.houyi.gateway.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.houyi.common.constant.ServiceNameConstants;
import com.houyi.common.model.client.OpenApi;
import com.houyi.gateway.feign.fallback.ClientApiServiceFallbackFactory;

/**
 * @Author: houyi
 */
@FeignClient(name = ServiceNameConstants.CLIENT_SERVICE, fallbackFactory = ClientApiServiceFallbackFactory.class, decode404 = true)
public interface ClientApiService {
	/**
	 * 角色菜单列表
	 * @param roleCodes
	 */
	@GetMapping(value = "/client/findApis/{client_id}")
	List<OpenApi> findApisByClientId(@PathVariable("client_id") String client_id);
}
