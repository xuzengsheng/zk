package com.nccs.thread;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-27 16:43
 * @description: 哲学家进餐问题
 * <p>
 * 解决方法二
 * 5个哲学家共用一张圆桌，分别坐在周围的5张椅子上，
 * <p>
 * 在圆桌上有5个碗和5只筷子（注意是5只筷子，不是5双），
 * <p>
 * 碗和筷子交替排列。他们的生活方式是交替地进行思考（thinking）和进餐（eating）。
 * <p>
 * 方案：
 * 仅当一个哲学家左右两边的叉子都可用时才允许他抓起叉子，即破坏死锁四大条件之一 ----请求和保持条件
 * 说明白点就是，不会出现某个哲学家拿一个筷子等一个筷子的情况，必须同时拿两个！
 **/

public class ThreadTest04 {

    public static void main(String[] args) {
        Chop fiveChops = new Chop(); //初始化筷子
        new Philosopher(0, fiveChops).start();
        new Philosopher(1, fiveChops).start();
        new Philosopher(2, fiveChops).start();
        new Philosopher(3, fiveChops).start();
        new Philosopher(4, fiveChops).start();
    }


    static class Philosopher extends Thread {
        private int index;  //哲学家的位置下标
        private Chop chop;  //哲学家拿的筷子

        public Philosopher(int index, Chop chop) {
            this.index = index;
            this.chop = chop;
        }

        @Override
        public void run() {
            while (true) {
                thinking();
                chop.takeChop(index);
                eating();
                chop.putChop(index);
            }
        }

        private void thinking() {
            System.out.println("第" + index + "个哲学家正在思考...");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void eating() {
            System.out.println("===第" + index + "个哲学家正在吃饭");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    static class Chop {
        private Boolean[] chops = {false, false, false, false, false};

        /**
         * 拿起筷子
         *
         * @param index 哲学家座位的下标
         */
        public synchronized void takeChop(int index) {

            //chops[index]：哲学家左手的筷子，当值为true时，表示已经拿起
            //chops[(index + 1) % 5]:哲学家右手的筷子，当值为true时，表示已经拿起

            //当左右手有一个筷子没拿起时，则进入等待状态
            while (chops[index] || chops[(index + 1) % 5]) {
                try {
                    wait(); //拿不到筷子就会被阻塞 进入等待池 从而不会再来竞争
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //非等待状态
            chops[index] = true; // 拿起左手筷子
            chops[(index + 1) % 5] = true; //拿起右手筷子
        }

        /**
         * 放下筷子
         *
         * @param index 哲学家座位的下标
         */
        public synchronized void putChop(int index) {
            chops[index] = false;
            chops[(index + 1) % 5] = false;
            notifyAll(); //放下后唤醒所有阻塞的线程
        }

    }

}

