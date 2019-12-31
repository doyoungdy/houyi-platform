package com.houyi.common.model.client;

import java.util.Set;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.houyi.common.model.SuperEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @Description 平台开发接口模板
 * @Author houyi 
 *  
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("client_open_template")
public class OpenTemplate extends SuperEntity<OpenTemplate>{

	private static final long serialVersionUID = -4327400880111545339L;
	
	/**
	 * 状态(0:不可用,1:可用,-1:删除)
	 */
	private Integer status;

	/**
	 * 模板编码
	 */
	private String code;

	/**
	 * 模板名称
	 */
	private String name;
	
	/**
	 * 显示排序
	 */
	private Long sort;
	
	/**
	 * 拥有的接口权限
	 */
	@TableField(exist = false)
	private Set<Long> apiIds;


}
