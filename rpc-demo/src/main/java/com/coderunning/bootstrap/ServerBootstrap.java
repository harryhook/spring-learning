package com.coderunning.bootstrap;

import com.coderunning.provider.HelloService;
import com.coderunning.provider.impl.HelloServiceImpl;
import com.coderunning.server.RpcServer;

/**
 * @author haowei.chen
 * @since 2024/3/18 19:59
 */
// ServerBootstrap.java
public class ServerBootstrap {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcServer.export(service, 8088);
    }
}