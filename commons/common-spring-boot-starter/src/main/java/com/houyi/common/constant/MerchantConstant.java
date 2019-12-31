package com.houyi.common.constant;
/**
 * 与第三方商户相关的常量
 * @author AB051788
 *
 */
public interface MerchantConstant {
	
    /**
     * 客户端为商户类型时,在oauth_client_details表additional_information字段存商户(sys_user)id使用的Key
     * 多处使用,便于统一管理
     */
    String CLIENT_MERCHANTID_KEY = "merchantId";

    /**
     * 认证方式为客户端认证时,AuthenticationToken 的detail存的是客户端client_id,加此前缀区分
     * 在资源服务器需要使用client_id去验证取到对应可访问apis
     */
    String AUTHEN_CLIENT_DETAIL_PRE = "clientId:";

}
