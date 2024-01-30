package com.coderunning.service;

import java.util.concurrent.locks.LockSupport;

/**
 * @author harryhook
 * @date 2022/10/8 23:39
 * @desc
 */
public class LockSupportDemo {
    public static void main(String[] args) {
        MyLockSupportThread myThread = new MyLockSupportThread(Thread.currentThread());
        myThread.start();
        System.out.println("before park");
        // 获取许可
        LockSupport.park();
        System.out.println("after park");
    }
}
