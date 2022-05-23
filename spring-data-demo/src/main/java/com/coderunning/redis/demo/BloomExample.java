package com.coderunning.redis.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Arrays;

public class BloomExample {
    private static final String _KEY = "userlist";

    public static void main(String[] args) {
        JedisPool pool = new JedisPool( "localhost", 6379);
        Jedis jedis =pool.getResource();
        for (int i = 1; i < 10001; i++) {
            bfAdd(jedis, _KEY, "user_" + i);
            boolean exists = bfExists(jedis, _KEY, "user_" + (i + 1));
            if (exists) {
                System.out.println("找到了" + i);
                break;
            }
        }
        System.out.println("执行完成");
    }

    /**
     * 添加元素
     * @param jedis Redis 客户端
     * @param key   key
     * @param value value
     * @return boolean
     */
    public static boolean bfAdd(Jedis jedis, String key, String value) {
        String luaStr = "return redis.call('bf.add', KEYS[1], KEYS[2])";
        Object result = jedis.eval(luaStr, Arrays.asList(key, value),
                Arrays.asList());
        if (result.equals(1L)) {
            return true;
        }
        return false;
    }

    /**
     * 查询元素是否存在
     * @param jedis Redis 客户端
     * @param key   key
     * @param value value
     * @return boolean
     */
    public static boolean bfExists(Jedis jedis, String key, String value) {
        String luaStr = "return redis.call('bf.exists', KEYS[1], KEYS[2])";
        Object result = jedis.eval(luaStr, Arrays.asList(key, value),
                Arrays.asList());
        if (result.equals(1L)) {
            return true;
        }
        return false;
    }
}
