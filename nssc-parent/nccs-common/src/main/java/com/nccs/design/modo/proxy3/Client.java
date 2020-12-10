package com.nccs.design.modo.proxy3;

/**
 * 设计模式 -- Cglib动态代理
 *
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-12-08 14:25
 * @description: 客户端
 **/

public class Client {
    public static void main(String[] args) {
        OutsourcedProgrammer programmer = new OutsourcedProgrammer();
        OutsourcedProgrammer proxyInstance = (OutsourcedProgrammer) new OutsourcedCompanyProxy(programmer).getProxyInstance();
        proxyInstance.startCoding();
    }
}
