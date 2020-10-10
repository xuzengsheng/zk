package com.nccs.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-27 17:12
 * @description:
 **/
//@Component
public class UserLoginAdapter implements WebMvcConfigurer {

    @Autowired
    private UserLoginInterceptor userLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //添加对用户是否登录的拦截器，并添加过滤项、排除项
        registry.addInterceptor(userLoginInterceptor).addPathPatterns("/**"); //拦截所有界面
//                .excludePathPatterns("/css/**", "/js/**", "/images/**")//排除样式、脚本、图片等资源文件
//                .excludePathPatterns("/wechatplatformuser/loginRBAC.html")//排除登录页面
//                .excludePathPatterns("/wechatplatformuser/defaultKaptcha")//排除验证码
//                .excludePathPatterns("/wechatplatformuser/loginRBAC");//排除用户点击登录按钮

    }


}
