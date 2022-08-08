package learn;

/**
 *  通过 javap工具查看生成的class文件
 */
public class Synchronized {
    public static void main(String[] args) {
        // 对 Synchronized Class对象加锁
        synchronized (Synchronized.class) {
            System.out.println("获取锁成功__1");
        }

        // 静态同步方法，对 Synchronized Class对象加锁
        lock();
    }

    public static synchronized void lock(){
        System.out.println("获取锁成功__2");
    }
}
