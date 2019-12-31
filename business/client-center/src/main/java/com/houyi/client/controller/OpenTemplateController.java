package com.houyi.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houyi.client.exception.BusinessException;
import com.houyi.client.exception.ParamException;
import com.houyi.client.exception.SqlException;
import com.houyi.client.service.ITemplateService;
import com.houyi.common.model.Result;
import com.houyi.common.model.client.OpenTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author houyi.com
 */
@RestController
@Api(tags = "平台开放接口模板api")
@Slf4j
@RequestMapping("/template")
public class OpenTemplateController {
	
    @Autowired
    private ITemplateService templateService;
    
    /**
     * 新增/修改
     * @param template(code name)
     * @return
     * @throws SqlException 
     * @throws ParamException 
     */
    @ApiOperation(value = "新增模板")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id更新时传", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "code", value = "模板code", required = true, dataType = "String"),
        @ApiImplicitParam(name = "name", value = "模板名称", required = true, dataType = "String")
     })
    @PostMapping("/saveOrUpdate")
    public Result<String> saveOrUpdate(@RequestBody OpenTemplate template) throws ParamException, SqlException {
        	if(templateService.insertOrUpdate(template)){
        		return Result.succeed("操作成功");
        	}else{
        		return Result.failed("操作失败");
        	}
    }

    /**
     * 修改状态
     *
     * @param params
     * @return
     * @throws BusinessException 
     * @throws ParamException 
     */
    @ApiOperation(value = "修改状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "状态(0:不可用,1:可用,-1:删除)", required = true, dataType = "Integer")
    })
    @PostMapping("/status")
    public Result<OpenTemplate> status(@RequestBody OpenTemplate template) throws ParamException, BusinessException {
            return templateService.updateStatus(template);
    }

    

    /**
     * 
     * @param template
     * @return
     * @throws ParamException 
     */
    @ApiOperation(value = "模板分配接口")
    @PostMapping("/grantapis")
    public Result<String> setMenuToRole(@RequestBody OpenTemplate template) throws ParamException {
    	templateService.grantApisToTemplate(template.getId(), template.getApiIds());
        return Result.succeed("操作成功");
    }


    
}
