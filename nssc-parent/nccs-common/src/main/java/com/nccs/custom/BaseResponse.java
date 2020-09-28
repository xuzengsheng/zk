package com.nccs.custom;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-27 10:33
 * @description: 请求统一响应格式类
 **/
@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = -6609499448346439617L;
    private String code;
    private String msg;
    private String timestamp;
    public T data;

    /**
     * 无参构造 默认返回成功
     */
    public BaseResponse() {
        this.code = BaseCodeEnum.SUCCESS.getCode();
        this.msg = BaseCodeEnum.SUCCESS.getMsg();
    }

    /**
     * 判断请求是否调用成功
     *
     * @return
     */
    public boolean isSuccess() {
        return BaseCodeEnum.SUCCESS.getCode().equals(this.code);
    }


    /**
     * 有参构造
     *
     * @param BusinessException 自定义异常类
     */
    public BaseResponse(BusinessException BusinessException) {
        this.code = BusinessException.getCode();
        this.msg = BusinessException.getMsg();
    }

    public BaseResponse(BaseCodeEnum baseCodeEnum) {
        this.code = baseCodeEnum.getCode();
        this.msg = baseCodeEnum.getMsg();
    }


    /**
     * 有参构造
     *
     * @param code 异常编码
     * @param msg  异常信息
     */
    public BaseResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private void saveJsonResult(BaseCodeEnum baseCodeEnum) {
        this.code = baseCodeEnum.getCode();
        this.msg = baseCodeEnum.getMsg();
    }

    /**
     * 打印异常
     *
     * @param msg
     * @param log
     */
    public void errorParam(String msg, Logger log) {
        this.code = BaseCodeEnum.FAILED.getCode();
        this.msg = BaseCodeEnum.FAILED.getMsg() + msg;
        log.error("error code|{}|msg|{}|", this.code, msg);
    }

    /**
     * 打印异常
     *
     * @param code
     * @param msg
     * @param log
     */
    public void errorParam(String code, String msg, Logger log) {
        this.custom(code, msg, log);
    }

    /**
     * 默认异常
     *
     * @param code
     * @param msg
     * @param log
     */
    public void custom(String code, String msg, Logger log) {
        this.code = code;
        this.msg = msg;
        log.error("error code|{}|msg:{}", code, msg);
    }

    public void saveResult(String codeInput, Logger log) {
        this.saveResult(codeInput, (String) null, (Logger) null);
    }

    public void saveResult(String codeInput, String msg, Logger log) {
        byte var5 = -1;
        switch (codeInput.hashCode()) {
            case 48:
                if (codeInput.equals("0")) {
                    var5 = 0;
                }
            default:
                switch (var5) {
                    case 0:
                        this.saveJsonResult(BaseCodeEnum.SUCCESS);
                        break;
                    default:
                        this.saveJsonResult(BaseCodeEnum.FAILED);
                }

                if (StringUtils.isNotBlank(msg)) {
                    this.setMsg(msg);
                }

                if (null != log) {
                    log.error("error code:{}|msg:{}", this.code, msg);
                }

        }
    }

    public void go(Logger logger) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement[] var3 = stackTrace;
        int var4 = stackTrace.length;
        for (int var5 = 0; var5 < var4; ++var5) {
            StackTraceElement s = var3[var5];
            logger.error(s.getClassName() + ",-" + s.getMethodName() + ":[ " + s.getLineNumber() + "]");
        }

    }

    public BaseResponse build(String code, String msg) {
        this.code = code;
        this.msg = msg;
        LocalDateTime arrivalDate = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        String timestamp = arrivalDate.format(format);
        this.timestamp = timestamp;
        return this;
    }


    /**
     * 响应成功
     *
     * @param data 响应数据
     * @return
     */
    public static BaseResponse<Object> success(Object data) {
        BaseResponse<Object> baseResponse = new BaseResponse(BaseCodeEnum.SUCCESS);
        baseResponse.setData(data);
        return baseResponse;
    }

    /**
     * 响应失败
     *
     * @param msg 异常信息
     * @return
     */
    public static BaseResponse<Object> fail(String msg) {
        return new BaseResponse(BaseCodeEnum.FAILED.getCode(), msg);
    }

    /**
     * 响应失败
     *
     * @param code 异常码
     * @param msg  异常信息
     * @return
     */
    public static BaseResponse<Object> fail(String code, String msg) {
        return new BaseResponse(code, msg);
    }

    /**
     * 响应数据
     * @return
     */
    public T data() {
        if (BaseCodeEnum.SUCCESS.getCode().equals(this.code)) {
            return this.data;
        }
        return null;
    }
}