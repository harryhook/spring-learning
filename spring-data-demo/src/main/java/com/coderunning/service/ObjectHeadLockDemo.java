package com.coderunning.service;

import org.openjdk.jol.info.ClassLayout;

public class ObjectHeadLockDemo {
    // 这里创建了一个没有成员属性的C对象，使用JOL类库中的ClassLayout类查看其对象布局信息
    private static C c = new C();

    public static void main(String[] args) throws InterruptedException {
        // 这时候c还没有加锁，是一个无锁可偏向（没有锁住，但是可以加偏向锁）的状态，所以Markword最后三位为101，前面都为0。
        // 后面t1线程对c加锁时会加偏向锁，再后面还是t1线程继续加锁依然是偏向锁，当其他线程来加锁（交替进行）时会膨胀为轻量锁，抢占锁时会膨胀为重量锁
        Thread t1 = new Thread(() -> testLock());
        Thread t2 = new Thread(() -> testLock());
        t1.setName("t1");
        t2.setName("t2");
        // t1线程启动，对c加锁，会加偏向锁，Markword最后三位还是101，但是前面不再是0，而是保存了t1线程的id，表示偏向t1线程。
        // 后面如果在其他线程来加锁前t1线程再次来加锁的话，c加的还是偏向锁，其Markword内容不变且加锁效率极高，这也是偏向锁设计的目的。
        t1.start();
        // 调用join，后面的代码在t1线程执行完后才会开始执行
        t1.join();
        // 这时候t1线程已经执行完毕释放了锁，所以不会有抢占锁的情况，锁只会膨胀为轻量锁；如果不调用join，t2线程如果与t1线程同时抢占锁，锁就会膨胀为重量锁
        t2.start();
    }

    private static void testLock() {
        // 加锁前中后都打印一下，看看有什么不同
        System.out.println(Thread.currentThread().getName());
        System.out.println(ClassLayout.parseInstance(c).toPrintable());
        synchronized (c) {
            System.out.println(ClassLayout.parseInstance(c).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(c).toPrintable());
    }
}

class C {}