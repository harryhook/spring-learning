package com.coderunning.service;

import java.util.ArrayList;
import java.util.Random;

public class TestObjectSize {

    public static void main(String[] args) {
        ArrayList<Picture> list = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(1024 * 1024)));

            Thread thread = new Thread(() -> {
                ThreadLocal<String> threadLocal = new ThreadLocal<>();
                threadLocal.set("zhangsan");
                System.out.println(threadLocal.get());
            });

            thread.start();
            // 当前线程可以得到zhangsan，

            Thread thread2 = new Thread(() -> {
                ThreadLocal<String> threadLocal = new ThreadLocal<>();
                System.out.println(threadLocal.get());
            });

            thread2.start();//thread2这个线程执行会得到null
        }
    }
}
class Picture {
    private byte[] pixels;

    public Picture(int length) {
        this.pixels = new byte[length];
    }
}

