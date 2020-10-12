package com.nccs.advice;

import com.nccs.custom.BaseResponse;
import com.nccs.custom.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-28 09:12
 * @description: 全局异常处理通知
 **/
@ControllerAdvice
public class ExceptionHandlerAdvice {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public BaseResponse businessException(BusinessException e) {
        logger.error("全局异常处理器捕获到BusinessException异常：{}", e.getMsg());
        return new BaseResponse(e);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public BaseResponse runtimeException(RuntimeException e) {
        logger.error("全局异常处理器捕获到RuntimeException异常：{}", e);
        return BaseResponse.fail(e.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public BaseResponse throwable(Throwable e) {
        logger.error("全局异常处理器捕获到Throwable异常：{}", e);
        return BaseResponse.fail(e.getMessage());
    }
}
