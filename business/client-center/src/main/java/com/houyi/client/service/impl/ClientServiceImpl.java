package com.houyi.client.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.houyi.client.exception.BusinessException;
import com.houyi.client.exception.ParamException;
import com.houyi.client.exception.SqlException;
import com.houyi.client.mapper.ClientMapper;
import com.houyi.client.mapper.ClientTemplateMapper;
import com.houyi.client.mapper.OpenApiMapper;
import com.houyi.client.model.ClientTemplate;
import com.houyi.client.service.IClientService;
import com.houyi.common.model.client.Client;
import com.houyi.common.model.client.OpenApi;
import com.houyi.common.service.impl.SuperServiceImpl;

import cn.hutool.core.util.StrUtil;
@Service
public class ClientServiceImpl extends SuperServiceImpl<ClientMapper, Client> implements IClientService{
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Resource
	private ClientTemplateMapper clientTemplateMapper;
	
	@Resource
	private OpenApiMapper openApiMapper;
	
	@Override
	public Client insertOrUpdate(Client client) throws ParamException, SqlException,BusinessException {
		if(client == null){
			throw new ParamException("参数缺失");
		}
		if(client.getId() == null){
			if(!StrUtil.isNotBlank(client.getClientId())){
				throw new ParamException("clientId参数缺失");
			}
			if(!StrUtil.isNotBlank(client.getAdditionalInformation())){
				throw new ParamException("additionalInformation参数缺失");
			}
			if(!StrUtil.isNotBlank(client.getClientSecretStr())){
				throw new ParamException("clientSecretStr参数缺失");
			}
			client.setCreateTime(new Date());
			client.setUpdateTime(new Date());
			client.setClientSecret(passwordEncoder.encode(client.getClientSecretStr()));
		}
		try{
			 if(super.saveOrUpdate(client)){
				 return client;
			 }else{
				 throw new BusinessException("操作失败");
			 }
		}catch (Exception e) {
			throw new SqlException(e);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public boolean saveTemplate(ClientTemplate ct) throws ParamException {
		if(ct == null || ct.getClientId()==null || ct.getTempalteId()==null){
			throw new ParamException("参数缺失");
		}
		clientTemplateMapper.delete(ct.getClientId(), null);
		if(clientTemplateMapper.save(ct.getClientId(), ct.getTempalteId()) == 1){
			return true;
		}
		return false;
	}

	@Override
	public List<OpenApi> findApis(Long clientId) {
		return openApiMapper.findApisByClientId(clientId);
	}

	@Override
	public List<OpenApi> findApis(String client_id) {
		return openApiMapper.findApisByClient_Id(client_id);
	}
	
	
}
