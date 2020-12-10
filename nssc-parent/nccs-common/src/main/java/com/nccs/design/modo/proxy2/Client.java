package com.nccs.design.modo.proxy2;

/**
 * 设计模式 -- JDK代理
 *
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-12-08 14:08
 * @description: 客户端
 * <p>
 * jdk代理是动态代理的一种，是通过反射实现的，和静态代理不同的是，代理类不再需要实现目标对象的接口
 * <p>
 * jdk动态代理有一个很大的局限性，目标对象必须实现接口，但是有的目标对象并不需要一个接口，这就需要采用Cglib 代理
 **/

public class Client {
    public static void main(String[] args) {
        //A外包公司程序员
        Programmer aProgrammer = new AoutsourcedProgrammer();
        Programmer proxyInstanceA = (Programmer) new OutsourcedCompanyProxy(aProgrammer).getProxyInstance();
        proxyInstanceA.startCoding();
        //B外包公司程序员
        Programmer bProgrammer = new BoutsourcedProgrammer();
        Programmer proxyInstanceB = (Programmer) new OutsourcedCompanyProxy(bProgrammer).getProxyInstance();
        proxyInstanceB.startCoding();
        //C外包公司程序员
        Programmer cProgrammer = new CoutsourcedProgrammer();
        Programmer proxyInstanceC = (Programmer) new OutsourcedCompanyProxy(cProgrammer).getProxyInstance();
        proxyInstanceC.startCoding();

    }
}
