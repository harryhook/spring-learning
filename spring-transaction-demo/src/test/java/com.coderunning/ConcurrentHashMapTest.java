package com.coderunning;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author haowei.chen
 * @since 2024/4/30 15:30
 */
public class ConcurrentHashMapTest {


    public static void main(String[] args) {
        final ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(5, 55);

        System.out.println(map.get(5));
    }
}


