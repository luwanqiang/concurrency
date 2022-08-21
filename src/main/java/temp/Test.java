package temp;

import sun.misc.Unsafe;

import javax.swing.text.Segment;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.*;

public class Test {
    private static final Integer LOCK = 0;

    /**
     * 1. AbstractQueuedSynchronizer  --同步器
     * 2. ReentrantLock  --重入锁
     * 3. ReentrantReadWriteLock  --读写锁
     * 4. LockSupport 静态工具类
     */
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Lock reentrantLock_2 = new ReentrantLock();
        ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Segment segment = new Segment();
        // 构造函数是私有的
//        Unsafe unsafe = new Unsafe();
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        // 阻塞当前线程，调用 unpark()或当前线程中断，才能从 park()方法返回
//        LockSupport.park();
        for (int i = 0; i < 5; i++) {
            reentrySyn();
        }
        Map map = new HashMap();
        map.put("key", "oldValue");
        System.out.println(map.put("key", "newValue"));

        // 当前设备的 CPU个数
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println("当前设备CPU个数:" + i);

    }

    /**
     * synchronized重入示例
     * synchronized关键字隐式的支持了重入锁
     */
    public static void reentrySyn() {
        synchronized (LOCK) {
            System.out.println("synchronized is reentry!!!");
        }
    }
}
