package com.nccs.design.modo.proxy2;

/**
 * 设计模式 -- JDK代理
 *
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-12-08 14:07
 * @description: A公司外包程序员（真实角色）
 **/


public class AoutsourcedProgrammer implements Programmer {
    @Override
    public void startCoding() {
        System.out.println("A公司程序员开始编码");
    }
}
