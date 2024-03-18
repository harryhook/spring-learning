package com.coderunning.bootstrap;

import com.coderunning.provider.impl.HelloServiceImpl;
import com.coderunning.server.RpcServer;

/**
 * @author haowei.chen
 * @since 2024/3/18 19:59
 */
public class ServerBootstrap {
    public static void main(String[] args) throws Exception {
        RpcServer.export(new HelloServiceImpl());
    }
}
