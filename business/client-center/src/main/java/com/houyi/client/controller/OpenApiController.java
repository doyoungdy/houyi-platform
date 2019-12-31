package com.houyi.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houyi.client.exception.BusinessException;
import com.houyi.client.exception.ParamException;
import com.houyi.client.exception.SqlException;
import com.houyi.client.service.IOpenApiService;
import com.houyi.common.model.PageResult;
import com.houyi.common.model.Result;
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
@Api(tags = "开放接口")
@Slf4j
@RequestMapping("/openapi")
public class OpenApiController {
	
    @Autowired
    private IOpenApiService openApiService;
    
    /**
     * 添加 或者 更新
     *
     * @param openapi(service_code api_name api_path)
     * @return
     * @throws SqlException 
     * @throws ParamException 
     */
    @ApiOperation(value = "新增接口")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id更新时传", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "serviceCode", value = "所在服务code", required = true, dataType = "String"),
        @ApiImplicitParam(name = "apiName", value = "接口名称", required = true, dataType = "String"),
        @ApiImplicitParam(name = "apiPath", value = "接口path", required = true, dataType = "String"),
     })
    @PostMapping("/saveOrUpdate")
    public Result<String> saveOrUpdate(@RequestBody OpenApi openapi) throws ParamException, SqlException {
        	if(openApiService.insertOrUpdate(openapi)){
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
    public Result<OpenApi> status(@RequestBody OpenApi openapi) throws ParamException, BusinessException {
            return openApiService.updateStatus(openapi);
    }
    
    
    @ApiOperation(value = "查询所有接口")
    @GetMapping("/findAlls")
    public PageResult<OpenApi> findAlls() {
        List<OpenApi> list = openApiService.findAllApis(null);
        return PageResult.<OpenApi>builder().data(list).code(0).count((long) list.size()).build();
    }
    
}
