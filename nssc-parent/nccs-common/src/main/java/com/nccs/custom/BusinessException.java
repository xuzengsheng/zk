package com.nccs.custom;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-27 14:51
 * @description:
 **/
@Data
public class BusinessException extends RuntimeException{

    private Logger logger = LoggerFactory.getLogger(BusinessException.class);
    private String code;
    private String msg;
    private Object[] args;


    public BusinessException() {
    }

    public BusinessException(BusinessException baseException) {
        this.code = baseException.getCode();
        this.msg = baseException.getMsg();
    }

    public BusinessException(BaseCodeEnum baseCodeEnum) {
        this.code = baseCodeEnum.getCode();
        this.msg = baseCodeEnum.getMsg();
    }

    public BusinessException(BusinessException baseException, Object[] args) {
        this.code = baseException.getCode();
        this.args = args;
    }

    public BusinessException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String code) {
        this.code = code;
    }

    public BusinessException(Exception e) {
        this.logger.warn(e.getMessage(), e);
        this.code = BaseCodeEnum.FAILED.getCode();
    }

    public static BusinessException fail(String msg) {
        return new BusinessException(BaseCodeEnum.FAILED.getCode(), msg);
    }

    public static BusinessException fail(BusinessException BusinessException) {
        return new BusinessException(BusinessException);
    }
}
