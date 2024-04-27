package com.coderunning.redis.demo;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author haowei.chen
 * @since 2024/4/25 22:30
 */
public class RedisLockApplication {

    public static void main(String[] args) throws InterruptedException {

        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379");

        // 1. 创建RedissonClient
        RedissonClient redisson = Redisson.create(config);
        testRedissonSimpleLock(redisson);

    }

    private static void testRedissonSimpleLock(RedissonClient redisson) throws InterruptedException {
        // 2. 获取锁
        RLock lock = redisson.getLock("lock");

        lock.lock();
        boolean islocked = lock.tryLock(30, 10, TimeUnit.SECONDS);
        // 3. 业务逻辑
        System.out.println("业务逻辑");

        Thread.sleep(5 * 1000);
        // 4. 释放锁
        lock.unlock();
        System.out.println("释放锁");
    }

}
