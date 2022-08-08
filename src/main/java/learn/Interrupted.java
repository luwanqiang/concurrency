package learn;

import comm.SleepUtils;

import java.util.concurrent.TimeUnit;

/**
 *  查看中断标识位：
 *      sleepThread 线程的中断标识位被清除了
 *      busyThread 线程的中断标识位没有被清除
 */
public class Interrupted {
    public static void main(String[] args) throws InterruptedException {
        // sleepThread 线程不停的进行睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "sleepThread");
        sleepThread.setDaemon(true);
        // busyThread 线程不停运行
        Thread busyThread = new Thread(new BusyRunner(), "busyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();

        // 休眠5秒，让两个线程充分运行
        TimeUnit.SECONDS.sleep(2);
        // 中断线程
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("sleepThread--->" + sleepThread.isInterrupted());
        System.out.println("busyThread--->" + busyThread.isInterrupted());
    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {

            }
        }
    }
}
