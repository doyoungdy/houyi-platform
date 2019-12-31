package com.houyi.common.model.client;

import com.baomidou.mybatisplus.annotation.TableName;
import com.houyi.common.model.SuperEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 平台提供的对外接口
 * @Author houyi 
 *  
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("client_open_api")
public class OpenApi extends SuperEntity<OpenApi> {
	private static final long serialVersionUID = 427612247798759045L;

	/**
	 * 状态(0:不可用,1:可用,-1:删除)
	 */
	private Integer status;

	/**
	 * 所在服务
	 */
	private String serviceCode;

	/**
	 * 接口名称
	 */
	private String apiName;

	/**
	 * 接口path
	 */
	private String apiPath;

	/**
	 * 显示排序
	 */
	private Long sort;

}
