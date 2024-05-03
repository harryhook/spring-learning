package com.coderunning;

import java.util.HashMap;

/**
 * @author haowei.chen
 * @since 2024/4/30 15:30
 */
public class HashMapTest {


    public static void main(String[] args) {
        final HashMap<Integer, Integer> map = new HashMap<>(2);
        map.put(5, 55);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                map.put(i, i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                map.get(5);
            }
        });

        t1.start();
        t2.start();
    }
}


