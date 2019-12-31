package com.houyi.common.model.client;



import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.houyi.common.model.SuperEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 平台提供对外接口的服务
 * @Author houyi 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("client_paas")
public class ClientPaas extends SuperEntity<ClientPaas> {
	private static final long serialVersionUID = -5849952141273960213L;
	
	/**
	 * 状态(0:不可用,1:可用,-1:删除)
	 */
	private Integer status;
	
	/**
	 * 服务标识
	 */
	private String serviceCode;
	
	/**
	 * 服务名称
	 */
	private String serviceName;
	
	/**
	 * 服务详细描述
	 */
	private String serviceDesc;
	
	/**
	 * 显示排序
	 */
	private Long sort;
	
	/**
	 * 服务下全部接口
	 */
	@TableField(exist = false)
	private List<OpenApi> apis;
}
