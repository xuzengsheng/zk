package com.nccs.design.modo.proxy1;

/**
 * 设计模式 -- 静态代理
 *
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-12-08 14:08
 * @description: 客户端
 * <p>
 * 缺点：
 * 因为代理类和目标对象必须实现同一个接口，代理类必须和目标对象数量一致，导致代理类数量过多
 * 因为采取了硬编码的方式，如果接口增加方法，代理类和目标对象必须进行修改
 **/

public class Client {
    public static void main(String[] args) {
        Programmer programmer = new OutsourcedProgrammer();
        OutsourcedCompanyProxy outsourcedCompanyProxy = new OutsourcedCompanyProxy(programmer);
        outsourcedCompanyProxy.startCoding();

    }
}
