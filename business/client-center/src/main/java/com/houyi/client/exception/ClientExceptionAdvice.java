package com.houyi.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.houyi.common.exception.DefaultExceptionAdvice;
import com.houyi.common.model.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局Controller 外抛的异常处理
 * @Author: houyi
 * 
 */
@ControllerAdvice
@Slf4j
public class ClientExceptionAdvice extends DefaultExceptionAdvice {
    /**
     * ParamException 参数异常处理
     * 返回状态码:200
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ParamException.class})
    public Result<Object> handleException(ParamException e) {
        return defHandler(e.getErrorCode(),e.getMessage(),e);
    }
    
    /**
     * BusinessException 业务异常处理
     * 返回状态码:200
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    public Result<Object> handleException(BusinessException e) {
        return defHandler(e.getErrorCode(),e.getMessage(),e);
    }
    
    /**
     * SqlException 数据库操作异常处理
     * 返回状态码:200
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(SqlException.class)
    public Result<Object> handleException(SqlException e) {
        return defHandler(e.getErrorCode(),e.getMessage(),e);
    }
    
    /**
     * RunException 运行时异常处理
     * 返回状态码:200
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RunException.class)
    public Result<Object> handleException(RunException e) {
        return defHandler(e.getErrorCode(),e.getMessage(),e);
    }
    
    /**
     * Exception 异常处理
     * 返回状态码:200
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) {
        return defHandler(ClientErrorCode.CLIENT_DEF,e.getMessage(),e);
    }
    
    private Result<Object> defHandler(String code,String msg, Exception e) {
        log.error(msg, e);
        return Result.failed(msg);
    }
}
