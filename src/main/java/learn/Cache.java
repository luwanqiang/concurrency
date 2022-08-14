package learn;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁示例
 */
public class Cache {
    static Map<String, Object> map = new HashMap<String, Object>();
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock read = readWriteLock.readLock();
    static Lock write = readWriteLock.writeLock();

    // 获取一个key对应的value
    public static final Object get(String key) {
        read.lock();
        try {
            return map.get(key);
        } finally {
            read.unlock();
        }
    }

    // 设置jey对应的value，并返回旧的value
    public static final Object set(String key, Object value) {
        write.lock();
        try {
            return map.put(key, value);
        } finally {
            write.unlock();
        }
    }

    // 清理所有的内容
    public static final void clean() {
        write.lock();
        try {
            map.clear();
        } finally {
            write.unlock();
        }
    }
}
