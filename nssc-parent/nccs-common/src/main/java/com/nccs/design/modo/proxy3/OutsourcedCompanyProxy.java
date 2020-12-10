package com.nccs.design.modo.proxy3;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 设计模式 -- Cglib动态代理
 *
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-12-08 14:21
 * @description: 外包公司（代理角色）
 **/

public class OutsourcedCompanyProxy implements MethodInterceptor {

    //程序员
    private Object target;

    public OutsourcedCompanyProxy(Object target) {
        this.target = target;
    }

    //获取代理对象
    public Object getProxyInstance() {
        //cglib增强器，用来创建代理对象
        Enhancer enhancer = new Enhancer();
        //设置需要创建的代理对象
        enhancer.setSuperclass(target.getClass());
        //设置回调，所有方法都会被intercept拦截
        enhancer.setCallback(this);
        //创建代理对象
        return enhancer.create();
    }


    //重写intercept方法
    @Override
    public Object intercept(Object arg0, Method method, Object[] args, MethodProxy arg3) throws Throwable {
        System.out.println("招聘程序员");
        System.out.println("面试");
        System.out.println("签订外包公司劳动合同");
        return method.invoke(target, args);
    }
}
