package com.nccs.thread;

import java.util.concurrent.Semaphore;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-28 08:55
 * @description: 吃水果问题
 * 一家四口爸爸、妈妈、儿子、女儿。桌上有一个盘子，一次只能放一个水果。爸爸专门放苹果，妈妈专门放橘子。儿子专门吃橘子，女儿专门吃苹果。
 * 只要盘子为空，则爸爸妈妈其中的一人可以向盘中放入水果；仅当出现自己需要的水果时，儿子女儿才能从盘中取出
 **/

public class ThreadTest05 {
    private static Semaphore diskMutex = new Semaphore(1); //盘子锁
    private static Semaphore appleMutex = new Semaphore(0); //苹果锁 初始向量为0 只有当父亲将水果放入之后，向量+1
    private static Semaphore orangeMutex = new Semaphore(0); //橘子锁 初始向量为0 只有当母亲将水果放入之后，向量+1


    public static void main(String[] args) {
        //启动线程
        new Father().start();
        new Mother().start();
        new Son().start();
        new Daughter().start();

    }

    //父亲
    static class Father extends Thread {
        private final static String NAME = "父亲";
        private final static String APPLE = "苹果";

        @Override
        public void run() {
//            System.out.println(NAME + "线程正在运行");
            try {
                while (true) {
                    diskMutex.acquire(); //获取盘子锁
                    Disk.putFruit(NAME, APPLE); //放入苹果
                    sleep(1000);
                    appleMutex.release(); //释放苹果锁
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //母亲
    static class Mother extends Thread {
        private final static String NAME = "母亲";
        private final static String ORANGE = "橘子";

        @Override
        public void run() {
//            System.out.println(NAME + "线程正在运行");
            try {
                while (true) {
                    diskMutex.acquire(); //获取盘子锁
                    Disk.putFruit(NAME, ORANGE); //放入橘子
                    sleep(1000);
                    orangeMutex.release(); //释放橘子锁
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //儿子线程
    static class Son extends Thread {
        private final static String NAME = "儿子";
        private final static String ORANGE = "橘子";

        @Override
        public void run() {
//            System.out.println(NAME + "线程正在运行");
            try {
                while (true) {
                    orangeMutex.acquire(); //获取橘子锁
                    Disk.takeFruit(NAME, ORANGE); //吃橘子
                    sleep(1000);
                    diskMutex.release(); //释放盘子锁
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //女儿
    static class Daughter extends Thread {
        private final static String NAME = "女儿";
        private final static String APPLE = "苹果";

        @Override
        public void run() {
//            System.out.println(NAME + "线程正在运行");
            try {
                while (true) {
                    appleMutex.acquire(); //获取苹果锁
                    Disk.takeFruit(NAME, APPLE); //吃苹果
                    sleep(1000);
                    diskMutex.release(); //释放盘子锁
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //盘子
    static class Disk {

        public static void putFruit(String name, String fruit) {
            System.out.println(name + "往盘子里边放了一个" + fruit);
        }

        public static void takeFruit(String name, String fruitName) {
            System.out.println(name + "吃了一个" + fruitName);
        }

    }


}
