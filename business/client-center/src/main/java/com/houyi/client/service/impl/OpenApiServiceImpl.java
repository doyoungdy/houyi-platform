package com.houyi.client.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houyi.client.exception.BusinessException;
import com.houyi.client.exception.ParamException;
import com.houyi.client.exception.SqlException;
import com.houyi.client.mapper.OpenApiMapper;
import com.houyi.client.service.IOpenApiService;
import com.houyi.common.model.Result;
import com.houyi.common.model.client.OpenApi;
import com.houyi.common.service.impl.SuperServiceImpl;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: houyi
 */
@Service
@Slf4j
public class OpenApiServiceImpl extends SuperServiceImpl<OpenApiMapper, OpenApi> implements IOpenApiService{
	
	@Resource
	private OpenApiMapper openApiMapper;
	

	@Override
	public boolean insertOrUpdate(OpenApi openapi) throws ParamException, SqlException {
		if(openapi == null){
			throw new ParamException("参数缺失");
		}
		if(openapi != null && openapi.getId() == null){
			openapi.setCreateTime(new Date());
			openapi.setUpdateTime(new Date());
			if(!StrUtil.isNotBlank(openapi.getServiceCode())){
				throw new ParamException("serviceCode参数缺失");
			}
			if(!StrUtil.isNotBlank(openapi.getApiPath())){
				throw new ParamException("apiPath参数缺失");
			}
			if(!StrUtil.isNotBlank(openapi.getApiName())){
				throw new ParamException("apiName参数缺失");
			}
		}
		try{
			return super.saveOrUpdate(openapi);
		}catch (Exception e) {
			throw new SqlException(e);
		}
		
	}

	@Override
	public Result<OpenApi> updateStatus(OpenApi openapi) throws ParamException, BusinessException {
		if(openapi == null){
			throw new ParamException("参数缺失");
		}
		if(openapi.getId()== null){
			throw new ParamException("id参数缺失");
		}
		if(openapi.getStatus() == null){
			throw new ParamException("status参数缺失");
		}
		
		OpenApi dbApi = baseMapper.selectById(openapi.getId());
        if (dbApi == null) {
			throw new BusinessException("id不存在");
        }
        dbApi.setStatus(openapi.getStatus());
        dbApi.setUpdateTime(new Date());

        int i = baseMapper.updateById(dbApi);
        log.info("修改openapi：{}", dbApi);

        return i > 0 ? Result.succeed(dbApi, "更新成功") : Result.failed("更新失败");
	}

	@Override
	public List<OpenApi> findAllApis(OpenApi openapi) {
		return baseMapper.selectList(
                new QueryWrapper<OpenApi>().orderByAsc("sort").between("status", 0, 1)
        );
	}
	
	

}
