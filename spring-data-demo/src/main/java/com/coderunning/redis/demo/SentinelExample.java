package com.coderunning.redis.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class SentinelExample {
    // master name
    private static String _MASTER_NAME = "mymaster";

    public static void main(String[] args) {
        // Sentinel 配置信息
        Set<String> set = new HashSet<>();
        // 连接信息 ip:port
        set.add("127.0.0.1:26379");
        // 创建 Sentinel 连接池
        Set<String> sentinels = new HashSet<>();
        sentinels.add("127.0.0.1:26379");
        sentinels.add("127.0.0.1:26377");

        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);

        // 获取 Redis 客户端
        Jedis jedis = sentinelPool.getResource();
        // 设置元素
        String setRes = jedis.set("key", "Hello, redis.");
        System.out.println(setRes);
        // 获取元素
        System.out.println(jedis.get("key"));
    }
}
