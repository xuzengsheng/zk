package com.nccs.thread;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-27 11:31
 * @description: 两个线程交替打印1到100的数字
 **/

public class ThreadTest01 {
    //    private static Semaphore semaphore = new Semaphore(1);
    private final static Object lock = new Object(); //对象锁

    private static int num = 1;

    private static boolean isPrint = false; //判断A是否已经打印过

    public static void main(String[] args) throws InterruptedException {

        //线程A 》》 打印奇数
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                synchronized (lock) {
                    try {
                        while (isPrint) {      //如果A已经打印过，则进行阻塞
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("A:" + num); //如果A没打印过，则打印
                        num++;          //打印后 num自增1
                        isPrint = true; //将isPrint修改为已打印
                    } finally {
                        lock.notify();  //唤醒其他等待中的线程
                    }
                }
            }

        }).start();

        //线程B 》》 打印偶数
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                synchronized (lock) {
                    while (!isPrint) {      //如果A没有打印过，则进行阻塞
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("B:" + num); //如果A打印过，则打印
                    num++; //打印后 num自增1
                    isPrint = false; //将isPrint修改为A未打印
                    lock.notify();  //唤醒其他等待中的线程
                }
            }
        }).start();
    }
}
