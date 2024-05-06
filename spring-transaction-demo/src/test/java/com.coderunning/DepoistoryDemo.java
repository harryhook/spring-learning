package com.coderunning;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @since 2024/5/6 23:19
 */
public class DepoistoryDemo {


    public static void main(String[] args) {
        Depoistory depot = new Depoistory(500);
        new Producer(depot).produce(500);
        new Producer(depot).produce(200);
        new Consumer(depot).consume(500);
        new Consumer(depot).consume(200);
        new Producer(depot).produce(100);

    }

}

class Consumer {

    private Depoistory depot;

    public Consumer(Depoistory depot) {
        this.depot = depot;
    }

    public void consume(int count) {
        new Thread(() -> depot.consume(count), count + " consume thread").start();
    }
}

class Producer {

    Depoistory depoistory;

    public Producer(Depoistory depoistory) {
        this.depoistory = depoistory;
    }

    public void produce(int count) {
        new Thread(() -> depoistory.produce(count), count + " producer thread").start();
    }
}

class Depoistory {

    int size;
    int capacity;
    ReentrantLock lock = new ReentrantLock();
    Condition full;
    Condition empty;

    public Depoistory(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        full = lock.newCondition();
        empty = lock.newCondition();

    }

    public void produce(int count) {
        lock.lock();
        int left = count;
        try {
            while (left > 0) {
                while (size >= capacity) {
                    System.out.println(Thread.currentThread() + " before await");
                    full.await();
                    System.out.println(Thread.currentThread() + " after await");
                }
                int inc = (left + size) > capacity ? (capacity - size) : left;
                left -= inc;
                size += inc;
                System.out.println("生产者生产了" + inc + "个产品，当前仓库中有" + size + "个产品");
                empty.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consume(int count) {
        lock.lock();
        int left = count;
        try {
            while (left > 0) {
                while (size <= 0) {
                    System.out.println(Thread.currentThread() + " before await");
                    empty.await();
                    System.out.println(Thread.currentThread() + " after await");
                }
                int dec = (size - left) > 0 ? left : size;
                left -= dec;
                size -= dec;
                System.out.println("消费者消费了" + dec + "个产品，当前仓库中有" + size + "个产品");
                full.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

