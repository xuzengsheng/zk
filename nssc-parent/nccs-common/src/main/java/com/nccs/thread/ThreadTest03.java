package com.nccs.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-27 15:33
 * @description: 多线程实现读者 -- 写者问题
 * 1.同一时间只允许一个写者对文件进行操作，写的时候不允许读（读--写互斥，写--写互斥）
 * 2.同一时间可以允许多个读者进行读取，但人数不能超过要求的最大人数(3)
 **/

public class ThreadTest03 {

    public static void main(String[] args) {

        Queue3 queue3 = new Queue3();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue3.read();
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue3.write(new Random().nextInt(10000));
                }
            }).start();
        }


    }


    //内部类
    static class Queue3 {
        private Object data = null; // 共享数据，同一时刻只能有一个线程能写该数据，但可以有多个线程同时读该数据。
        private Semaphore wmutex = new Semaphore(1); //写锁信号量，同一时刻只允许一个人进行写操作
        private Semaphore rmutex = new Semaphore(3); //读锁信号量。同一时刻允许多人进行读操作，最多三人
        private int count = 0; //人数

        //读操作
        public void read() {
            try {
                rmutex.acquire(); //获取读锁信号量
                if (count == 0) { //判断是否有进程已经在进行读操作，如果没有，那么第一个读操作的进程要获取写锁
                    wmutex.acquire();// 获取写锁的信号量，防止其他线程获取到写锁
                }
                count++; //人数+1
                System.out.println(Thread.currentThread().getName() + " 准备读取数据");
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 读取到数据 :" + data);
                count--; //读取到数据后，读者准备退出
                if (count == 0) { //判断当前人数里面是否还有其他读者，如果没有，则将写锁也一同释放
                    wmutex.release();//当所有读者都退出后，释放写锁
                }
                rmutex.release(); //释放一个读锁的信号量，让出位置，方便其他读者获取锁
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //写操作
        public void write(Object data) {
            try {
                wmutex.acquire(); //获取写锁
                System.out.println(Thread.currentThread().getName() + " 准备写入数据");
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.data = data; //写入数据
                System.out.println(Thread.currentThread().getName() + " 已经写入数据: " + data);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                wmutex.release(); //写完后释放写锁
            }
        }
    }
}
