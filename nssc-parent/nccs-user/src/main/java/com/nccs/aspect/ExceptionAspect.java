//package com.nccs.aspect;
//
///**
// * @program: nssc-parent
// * @author: xuzengsheng
// * @create: 2020-09-27 16:10
// * @description:
// **/
//
//import com.google.common.collect.Lists;
//import com.nccs.custom.BaseResponse;
//import com.nccs.custom.BusinessException;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * @Author: gmy
// * @Description: 基于AOP的全局异常统一处理
// * @Date: 2018/6/1
// * @Time: 13:46
// */
////@Component
//@Aspect
//public class ExceptionAspect {
//    public static final Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);
//
//    /**
//     * 定义切入点 发起请求
//     */
//    @Pointcut("execution(* com.nccs.*.*.*(..))")
//    public void pointCut() {
//    }
//
//    /**
//     * 定义切面
//     *
//     * @param point
//     * @return
//     */
//    @Around("pointCut()")
//    public Object handleControllerMethod(ProceedingJoinPoint point) {
//
//        Object object;
//        try {
//            logger.info("执行Controller开始: " + point.getSignature() + " 参数：" + Lists.newArrayList(point.getArgs()).toString());
//            object = point.proceed(point.getArgs());
//            logger.info("执行Controller结束: " + point.getSignature() + "， 返回值：" + object.toString());
//        } catch (Throwable throwable) {
//            return handlerException(point, throwable);
//        }
//        return object;
//    }
//
//    /**
//     * 全局异常处理
//     *
//     * @param point
//     * @param e
//     * @return
//     */
//    private BaseResponse<?> handlerException(ProceedingJoinPoint point, Throwable e) {
//        BaseResponse<?> baseResponse = null;
//        if (e.getClass().isAssignableFrom(BusinessException.class)) {
//            BusinessException businessException = (BusinessException) e;
//            logger.error("RuntimeException{方法：" + point.getSignature() + "， 参数：" + point.getArgs() + ",异常：" + businessException.getMessage() + "}", e);
//            baseResponse = new BaseResponse<>(businessException);
//        } else if (e instanceof RuntimeException) {
//            logger.error("RuntimeException{方法：" + point.getSignature() + "， 参数：" + point.getArgs() + ",异常：" + e.getMessage() + "}", e);
//            baseResponse = BaseResponse.fail(e.getMessage());
//        } else {
//            logger.error("异常{方法：" + point.getSignature() + "， 参数：" + point.getArgs() + ",异常：" + e.getMessage() + "}", e);
//            baseResponse = BaseResponse.fail(e.getMessage());
//        }
//        return baseResponse;
//    }
//}