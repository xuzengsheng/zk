package com.nccs.design.modo.proxy2;

/**
 * 设计模式 -- JDK代理
 *
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-12-08 14:07
 * @description: B公司外包程序员（真实角色）
 **/


public class BoutsourcedProgrammer implements Programmer {
    @Override
    public void startCoding() {
        System.out.println("B公司程序员开始编码");
    }
}
