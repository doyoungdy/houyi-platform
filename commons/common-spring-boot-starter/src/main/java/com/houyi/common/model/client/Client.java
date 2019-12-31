package com.houyi.common.model.client;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.houyi.common.model.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: houyi
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("oauth_client_details")
public class Client extends SuperEntity<Client> {
   private static final long serialVersionUID = -8185413579135897885L;
   /**
    * 应用标识
    */
   private String clientId;
   /**
    * 资源限定串(逗号分割)
    */
   private String resourceIds = "";
   /**
    * 应用密钥(bcyt) 加密
    */
   private String clientSecret;
   /**
    * 应用密钥(明文)
    */
   private String clientSecretStr;
   /**
    * 范围
    */
   private String scope = "all";
   /**
    * 5种oauth授权方式(authorization_code,password,refresh_token,client_credentials)
    */
   private String authorizedGrantTypes = "authorization_code,password,refresh_token,client_credentials";
   /**
    * 回调地址
    */
   private String webServerRedirectUri;
   /**
    * 权限
    */
   private String authorities = "";
   /**
    * access_token有效期
    */
   @TableField(value = "access_token_validity")
   private Integer accessTokenValiditySeconds = 18000;
   /**
    * refresh_token有效期
    */
   @TableField(value = "refresh_token_validity")
   private Integer refreshTokenValiditySeconds = 28800;
   /**
    * 附近信息
    */
   private String additionalInformation = "{}";
   /**
    * 是否自动授权 是-true
    */
   private String autoapprove = "true";
}
