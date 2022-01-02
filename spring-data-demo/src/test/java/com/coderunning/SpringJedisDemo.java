package com.coderunning;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

@SpringBootTest
public class SpringJedisDemo {

    @Test
    public void init() {
        System.out.println("====");
    }


    @Test
    public void testSales() {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxWaitMillis(1000);

        JedisPool pool = new JedisPool(jedisPoolConfig, "localhost", 6379);

        for (int i = 0; i < 10; i++) {
            Jedis jedis;
            try {
                jedis = pool.getResource();
                jedis.ping();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        pool.getResource().ping();

//        JedisPooled jedis = new JedisPooled("localhost", 6379);
//
//        jedis.sadd("planets", "Venus");
//
//        System.out.println(jedis.srandmember("planets", 1));
//
//
//        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
//        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7379));
//        jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7380));
////        JedisCluster jedis = new JedisCluster(jedisClusterNodes);
//
//        jedis.sadd("planets", "Mars");
//
//        System.out.println(jedis.srandmember("planets", 2));


    }

    @Test
    public void testPipeline() {


        JedisPool pool = new JedisPool( "localhost", 6379);
        Jedis jedis =pool.getResource();
        System.out.println(System.currentTimeMillis());
        for (int i = 0; i < 100000; i++) {

             jedis.hset("hashKey:"+i, "field:"+i, "value:" + i);

        }
        System.out.println(System.currentTimeMillis());
//        1641135361268
//        1641135363777


        System.out.println(System.currentTimeMillis());

        for(int i=0; i<100; i++) {
            Pipeline pipeline = jedis.pipelined();

            for(int j = i*1000; j<(i+1) * 1000; j++) {
                pipeline.hset("hashKey:"+i, "field:"+i, "value:" + i);

            }
            pipeline.syncAndReturnAll();
        }
        System.out.println(System.currentTimeMillis());


    }
}
