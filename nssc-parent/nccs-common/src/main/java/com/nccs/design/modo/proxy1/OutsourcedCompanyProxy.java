package com.nccs.design.modo.proxy1;

/**
 * 设计模式 -- 静态代理
 *
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-12-08 14:08
 * @description: 外包公司（代理角色）
 **/

public class OutsourcedCompanyProxy implements Programmer {

    //程序员
    private Programmer programmer;

    public OutsourcedCompanyProxy(Programmer programmer) {
        this.programmer = programmer;
    }

    @Override
    public void startCoding() {
        System.out.println("招聘程序员");
        System.out.println("面试");
        System.out.println("签订外包公司劳动合同");
        programmer.startCoding();
    }
}
