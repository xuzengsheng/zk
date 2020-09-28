package com.nccs.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nccs.custom.BaseCodeEnum;
import com.nccs.custom.BaseResponse;
import com.nccs.custom.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-27 17:04
 * @description: 用户登录拦截器
 **/
@Component
public class UserLoginInterceptor implements HandlerInterceptor {


    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //获取请求头中的用户TOKEN
//        String user_token = request.getHeader("USER_TOKEN");
//        if (StringUtils.isEmpty(user_token)) {
//            //如果未获取到token，则response返回
//            setReturn(response, BaseCodeEnum.SIGN_ERROR.getCode(), BaseCodeEnum.SIGN_ERROR.getMsg());
//            return false;
//        } else {
//            //如果获取到token,则验证token的信息
//            return true;
//        }
        return true;
    }

    //返回错误信息
    private static void setReturn(HttpServletResponse response, String code, String msg) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        //UTF-8编码
        httpResponse.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        BaseResponse build = new BaseResponse(code, msg);
        String json = new ObjectMapper().writeValueAsString(build);
        httpResponse.getWriter().print(json);
    }

}
