package com.houyi.client.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.houyi.client.service.IPaasService;
import com.houyi.common.model.PageResult;
import com.houyi.common.model.Result;
import com.houyi.common.model.client.ClientPaas;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author houyi.com
 */
@RestController
@Api(tags = "平台微服务")
@Slf4j
@RequestMapping("/server")
public class PlatformServiceController {
    @Autowired
    private IPaasService platformService;
	
    /**
     * 添加微服务 或者 更新
     * 
     * @param server (serviceCode,serviceName)
     * @return
     */
    @ApiOperation(value = "新增微服务")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id更新时传", required = false, dataType = "Integer"),
        @ApiImplicitParam(name = "serviceCode", value = "服务code", required = true, dataType = "String"),
        @ApiImplicitParam(name = "serviceName", value = "服务名称", required = true, dataType = "String")
     })
    @PostMapping("/saveOrUpdate")
    public Result<String> saveOrUpdate(@RequestBody ClientPaas server) {
        try {
        	if(platformService.insertOrUpdate(server)){
        		return Result.succeed("操作成功");
        	}else{
        		return Result.failed("操作成功");
        	}
        } catch (Exception ex) {
            log.error("platform-service-saveOrUpdate-error", ex);
            return Result.failed("操作失败");
        }
    }
    
    /**
     * 修改状态
     *
     * @param params
     * @return
     */
    @ApiOperation(value = "修改状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "服务id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "状态(0:不可用,1:可用,-1:删除)", required = true, dataType = "Integer")
    })
    @PostMapping("/status")
    public Result<Object> status(@RequestParam Map<String, Object> params) {
        try {
            return platformService.updateStastus(params);
        } catch (Exception ex) {
            log.error("memu-delete-error", ex);
            return Result.failed("操作失败");
        }
    }
    
    
    @ApiOperation(value = "查询所有服务")
    @GetMapping("/findAlls")
    public PageResult<ClientPaas> findAlls() {
        List<ClientPaas> list = platformService.findAll();
        return PageResult.<ClientPaas>builder().data(list).code(0).count((long) list.size()).build();
    }

    
}
