package com.houyi.user.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houyi.db.mapper.SuperMapper;

import com.houyi.common.model.SysRole;
import org.apache.ibatis.annotations.Param;

/**
* @Author: houyi
 * 角色
 */
public interface SysRoleMapper extends SuperMapper<SysRole> {
	List<SysRole> findList(Page<SysRole> page, @Param("r") Map<String, Object> params);
}
