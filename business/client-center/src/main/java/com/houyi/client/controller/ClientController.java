package com.houyi.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houyi.client.exception.BusinessException;
import com.houyi.client.exception.ParamException;
import com.houyi.client.exception.SqlException;
import com.houyi.client.model.ClientTemplate;
import com.houyi.client.service.IClientService;
import com.houyi.common.model.Result;
import com.houyi.common.model.client.Client;
import com.houyi.common.model.client.OpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author houyi.com
 */
@RestController
@Api(tags = "客户端接口")
@Slf4j
@RequestMapping("/client")
public class ClientController {
	
    @Autowired
    private IClientService clientService;
    
    /**
     * 添加 或者 更新
     *
     * @param openapi(clientId resourceIds clientSecretStr)
     * @return
     * @throws SqlException 
     * @throws ParamException 
     * @throws BusinessException 
     */
    @ApiOperation(value = "新增接口")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id更新时传", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "clientId", value = "应用标识", required = true, dataType = "String"),
        @ApiImplicitParam(name = "additionalInformation", value = "商户merchantId {merchantId:100}", required = true, dataType = "String"),
        @ApiImplicitParam(name = "clientSecretStr", value = "应用密钥(明文)", required = true, dataType = "String")
     })
    @PostMapping("/saveOrUpdate")
    public Result<Client> saveOrUpdate(@RequestBody Client client) throws ParamException, SqlException, BusinessException {
        	return Result.succeed(clientService.insertOrUpdate(client));
    }
    
    /**
     * 添加 或者 更新
     *
     * @param ClientTemplate(clientId tempalteId)
     * @return
     * @throws SqlException 
     * @throws ParamException 
     * @throws BusinessException 
     */
    @ApiOperation(value = "客户端分配接口模板")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "clientId", value = "客户端ID", required = true, dataType = "Long"),
        @ApiImplicitParam(name = "tempalteId", value = "模板ID", required = true, dataType = "Long")
     })
    @PostMapping("/saveTemplate")
    public Result<String> saveOrUpdate(@RequestBody ClientTemplate ct) throws ParamException, SqlException, BusinessException {
    	if(clientService.saveTemplate(ct)){
    		return Result.succeed("成功");
    	}else{
    		return Result.failed("操作失败");
    	}
    }
    
    /**
     * 查询客户端有权访问的接口
     *
     * @param clientId
     * @return
     * @throws SqlException 
     * @throws ParamException 
     * @throws BusinessException 
     */
    @ApiOperation(value = "查询客户端有权访问的接口")
    @GetMapping("/findApisById/{clientId}")
    public List<OpenApi> findApisById(@PathVariable Long clientId){
    	return clientService.findApis(clientId);
    } 
    
    /**
     * 通过应用标识查询客户端有权访问的接口
     *
     * @param clientId
     * @return
     * @throws SqlException 
     * @throws ParamException 
     * @throws BusinessException 
     */
    @ApiOperation(value = "查询客户端有权访问的接口")
    @GetMapping("/findApis/{client_id}")
    public List<OpenApi> findApis(@PathVariable String client_id){
    	return clientService.findApis(client_id);
    } 
}
