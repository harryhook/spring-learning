package com.coderunning;

/**
 * @author haowei.chen
 * @description snowflake算法
 * @since 2024/4/26 14:45
 */
public class IdWorker {

    private final long workerId;
    private final long datacenterId;
    private long sequence = 0L;
    private final long twepoch = 1288834974657L; // 2010-11-04 09:42:54
    private final long workerIdBits = 5L;
    private final long datacenterIdBits = 5L;
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);  // 2^5-1=31
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);  // 2^5-1=31
    private final long sequenceBits = 12L;  // 12 位序列号 2^12=4096
    private final long workerIdShift = sequenceBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;  // 12+5=17
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;  // 12+5+5=22
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);  // 2^12-1=4095
    private long lastTimestamp = -1L;

    public IdWorker(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            lastTimestamp - timestamp));
        }
        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出 即 序列 > 4095
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;

        // 这儿就是最核心的二进制位运算操作，生成一个64bit的id
        // 先将当前时间戳左移，放到41 bit那儿；将机房id左移放到5 bit那儿；将机器id左移放到5 bit那儿；将序号放最后12 bit
        // 最后拼接起来成一个64 bit的二进制数字，转换成10进制就是个long型
        return ((timestamp - twepoch) << timestampLeftShift) |   // shift 22处， 41位
                (datacenterId << datacenterIdShift) |   // shift 到 17处， 5位
                (workerId << workerIdShift) |  // shift 到 12处， 5位
                sequence;   // 毫秒内序列 shift 到 0处, 12位
    }

    // 获取当前时间戳，如果和上次生成id的时间戳相同，则进行毫秒内序列
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        // 优化点： 机房id 和机器id 可以替换成业务变量，比如用户id， 订单id等
        IdWorker worker = new IdWorker(1, 2);
        for (int i = 0; i < 10; i++) {
            System.out.println(worker.nextId());
        }
    }

}