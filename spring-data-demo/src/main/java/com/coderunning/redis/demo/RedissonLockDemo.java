package com.coderunning.redis.demo;

import org.springframework.data.redis.connection.RedisCommands;

import java.util.concurrent.TimeUnit;

public class RedissonLockDemo {

    public boolean tryLock(long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException {
        long time = unit.toMillis(waitTime);
        long current = System.currentTimeMillis();
        long threadId = Thread.currentThread().getId();  // 1.尝试获取锁

        Long ttl = tryAcquire(leaseTime, unit, threadId);  // lock acquired
        if (ttl == null) {
            return true;
        }
        // 申请锁的耗时如果大于等于最大等待时间，则申请锁失败.
        time -= System.currentTimeMillis() - current;
        if (time <= 0) {
            acquireFailed(threadId);
            return false;
        }
        current = System.currentTimeMillis();
        /**  * 2.订阅锁释放事件，并通过 await 方法阻塞等待锁释放，有效的解决了无效的锁申请浪费资源的问题：
         * * 基于信息量，当锁被其它资源占用时，当前线程通过 Redis 的 channel 订阅锁的释放事件，一旦锁释放会发消息通知待等待的线程进行竞争.  *  *
         * 当 this.await 返回 false，说明等待时间已经超出获取锁最大等待时间，取消订阅并返回获取锁失败.  *
         * 当 this.await 返回 true，进入循环尝试获取锁.  */
        RFuture<RedissonLockEntry> subscribeFuture = subscribe(threadId);
        // await 方法内部是用 CountDownLatch 来实现阻塞，获取 subscribe 异步执行的结果（应用了 Netty 的 Future）
        if (!subscribeFuture.await(time, TimeUnit.MILLISECONDS)) {
            if (!subscribeFuture.cancel(false)) {
                subscribeFuture.onComplete((res, e) -> {
                    if (e == null) {
                        unsubscribe(subscribeFuture, threadId);
                    }
                });
            }
            acquireFailed(threadId);
            return false;
        }
        try {    // 计算获取锁的总耗时，如果大于等于最大等待时间，则获取锁失败.
            time -= System.currentTimeMillis() - current;
            if (time <= 0) {
                acquireFailed(threadId);
                return false;
            }      /**    * 3.收到锁释放的信号后，在最大等待时间之内，循环一次接着一次的尝试获取锁    * 获取锁成功，则立马返回 true，    * 若在最大等待时间之内还没获取到锁，则认为获取锁失败，返回 false 结束循环    */
            while (true) {
                long currentTime = System.currentTimeMillis();        // 再次尝试获取锁
                ttl = tryAcquire(leaseTime, unit, threadId);      // lock acquired
                if (ttl == null) {
                    return true;
                }      // 超过最大等待时间则返回 false 结束循环，获取锁失败
                time -= System.currentTimeMillis() - currentTime;
                if (time <= 0) {
                    acquireFailed(threadId);
                    return false;
                }        /**      * 6.阻塞等待锁（通过信号量(共享锁)阻塞,等待解锁消息）：      */currentTime = System.currentTimeMillis();
                if (ttl >= 0 && ttl < time) {        //如果剩余时间(ttl)小于wait time ,就在 ttl 时间内，从Entry的信号量获取一个许可(除非被中断或者一直没有可用的许可)。
                    getEntry(threadId).getLatch().tryAcquire(ttl, TimeUnit.MILLISECONDS);
                } else {        //则就在wait time 时间范围内等待可以通过信号量
                    getEntry(threadId).getLatch().tryAcquire(time, TimeUnit.MILLISECONDS);
                }        // 更新剩余的等待时间(最大等待时间-已经消耗的阻塞时间)
                time -= System.currentTimeMillis() - currentTime;
                if (time <= 0) {
                    acquireFailed(threadId);
                    return false;
                }
            }
        } finally {    // 7.无论是否获得锁,都要取消订阅解锁消息
            unsubscribe(subscribeFuture, threadId);
        }
        return get(tryLockAsync(waitTime, leaseTime, unit));
    }


    private void scheduleExpirationRenewal(long threadId) {
        ExpirationEntry entry = new ExpirationEntry();
        ExpirationEntry oldEntry = EXPIRATION_RENEWAL_MAP.putIfAbsent(getEntryName(), entry);
        if (oldEntry != null) {
            oldEntry.addThreadId(threadId);
        } else {
            entry.addThreadId(threadId);
            renewExpiration();
        }
    }

    protected RFuture<Boolean> renewExpirationAsync(long threadId) {
        return commandExecutor.evalWriteAsync(getName(),
                LongCodec.INSTANCE, RedisCommands.EVAL_BOOLEAN,
                "if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then " + "redis.call('pexpire', KEYS[1], ARGV[1]); " + "return 1; " + "end; " + "return 0;",
                Collections.<Object>singletonList(getName()), internalLockLeaseTime, getLockName(threadId));
    }

    private <T> RFuture<Long> tryAcquireAsync(long leaseTime, TimeUnit unit, long threadId) {
        if (leaseTime != -1) {
            return tryLockInnerAsync(leaseTime, unit, threadId, RedisCommands.EVAL_LONG);
        }
        RFuture<Long> ttlRemainingFuture = tryLockInnerAsync(commandExecutor.getConnectionManager().getCfg().getLockWatchdogTimeout(),
                TimeUnit.MILLISECONDS, threadId, RedisCommands.EVAL_LONG);
        ttlRemainingFuture.onComplete((ttlRemaining, e) -> {
            if (e != null) {
                return;
            }
            // lock acquired
            if (ttlRemaining == null) {
                scheduleExpirationRenewal(threadId);
            }
        });
        return ttlRemainingFuture;
    }

}
