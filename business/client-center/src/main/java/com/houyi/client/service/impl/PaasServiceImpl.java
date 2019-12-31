package com.houyi.client.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.houyi.client.exception.ClientErrorCode;
import com.houyi.client.exception.ParamException;
import com.houyi.client.mapper.PaasMapper;
import com.houyi.client.service.IPaasService;
import com.houyi.common.model.Result;
import com.houyi.common.model.client.ClientPaas;
import com.houyi.common.service.impl.SuperServiceImpl;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
/**
 * @author houyi.com
 */
@Service
@Slf4j
public class PaasServiceImpl extends SuperServiceImpl<PaasMapper, ClientPaas> implements IPaasService{
	
	@Resource
	private PaasMapper paasMapper;

	@Override
	public boolean insertOrUpdate(ClientPaas entity) throws ParamException {
		if(entity == null){
			throw new ParamException(ClientErrorCode.PARAM_CODE,"参数缺失");
		}
		if(entity != null && entity.getId() == null){
			entity.setCreateTime(new Date());
			entity.setUpdateTime(new Date());
			if(!StrUtil.isNotBlank(entity.getServiceCode())){
				throw new ParamException(ClientErrorCode.PARAM_CODE,"serviceCode参数缺失");
			}
			if(!StrUtil.isNotBlank(entity.getServiceName())){
				throw new ParamException(ClientErrorCode.PARAM_CODE,"serviceName参数缺失");
			}
		}
		return super.saveOrUpdate(entity);
	}
	
	@Override
	public Result<Object> updateStastus(Map<String, Object> params) {
        Long id = MapUtils.getLong(params, "id");
        Integer status = MapUtils.getInteger(params, "status");

        ClientPaas pServer = baseMapper.selectById(id);
        if (pServer == null) {
            return Result.failed("服务不存在");
        }
        pServer.setStatus(status);
        pServer.setUpdateTime(new Date());

        int i = baseMapper.updateById(pServer);
        log.info("修改用户：{}", pServer);

        return i > 0 ? Result.succeed(pServer, "更新成功") : Result.failed("更新失败");
	}

	@Override
	public List<ClientPaas> findAll() {
		return baseMapper.selectList(
                new QueryWrapper<ClientPaas>().orderByAsc("sort").between("status", 0, 1)
        );
	}
	


}
