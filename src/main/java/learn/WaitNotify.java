package learn;

import comm.SleepUtils;

import java.util.concurrent.TimeUnit;

/**
 *  Wait() notify() 示例
 *      1. wait()方法会释放对象的锁；
 *      2. notify()/notifyAll()方法调用后，等待线程不会立即从 wait()返回，需要等待调用 notify()/notifyAll()
 *        的线程释放锁后，等待线程才有机会从 wait()返回；
 *      3. wait()方法返回的前提是获得了调用对象的锁；
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "waitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(new Notify(), "notifyThread").start();
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                // 当条件不满足时，继续wait，同时会释放锁
                while (flag) {
                    try {
                        System.out.println("Wait - MonitorEnter!");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Wait - again MonitorEnter!");
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                // 获得lock的锁，然后进行通知，此时不会释放lock锁
                // wait()的线程需要等待当前线程释放锁后才有机会返回
                lock.notify();
                System.out.println("Notify - MonitorEnter!");
                flag = false;
                SleepUtils.second(3);
            }

            // 再次加锁
            synchronized (lock) {
                System.out.println("Notify - again MonitorEnter!");
            }
        }
    }
}
