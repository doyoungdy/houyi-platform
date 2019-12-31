package com.houyi.user.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.houyi.db.mapper.SuperMapper;
import com.houyi.common.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户表 Mapper 接口
 *
 * @Author: houyi
 * @data 2018-10-29
 */
public interface SysUserMapper extends SuperMapper<SysUser> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<SysUser> findList(Page<SysUser> page, @Param("u") Map<String, Object> params);
}
