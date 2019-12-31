package com.houyi.client.service.impl;

import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.houyi.client.exception.BusinessException;
import com.houyi.client.exception.ParamException;
import com.houyi.client.exception.SqlException;
import com.houyi.client.mapper.OpenTemplateMapper;
import com.houyi.client.mapper.TemplateApiMapper;
import com.houyi.client.service.ITemplateService;
import com.houyi.common.model.Result;
import com.houyi.common.model.client.OpenTemplate;
import com.houyi.common.service.impl.SuperServiceImpl;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: houyi
 */
@Service
@Slf4j
public class TemplateServiceImpl extends SuperServiceImpl<OpenTemplateMapper, OpenTemplate> implements ITemplateService{
	@Resource
	private OpenTemplateMapper openTemplateMapper;
	
	@Resource
	private TemplateApiMapper templateApiMapper;

	@Override
	public boolean insertOrUpdate(OpenTemplate template) throws ParamException, SqlException {
		if(template == null){
			throw new ParamException("参数缺失");
		}
		if(template != null && template.getId() == null){
			template.setCreateTime(new Date());
			template.setUpdateTime(new Date());
			if(!StrUtil.isNotBlank(template.getCode())){
				throw new ParamException("code参数缺失");
			}
			if(!StrUtil.isNotBlank(template.getName())){
				throw new ParamException("name参数缺失");
			}
		}
		try{
			return super.saveOrUpdate(template);
		}catch (Exception e) {
			throw new SqlException(e);
		}
	}

	@Override
	public Result<OpenTemplate> updateStatus(OpenTemplate template) throws ParamException, BusinessException {
		if(template == null){
			throw new ParamException("参数缺失");
		}
		if(template.getId()== null){
			throw new ParamException("id参数缺失");
		}
		if(template.getStatus() == null){
			throw new ParamException("status参数缺失");
		}
		
		OpenTemplate dbtemplate = baseMapper.selectById(template.getId());
        if (dbtemplate == null) {
			throw new BusinessException("id不存在");
        }
        dbtemplate.setStatus(template.getStatus());
        dbtemplate.setUpdateTime(new Date());

        int i = baseMapper.updateById(dbtemplate);
        log.info("修改openapi：{}", dbtemplate);

        return i > 0 ? Result.succeed(dbtemplate, "更新成功") : Result.failed("更新失败");
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void grantApisToTemplate(Long templateId, Set<Long> apiIds) throws ParamException {
		if(templateId == null || apiIds == null){
			throw new ParamException("参数缺失");
		}
		templateApiMapper.delete(templateId, null);

		if (!CollectionUtils.isEmpty(apiIds)) {
			apiIds.forEach(apiId -> templateApiMapper.save(templateId, apiId));
		}
		
	}
	
	
}
