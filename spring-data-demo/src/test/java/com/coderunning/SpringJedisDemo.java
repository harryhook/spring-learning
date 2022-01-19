package com.coderunning;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@Slf4j
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


    @Test
    public void testJedisSentinel() {

        Set<String> sentinels = new HashSet<String>();
        sentinels.add("127.0.0.1:26379");
        sentinels.add("127.0.0.1:26380");
        sentinels.add("127.0.0.1:26381");
        
        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);

        try (Jedis jedis = sentinelPool.getResource()) {
            System.out.println(System.currentTimeMillis());
            for (int i = 0; i < 100000; i++) {

                jedis.hset("hashKey:" + i, "field:" + i, "value:" + i);

            }


        }
        System.out.println(System.currentTimeMillis());


    }

    @Test
    public void testJedisSentinelFailover() {

        Set<String> sentinels = new HashSet<>();
        sentinels.add("127.0.0.1:26379");
        sentinels.add("127.0.0.1:26380");
        sentinels.add("127.0.0.1:26381");

        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster", sentinels);

        int count = 0;
        while(true) {
            Jedis jedis = null;

            try {
                jedis = sentinelPool.getResource();
                jedis.hset("hashKey:" + count, "field:" + count, "value:" + count);


                if(count % 200 == 0) {
                    Thread.sleep(100);
                    log.info(jedis.hget("hashKey:" + count, "field:" + count));
                }
                count++;
            }catch (Exception e) {
                e.printStackTrace();
            }finally {

            if(jedis != null ){
                jedis.close();
                }
            }
        }



    }
}
