package com.coderunning.provider.impl;

import com.coderunning.annotation.RpcService;
import com.coderunning.provider.HelloService;

/**
 * @author haowei.chen
 * @since 2024/3/16 19:06
 */
// 服务提供方实现
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("HelloServiceImpl收到: " + name);
        return "Hello, " + name + "!";
    }
}

