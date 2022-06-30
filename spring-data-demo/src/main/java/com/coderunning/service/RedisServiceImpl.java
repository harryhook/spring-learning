package com.coderunning.service;

import com.coderunning.factorty.JedisClusterFactory;
import com.coderunning.service.RedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private JedisClusterFactory jedisClusterFactory;


    @Override
    public void set(String key, String name) {

        jedisClusterFactory.getJedisCluster().set(key, name);
    }

    @Override
    public String get(String key) {
        return jedisClusterFactory.getJedisCluster().get(key);
    }
}
