package com.houyi.log.config;

import com.houyi.common.exception.DefaultExceptionAdvice;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * 统一异常处理
 *
 * @Author: houyi
 * 
 */
@ControllerAdvice
public class ExceptionAdvice extends DefaultExceptionAdvice {
}
