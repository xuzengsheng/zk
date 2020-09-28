package com.nccs.custom;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-27 10:38
 * @description:
 **/

public enum BaseCodeEnum {
    SUCCESS("0", "Success"),
    FAILED("1", "系统繁忙,请稍后再试"),
    ACCOUNT_OR_PASSOWD_WRONG("101", "账号或密码错误"),
    NO_PERMISSION("102", "没有访问该资源的权限"),
    SIGN_ERROR_WITH_EMPTY_DATA("103", "数据签名错误,存在为空的参数"),
    GET_SECRET_ERROR("104", "找不到本应用失败"),
    NO_CURRENT_USER("105", "当前没有登录的用户"),
    TOKEN_EXPIRED("106", "token过期"),
    TOKEN_ERROR("107", "token验证失败"),
    PERMISSION_ERROR("108", "没有访问该资源的权限"),
    SIGN_ERROR("109", "签名验证失败"),
    VERSION_EXPIRES("110", "数据已过期，请刷新后操作"),
    WRITE_ERROR("500", "渲染界面错误"),
    REQUEST_NULL("400", "请求数据为空或格式错误"),
    PARAMETER_INVALID("501", "参数校验失败"),
    PAGE_NULL("404", "请求页面不存在"),
    IO_ERROR("500", "流读取异常"),
    SERVICE_ERROR("500", "服务器异常"),
    REMOTE_SERVICE_NULL("404", "远程服务不存在"),
    PAGE_SIZE_MORE_THAN_1000("1001", "PageSize is too large,it must be less than 1001"),
    ENCRYPT_ERROR("1002", "加解密错误"),
    MAS_REMOTE_USER_IS_NULL("110111", "Sign in please"),
    ACCESS_TOKEN_IS_ERROR("110112", "AccessToken is null or error"),
    ACCESS_TOKEN_INVALID("110113", "AccessToken is invalid"),
    REFRESH_TOKEN_IS_NULL("110114", "RefreshToken is null"),
    REFRESH_TOKEN_IS_INVALID("110115", "RefreshToken is invalid"),
    SESSION_TIMEOUT("110116", "Session Timeout"),
    DENY_ACCESS("110117", "Deny Access"),
    BAD_SQL_ERROR("110118", "Bad sql error"),
    REST_METHOD_NOT_SUPPORT("110119", "Rest method not support"),
    VALIDATE_ERROR("110120", "Validate error"),
    FILE_NOT_FOUND("110121", "File not found"),
    FILE_READING_ERROR("110122", "File reading error"),
    DATA_Exist("110123", "数据已存在"),
    PREFIX_EXIST("110124", "前缀已存在"),
    MIC_ACCESS_TOKEN_IS_ERROR("110113", "MicAccessToken is null or error");

    private String code;
    private String msg;

    private BaseCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }

    public String toString() {
        return "ResultCodeConstant{code='" + this.code + '\'' + ", msg='" + this.msg + '\'' + '}';
    }
}
