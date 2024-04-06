package com.coderunning.bootstrap;

import com.coderunning.provider.HelloService;
import com.coderunning.proxy.RpcProxy;
import com.coderunning.req.RpcRequest;

/**
 * @author haowei.chen
 * @since 2024/3/18 19:59
 */
// ClientBootstrap.java
public class ClientBootstrap {
    public static void main(String[] args) {
        // 创建代理对象
        HelloService helloService = RpcProxy.createProxy(HelloService.class, "localhost", 8088);
        // 通过代理对象调用远程方法
        String result = helloService.sayHello("World");
        System.out.println(result);
    }
}