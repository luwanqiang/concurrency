package learn;

import comm.SleepUtils;

/**
 *  Java虚拟机退出时 Daemon线程中的 finally语句块不一定会执行：
 *      main线程在启动 daemonRunner线程后，daemonRunner线程随着main方法执行完毕而终止，
 *      此时JVM中已经没有 非Daemon线程，虚拟机需要退出，JVM中的所有 Daemon线程需要立即终止。
 *      因此 DaemonRunner线程中的 fainally语句块并没有执行。
 */
public class Daemon {
    public static void main(String[] args) {
        Thread daemonRunner = new Thread(new DaemonRunner(), "DaemonRunner");
        /**
         *  设置该线程为后台守护线程
         *  Daemon 属性需要在启动线程之前设置，不能在启动线程后设置
         *      在线程启动后设置Daemon属性会导致程序报错，抛出 IllegalThreadStateException 异常
         */
        daemonRunner.setDaemon(true);
        daemonRunner.start();
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                SleepUtils.second(100);
            } finally {
                /**
                 *  在构建Daemon线程时，不能依靠 finally块中的内容来确保执行关闭或清理资源的逻辑
                 */
                System.out.println("DaemonThread finally run.");
            }

        }
    }
}
