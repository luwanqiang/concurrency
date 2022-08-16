package learn;

import java.util.HashMap;
import java.util.UUID;

/**
 * @Projectname: concurrency
 * @Filename: HashMapCircle
 * @Author: LWQ
 * @Data:2022/8/16 23:00
 * @Description: HashMap在多线程并发下会导致死循环
 *  因为多线程会导致HashMap的Entry链表形成环状数据结构，
 *  一旦形成环状数据结构，Entry的 next节点永远不为空，就会产生死循环获取Entry
 */

public class HashMapCircle {
    final HashMap<String, String> map = new HashMap<String, String>(2);

    public void circle() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "circle" + i).start();
                }
            }
        }, "circleMain");

        thread.start();
        thread.join();
    }

    public static void main(String[] args) {
        HashMapCircle hashMapCircle = new HashMapCircle();
        try {
            hashMapCircle.circle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
