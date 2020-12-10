package com.nccs.design.modo.proxy1;

/**
 * 设计模式 -- 静态代理
 *
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-12-08 14:07
 * @description: 外包程序员（真实角色）
 **/


public class OutsourcedProgrammer implements Programmer {
    @Override
    public void startCoding() {
        System.out.println("程序员开始编码");
    }
}
