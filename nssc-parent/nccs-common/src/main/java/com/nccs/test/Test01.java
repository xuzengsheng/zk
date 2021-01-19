package com.nccs.test;

import com.alibaba.excel.annotation.ExcelProperty;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Map;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2021-01-19 17:27
 * @description: 通过反射，动态修改注解的属性值
 **/

public class Test01 {
    public static void main(String[] args) {
        try {
            ArrayList<ContractPriceExcelDto> dots = new ArrayList<>();
            ContractPriceExcelDto contractPriceExcelDto = new ContractPriceExcelDto();
            contractPriceExcelDto.setLineNum(1);
            dots.add(contractPriceExcelDto);
            for (ContractPriceExcelDto dot : dots) {
                Class dotClass = dot.getClass();
                for (Field field : dotClass.getDeclaredFields()) {
                    field.setAccessible(true);
                    ExcelProperty annotation = field.getDeclaredAnnotation(ExcelProperty.class);
                    System.out.println("修改前：" + annotation.index());
                    if (annotation == null) {
                        return;
                    }
                    //获取 annotation 这个代理实例所持有的 InvocationHandler
                    InvocationHandler handler = Proxy.getInvocationHandler(annotation);
                    // 获取 AnnotationInvocationHandler 的 memberValues 属性。（InvocationHandler是接口，而他的实现类是AnnotationInvocationHandler）
                    Field handlerField = handler.getClass().getDeclaredField("memberValues");
                    handlerField.setAccessible(true);
                    // 获取 memberValues ，这里面是注解中的所有属性和属性值，以map形式储存
                    Map memberValues = (Map) handlerField.get(handler);
                    memberValues.put("index", 10); //修改对应属性值
                    System.out.println("修改后：" + annotation.index());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
