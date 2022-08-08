package learn;

import java.util.concurrent.TimeUnit;

/**
 *  安全的终止线程
 *      1. 通过中断标识位进行终止
 *      2. 利用一个boolean变量进行终止
 *    以上两种做法可以使线程在终止时有机会去清理资源
 */
public class Shutdown {
    public static void main(String[] args) throws InterruptedException {
        Runner runner_1 = new Runner();
        Thread countThread_1 = new Thread(runner_1, "countThread_1");
//        countThread_1.setDaemon(true);
        countThread_1.start();
        // 睡眠1秒，main线程对 countThread线程进行中断，使 countThread能够感知到中断而结束
        TimeUnit.SECONDS.sleep(1);
        countThread_1.interrupt();

        Runner runner_2 = new Runner();
        Thread countThread_2 = new Thread(runner_2, "countThread_2");
//        countThread_2.setDaemon(true);
        countThread_2.start();
        // 睡眠1秒，main线程对 countThread线程进行中断，使 countThread能够感知到中断而结束
        TimeUnit.SECONDS.sleep(1);
        runner_2.cancel();
    }

    private static class Runner implements Runnable {
        private long i;
        private volatile boolean flag = true;
        @Override
        public void run() {
            while (flag && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        private void cancel() {
            flag = false;
        }
    }
}
