package learn;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @Projectname: concurrency
 * @Filename: AtomicIntegerArrayTest
 * @Author: LWQ
 * @Data:2022/8/21 22:30
 * @Description: leetcode_practice
 */

public class AtomicIntegerArrayTest implements Runnable{
    static int[] value = {1, 2};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    @Override
    public void run() {
        System.out.println("111");
    }

    public static void main(String[] args) {
        int andSet = ai.getAndSet(0, 3);
        System.out.println(andSet);
        System.out.println(ai.get(0));
        /**
         * AtomicIntegerArray 操作的是原数组的副本，操作时不会对原数组产生
         */
        System.out.println(value[0]);
    }
}
