package com.nccs.design.modo.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 设计模式 -- JDK代理
 *
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-12-08 14:08
 * @description: 外包公司（代理角色）
 **/

public class OutsourcedCompanyProxy {

    //程序员
    private Object target;

    public OutsourcedCompanyProxy(Object target) {
        this.target = target;
    }


    //获取代理对象
    public Object getProxyInstance() {
        // newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
        //loader:目标类的类加载器
        //interfaces 目标类所实现的接口
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("招聘程序员");
                        System.out.println("面试");
                        System.out.println("签订外包公司劳动合同");
                        return method.invoke(target, args);
                    }
                });
    }
}
