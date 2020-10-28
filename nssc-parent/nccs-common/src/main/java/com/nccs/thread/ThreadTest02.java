package com.nccs.thread;

import java.util.concurrent.Semaphore;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-27 14:27
 * @description: 使用多线程实现生产者 -- 消费者问题
 * 1.生产者往缓冲区添加产品，消费者从缓冲区消费产品
 * 2.生产者和消费者交替执行
 * 3.当缓冲区满的时候，不能够继续进行生产，当缓冲区为空时，不能够继续消费
 **/

public class ThreadTest02 {
    private final static int MAX_SIZE = 10; //缓冲区大小
    private static int count = 0; //计算已存入缓冲区的数量
    private static Semaphore semaphore = new Semaphore(1); //初始化信号量


    public static void main(String[] args) {
        //生产者线程
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);  //生产者每0.5秒生产一个产品
                    semaphore.acquire(); //获取信号量  如果获取成功，则继续往下走；如果获取失败，则阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (count < MAX_SIZE) { //判断缓冲区大小是否已达到最大值
                    count++; //如果未达到最大值，则+1
                    System.out.println("生产者：+ 1，count = " + count);
                    System.out.println("...");
                    semaphore.release();//释放锁。此处释放锁目的是让生产者和消费者能够交替运行，而不是生产者一直生产产品，直到缓冲区满了后，消费者再来消费产品
                } else {//如果缓冲区达到最大值，则释放锁
                    System.out.println("缓存满了，停止生产");
                    semaphore.release();//释放锁
                }
            }
        }).start();

        //消费者线程
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000); //消费者每1秒消费一个产品
                    semaphore.acquire();  //获取锁，未获取到，则阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (count > 0) {//判断缓冲区是否还有产品未消费
                    count--;
//                    System.out.println("消费者：- 1，count = " + count);
                    System.out.println("...");
                    semaphore.release();//释放锁
                } else {
                    System.out.println("缓存空了，停止消费");
                    semaphore.release();//释放锁
                }


            }
        }).start();

    }

}
