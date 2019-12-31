package com.houyi.user.service;

import java.util.Map;

import com.houyi.common.model.PageResult;
import com.houyi.common.model.Result;
import com.houyi.common.model.SysRole;
import com.houyi.common.service.ISuperService;

/**
* @Author: houyi
 */
public interface ISysRoleService extends ISuperService<SysRole> {
	void saveRole(SysRole sysRole);

	void deleteRole(Long id);

	/**
	 * 角色列表
	 * @param params
	 * @return
	 */
	PageResult<SysRole> findRoles(Map<String, Object> params);

	/**
	 * 新增或更新角色
	 * @param sysRole
	 * @return Result
	 */
	Result saveOrUpdateRole(SysRole sysRole);
}
