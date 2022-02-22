package com.coderunning.factorty;

import org.springframework.stereotype.Service;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JedisClusterFactory {

    private JedisCluster jedisCluster;

    private List<String> hostPortList;

    private int timeout = 1000;

    public void init() {

        // 默认配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        Set<HostAndPort> nodeList = new HashSet<>();


        for (String port : hostPortList) {
            String[] arr = port.split(":");

            if (arr.length != 2) {
                continue;
            }

            nodeList.add(new HostAndPort(arr[0], Integer.parseInt(arr[1])));

        }

        jedisCluster = new JedisCluster(nodeList, timeout, jedisPoolConfig);


    }

    public void destory() {

        if (jedisCluster != null) {
            jedisCluster.close();
        }
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public List<String> getHostPortList() {
        return hostPortList;
    }

    public void setHostPortList(List<String> hostPortList) {
        this.hostPortList = hostPortList;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
