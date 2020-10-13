package com.nccs.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 简单的令牌桶限流
 * 在SpringCloud分布式下实现限流，需要把令牌桶的维护放到一个公共的地方，比如Zuul路由，另外，guava里有现成的基于令牌桶的限流实现。
 */
@Slf4j
public class RateLimiter {

    /**
     * 桶的大小
     */
    private Integer limit;

    /**
     * 桶当前存放的令牌数
     */
    private static Integer tokens = 0;

    /**
     * 构造参数
     */
    public RateLimiter(Integer limit, Integer speed) {
        //初始化桶的大小，且桶一开始是满的
        this.limit = limit;
        //令牌数等于桶的最大容量
        RateLimiter.tokens = this.limit;

        //任务线程：每秒新增speed个令牌
        asyncTask(speed);
    }

    /**
     * 每秒往桶中新增speed个令牌
     *
     * @param speed
     */
    public void asyncTask(Integer speed) {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000L);
                    int newTokens = tokens + speed; //实际令牌数 = 剩下的令牌数 + 新增的令牌数
                    if (newTokens > limit) { //如果桶中的令牌数大于最大容量，则只取最大容量
                        tokens = limit;
                        System.out.println("桶满了");
                    } else {
                        tokens = newTokens;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    /**
     * 获取令牌
     * 当桶中的令牌数大于0时，获取成功 返回为true，否则获取失败，返回false
     * 此方法需要加锁
     */
    public synchronized boolean acquire() {
        if (tokens > 0) {
            tokens = tokens - 1;
            return true;
        }
        return false;
    }

    //测试
//    public static void main(String[] args) {
//        //令牌桶限流：峰值每秒可以处理10个请求，正常每秒可以处理3个请求
//        RateLimiter rateLimiter = new RateLimiter(10, 3);
//
//        //模拟请求
//        while (true) {
//            try {
//                Thread.sleep(200L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            //获取令牌
//            if (rateLimiter.acquire()) {
//                System.out.println("允许访问,token还剩 = " + tokens);
//            } else {
//                System.err.println("禁止访问,token = " + tokens);
//            }
//        }
//    }
}