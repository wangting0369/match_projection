package com.zixian.matchprojection.exception;

import com.zixian.matchprojection.common.BaseResponce;
import com.zixian.matchprojection.common.ResponceCode;
import com.zixian.matchprojection.common.ServerResponce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * AOP处理
 * 在方法调用前后进行的额外处理
 */


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandle {
    @ExceptionHandler(BusinessException.class)
    public BaseResponce businessExceptionHandle(BusinessException e){
        log.error("businessExceptionHandle",e);
        return ServerResponce.error(e);
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponce runtimeExceptionHandle(RuntimeException e){
        log.error("runtimeExceptionHandle",e);
        return ServerResponce.error(ResponceCode.Runtime_Exception);
    }
}
